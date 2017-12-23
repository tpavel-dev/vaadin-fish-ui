package kz.kcell.apps.spmot;


import kz.kcell.apps.common.Language;
import kz.kcell.apps.common.security.UsernamePasswordCredential;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Component;

@Component
//@Deprecated
//@ConfigurationProperties("spmot.Config")
public class  Config  {


    // prod
    private static final String  SPMOT_USERNAME = "spmot";
    private static final String  SPMOT_PASSWORD = "Spmot2014";

    // preprod
//    private static final String  SPMOT_USERNAME = "spmot_user";
//    private static final String  SPMOT_PASSWORD = "NobAytuns7";

    private static final boolean NOTIFY_WEN_ADD_PRODUCT = false;

    private static final String  CHANEL_NAME = "SPMOT";

    private static final String  POCKET_NAME = "DealerMotivation_001";

    private static final String  BALANCE_NAME = "BLP_Bonus";

    /**
     * Период за который необходимо успеть активировать подписку на продукт
     */
    private static final int EXPIRED_PERIOD_ACTIVATE_SUBSCRIBE_DAYS = 1;

    /**
     * Период в течение которого нельзя подключать аналогичный продукт
     */
    private static final int PERIOD_DISABLE_REPEAT_ANALOG_SUBSCRIBE_DAYS = 30;



    public static final String SMS_FROM_FIELD = "Activ";

    public static final int SUBSCRIBE_LOG_LAST_DAYS = 10;

    public static final long DEFAULT_WS_TIMEOUT = 5 * 60 * 1000;

    @Getter @Setter
    private static Language DEFAULT_LANGUAGE = Language.RU;

    public String getUser() {return SPMOT_USERNAME;}

//    @Override
//    public static String getUsername() {return SPMOT_USERNAME;}
//
//    @Override
//    public static String getPassword() {return SPMOT_PASSWORD;}

    public boolean isNOTIFY_WEN_ADD_PRODUCT() {
        return NOTIFY_WEN_ADD_PRODUCT;
    }


    public static String getPocketName() {
        return POCKET_NAME;
    }

    public static String getSpmotUsername() {
        return SPMOT_USERNAME;
    }

    public static String getSpmotPassword() {
        return SPMOT_PASSWORD;
    }

    public static boolean isNotifyWenAddProduct() {
        return NOTIFY_WEN_ADD_PRODUCT;
    }

    public static String getChanelName() {
        return CHANEL_NAME;
    }

    public static String getBalanceName() {
        return BALANCE_NAME;
    }

    public static int getExpiredPeriodActivateSubscribeDays() {
        return EXPIRED_PERIOD_ACTIVATE_SUBSCRIBE_DAYS;
    }

    public static int getPeriodDisableRepeatAnalogSubscribeDays() {
        return PERIOD_DISABLE_REPEAT_ANALOG_SUBSCRIBE_DAYS;
    }

//    private static final String root = "http://192.168.208.181:8080";
    private static final String root = "http://ldb-kms.kcell.kz";

    public static String getUsersWebServiceWsdlUrl()      { return root + "/coda/users?wsdl";          }
    public static String getBillingWebServicesWsdlUrl()   { return root + "/coda/billing?wsdl";        }
    public static String getCustomersWebServiceWsdlUrl()  { return root + "/coda/customers?wsdl";      }
    public static String getRefBooksWebServicesWsdlUrl()  { return root + "/coda/rb?wsdl";             }
    public static String getBalancesWebServiceWsdlUrl()   { return root + "/coda/balances?wsdl"; }
    public static String getBossWebServicesWsdlUrl()      { return root + "/coda_axon/boss?wsdl";      }
    public static String getMessagingWebServicesWsdlUrl() { return root + "/coda_axon/messaging?wsdl"; }

    public Config() {}
}
