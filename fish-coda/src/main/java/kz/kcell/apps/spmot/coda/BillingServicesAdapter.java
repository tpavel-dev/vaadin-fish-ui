package kz.kcell.apps.spmot.coda;

import kz.kcell.apps.common.exceptions.ErrorUtils;
import kz.kcell.apps.common.msisdn.Msisdn;
import kz.kcell.apps.pentagon.coda.ws.client.stub.*;
import kz.kcell.apps.spmot.Config;
import kz.kcell.apps.spmot.domain.spmot.entity.Subscribe;
import kz.kcell.apps.spmot.exceptions.CODAInternalFailure;
import kz.kcell.apps.spmot.exceptions.MsisdnNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static kz.kcell.apps.spmot.coda.BillingTestResult.valueOf;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 14 04 2016
 */
@Slf4j
@Component
public class BillingServicesAdapter {

    @Autowired
//    @Lazy
    private BillingServicesWS billingServicesWS;

    public void addBusinessProductWithBonus(Subscribe subscribe) throws AlreadySoldException, AlreadySubscribedException, TomatoSubscribeException, NoMoneyOnBalanceException, BillingServicesException, AddProductException {
        if (emulateBillingTestResult(subscribe)) return;

        String commentForBilling = buildCommentForBilling(subscribe);
        try {

            billingServicesWS.addBusinessProductWithBonusSharing(
                    subscribe.getBpId()
                    , subscribe.getMsisdn()
                    , subscribe.getDealer()
                    , Config.getBalanceName()
                    , Config.getPocketName()
                    , new Double(subscribe.getBonus())
                    , null
                    , null
                    , true
                    , 106
                    , commentForBilling
                    , Config.getChanelName()
                    , subscribe.getShareBonus().doubleValue()
                    , null
                    , null
            );
        } catch (Exception e) {
            log.info("Coda exception: "+e.getLocalizedMessage());
            checkAlreadySubscribeException(e);


            if (checkTomatoSubscribeException(e)) {
                throw new TomatoSubscribeException(e.getLocalizedMessage(), e);

            } else if (checkBalanceException(e)) {
                throw new NoMoneyOnBalanceException(e.getLocalizedMessage(), e);
            } else {
                throw new BillingServicesException(e.getLocalizedMessage(), e);
            }
        }
    }

    public Long getLastTariffId(Msisdn msisdn) throws MsisdnNotFoundException, CODAInternalFailure {
        try {
            ProductDTO tariffKA = billingServicesWS.getTariffKA(msisdn.get());
            return tariffKA.getProductsId();
        } catch (MsisdnNotFoundException_Exception e) {
            throw new MsisdnNotFoundException(e);
        } catch (CODAInternalFailure_Exception e) {
            throw new CODAInternalFailure(e);
        } catch (AttributesRetrivalException_Exception e) {
            throw new CODAInternalFailure(e);
        }
    }

    public void changeTariffNowWithBonus(Subscribe subscribe) throws AlreadySoldException, AlreadySubscribedException, TomatoSubscribeException, NoMoneyOnBalanceException, BillingServicesException, AddProductException {
        if (emulateBillingTestResult(subscribe)) return;

        String commentForBilling = buildCommentForBilling(subscribe);
        try {
            billingServicesWS.changeTariffNowWithBonusSharing(
                    subscribe.getMsisdn()
                    , subscribe.getBpId()
                    , Config.getChanelName()
                    , new Double(subscribe.getBonus())
                    , null
                    , null
                    , subscribe.getDealer()
                    , Config.getBalanceName()
                    , Config.getPocketName()
                    , commentForBilling
                    , subscribe.getShareBonus().doubleValue()
                    , null
                    , null
            );
        } catch (ProductAlreadySoldException_Exception e) {
            log.info("Coda exception: "+e.getLocalizedMessage());
            throw new AlreadySoldException(e.getLocalizedMessage(), e);
        } catch (Exception e) {
            log.info("Coda exception: "+e.getLocalizedMessage());
            checkAlreadySubscribeException(e);

            if (checkTomatoSubscribeException(e)) {
                throw new TomatoSubscribeException(e.getLocalizedMessage(), e);

            } else if (checkBalanceException(e)) {
                throw new NoMoneyOnBalanceException(e.getLocalizedMessage(), e);
            } else {
                throw new BillingServicesException(e.getLocalizedMessage(), e);
            }
        }
    }

    public boolean emulateBillingTestResult(Subscribe subscribe) throws AlreadySoldException, BillingServicesException, NoMoneyOnBalanceException, TomatoSubscribeException, AlreadySubscribedException, AddProductException {
        if (subscribe != null && StringUtils.isNotBlank(subscribe.getBillingTestResult())) {

            String[] s = subscribe.getBillingTestResult().split("-");
            BillingTestResult result = valueOf(s[0]);
            log.info("Billing in emulated mode :" + result);
            if (s.length > 1) {
                long wait_time = Long.valueOf(s[1]) * 1000;
                try {
                    log.info("Emulated bulling deleay on " + wait_time + " sec.");
                    Thread.sleep(wait_time);
                } catch (InterruptedException e) {

                }
            }

            switch (result) {
                case AddProductException:
                    throw new AddProductException();
                case AlreadySoldException:
                    throw new AlreadySoldException();
                case BillingServicesException:
                    throw new BillingServicesException();
                case NoMoneyOnBalanceException:
                    throw new NoMoneyOnBalanceException();
                case TomatoSubscribeException:
                    throw new TomatoSubscribeException();
                case AlreadySubscribedException:
                    throw new AlreadySubscribedException();
                case success:
                    return true;
                // todo доделать
/*
                case AddBusinessProductException_Exception:throw new AddBusinessProductException_Exception();
                case BalanceUpdateException_Exception: throw new BalanceUpdateException_Exception();
                case BillingConnectionFailure_Exception: throw new BillingConnectionFailure_Exception();
                case BusinessProductConfigException_Exception: throw new BusinessProductConfigException_Exception();
                case CDDBInternalFailure_Exception: throw new CDDBInternalFailure_Exception();
                case CODAInternalFailure_Exception: throw new CODAInternalFailure_Exception();
                case IllegalChannelException_Exception: throw new IllegalChannelException_Exception();
                case InvalidInputDataException_Exception: throw new InvalidInputDataException_Exception();
                case ProductAlreadySoldException_Exception: throw new ProductAlreadySoldException_Exception();
                case ProductNotFoundException_Exception: throw new ProductNotFoundException_Exception();
                case TariffChangeException_Exception: throw new TariffChangeException_Exception();
*/

                default:
                    ErrorUtils.defaultSwitchCaseHandle(result);
            }
        }
        return false;
    }

    /**
     * build comment for billing
     * <pre>
     * format
     *  d %s a %s i %s p %s b %s
     *
     *  where
     *
     *  d - dealer
     *  s - subscriber
     *  i - ivr code
     *  p - product id
     *  b - bonus
     * </pre>
     *
     * @param subscribe
     * @return string of comment or exception message if error
     */
    private String buildCommentForBilling(Subscribe subscribe) {
        String result = "";
        try {
            result = String.format(" d %s s %s i %s p %s b %s "
                    , subscribe.getDealer()
                    , subscribe.getMsisdn()
                    , subscribe.getIvrCode()
                    , subscribe.getBpId()
                    , subscribe.getBonus().longValue());
        } catch (Exception exc) {
            log.error(exc.getLocalizedMessage(), exc);
            result = "error build comment ";
        }

        // TODO delete this code 25/11/2015.
//        if (StringUtils.isNoneBlank(System.getProperty("kz.kcell.apps.spmot.ivr.service.IVRService.fixBugCommentStringForBilling"))) {
//            result = "CODA;SPMOT;;21579;" + result + ";";
//        }

        return result;
    }

    /**
     * check error is already subscribe like product
     * <p>
     * In code this error throws as
     * <code>
     * throw new CODAInternalFailure(String.format("Subscriber already has Business Product with id=%s", subsAlternativesBpIds.toString()));
     * </code>
     * <p>
     * kz.kcell.apps.pentagon.coda.ws.client.stub.AddBusinessProductException_Exception: com.orga.opsc.gold.bascu.kcell.client.errors.AddProductException: [KCELL-CS-40009] [CsAddProduct] Error has occurred while trying to sell the following products to account with id [277921]: PP_Unlimited_Data_MobileXL
     * <p>
     * kz.kcell.apps.pentagon.coda.ws.client.stub.AddBusinessProductException_Exception: com.orga.opsc.gold.bascu.kcell.client.errors.AddProductException: [KCELL-CS-40003] [CsAddProduct] The given product [PP_Internet_Everyday_10MB] has already been sold to subscriber (account id = [277921])
     * <p>
     * If message contains string "Subscriber already has Business Product with id="
     * then this is this error
     *
     * @param e
     * @return
     */
    private void checkAlreadySubscribeException(Exception e) throws AlreadySubscribedException, AddProductException {
        String msg = e.getMessage();

//        result = e instanceof ProductAlreadySoldException_Exception;
//        if(result) return result;


        if (StringUtils.contains(msg, "Subscriber already has Business Product with id="))
            throw new AlreadySubscribedException(e.getLocalizedMessage(), e);

        // kz.kcell.apps.pentagon.coda.ws.client.stub.AddBusinessProductException_Exception: com.orga.opsc.gold.bascu.kcell.client.errors.AddProductException: [KCELL-CS-40009] [CsAddProduct] Error has occurred while trying to sell the following products to account with id [277921]: PP_Unlimited_Data_MobileXL

        if (StringUtils.contains(msg, "[KCELL-CS-40009]"))
            throw new AddProductException(e.getLocalizedMessage(), e);
        // kz.kcell.apps.pentagon.coda.ws.client.stub.AddBusinessProductException_Exception: com.orga.opsc.gold.bascu.kcell.client.errors.AddProductException: [KCELL-CS-40003] [CsAddProduct] The given product [PP_Internet_Everyday_10MB] has already been sold to subscriber (account id = [277921])

        if (StringUtils.contains(msg, "[KCELL-CS-40003]"))
            throw new AlreadySubscribedException(e.getLocalizedMessage(), e);

    }

    /**
     * kz.kcell.apps.pentagon.coda.ws.client.stub.AddBusinessProductException_Exception: com.orga.opsc.gold.bascu.kcell.client.errors.AddProductException: [KCELL-CS-50003] [CsAddProduct] Account [277921] has insufficient credit. Operation failed with message: [BCU-UTL-00335] [CsAddProduct] Insufficient credit for the operation. The total available amount [2574.72] (excluding the reserved) is less than the needed amount [14990.0].
     * kz.kcell.apps.pentagon.coda.ws.client.stub.AddBusinessProductException_Exception: com.orga.opsc.gold.bascu.kcell.client.errors.AddProductException: [KCELL-CS-50003] [CsAddProduct] Account [277921] has insufficient credit. Operation failed with message: [BCU-UTL-00335] [CsAddProduct] Insufficient credit for the operation. The total available amount [835.72] (excluding the reserved) is less than the needed amount [1700.0].
     *
     * @param e
     * @return
     */
    private boolean checkBalanceException(Exception e) {
        String msg = e.getMessage();
        boolean result;
        result = StringUtils.contains(msg, "[BCU-UTL-00335]")
                || StringUtils.contains(msg, "[KCELL-CS-30001]");
        return result;
    }

    /**
     * check error is already subscribe TOMATO
     * <p>
     * In code this error throws as
     * <code>
     * String errMsg = String.format("BP %s is not eligible for Tomato %s", bpId, msisdn);
     * </code>
     * <p>
     * If message contains string "is not eligible for Tomato"
     * then this is error
     *
     * @param e
     * @return
     */
    private boolean checkTomatoSubscribeException(Exception e) {
        if (e instanceof TariffChangeException_Exception) {
            String msg = e.getMessage();
            return StringUtils.contains(msg, "Tomato");
        }
        String msg = e.getMessage();
        return StringUtils.contains(msg, "is not eligible for Tomato");

    }


}
