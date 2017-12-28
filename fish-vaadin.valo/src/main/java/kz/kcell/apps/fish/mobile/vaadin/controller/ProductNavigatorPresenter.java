package kz.kcell.apps.fish.mobile.vaadin.controller;


import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import kz.kcell.apps.bonus_cmdr.model.AccessGroup;
import kz.kcell.apps.bonus_cmdr.model.BusinessProduct;
import kz.kcell.apps.bonus_cmdr.model.BusinessProductCtgr;
import kz.kcell.apps.bonus_cmdr.model.Subscribe;
import kz.kcell.apps.common.Format;
import kz.kcell.apps.common.exceptions.BaseException;
import kz.kcell.apps.common.msisdn.FastMsisdn;
import kz.kcell.apps.common.msisdn.Msisdn;
import kz.kcell.apps.fish.exceptions.InvalidValueException;
import kz.kcell.apps.fish.mobile.vaadin.PresenterHelper;
import kz.kcell.apps.fish.mobile.vaadin.annotation.SpringPresenter;
import kz.kcell.apps.fish.mobile.vaadin.data.Account;
import kz.kcell.apps.fish.mobile.vaadin.ui.ErrorHandler;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ProductNavigatorView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.impl.OrderIvrFormViewImpl;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.impl.OrderIvrViewImpl;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.impl.ProductDescViewImpl;
import kz.kcell.vaadin.data.EmptyStringValidator;
import kz.kcell.vaadin.data.MsisdnValidator;
import kz.kcell.vaadin.ui.EventBus;
import kz.kcell.vaadin.ui.EventType;
import kz.kcell.vaadin.ui.Presenter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.EventObject;
import java.util.List;

import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.*;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 18 09 2014
 */
@Slf4j
@SpringPresenter
public class ProductNavigatorPresenter extends AbstractPresenter<ProductNavigatorView>
        implements Presenter, ProductNavigatorView.Listener

{


    //    @Autowired
    @Setter
    private ProductNavigatorView view;

    //    @Autowired
    @Setter
    private OrderIvrFormViewImpl orderFormView;

    //    @Autowired
    @Setter
    private OrderIvrViewImpl orderIvrView;

    //    @Autowired
    @Setter
    private ProductDescViewImpl productDescView;

    @Autowired
//    @Setter
    private EventBus eventBus;

//    @Getter
//    private BusinessProduct selectedBusinessProduct;

//    private FastMsisdn subscriberMsisdn;

//    private Subscribe subscribe;


    public ProductNavigatorPresenter() {
//        productService = ProductServiceRestClient.getInstance();
    }

    @PostConstruct
    public void init() {
//        view.setListener(this);
    }

    @Override
    public List<BusinessProductCtgr> fetchProductCtgrList() {
            return null;
    }

    @Override
    public void onUpdate(EventObject source) {
        try {
            view.showProductsScreen();
        } catch (Exception e) {
            view.showUpdateScreen();
        }
    }

    @Override
    public void showDescriptionInBrowser() {
        BusinessProduct selectedBusinessProduct = getSelectedBusinessProduct();

        String name = "";
        switch (SessionManager.getAccount().getLang()) {
            case RU: {
                name = selectedBusinessProduct.getDescRu();
                break;
            }
            case KK: {
                name = selectedBusinessProduct.getDescKz();
                break;
            }
        }

        UI.getCurrent().getPage().open(name, "_blank");
    }

    @Override
    public void showDescriptionInner() {
        eventBus.post(EventType.SHOW_PRODUCT_DESC);
    }

    @Override
    public void enterProductDescView(ProductDescViewImpl view) {
        BusinessProduct selectedBusinessProduct = getSelectedBusinessProduct();

        String name = "";
        switch (SessionManager.getAccount().getLang()) {
            case RU:
                name = selectedBusinessProduct.getDescRu();
                break;
            case KK:
                name = selectedBusinessProduct.getDescKz();
                break;
        }
        view.setProductDescUrl(name);
    }


    @Override
    public void onServiceSelected(BusinessProduct selectedBusinessProduct) {
//        getView().getUI().getSession().setAttribute(SessionAttributes.SELECTED_SERVICE.name(), q);
//        selectedBusinessProduct = q;
        setSelectedBusinessProduct(selectedBusinessProduct);
        eventBus.post(EventType.PRODUCT_SELECTED);
    }

    @Override
    public void enterOrderForm(OrderIvrFormViewImpl formView) {

        log.info("SHAREBONUS for {} is {}.",
                SessionManager.getAccount().getUser().getName(),
                SessionManager.isAccessGroupAvailable(AccessGroup.SHAREBONUS)?"available": "not available");

        formView.setVisibleShareBonus(SessionManager.isAccessGroupAvailable(AccessGroup.SHAREBONUS));

        formView.setMsisdn("");
        BusinessProduct selectedBusinessProduct = getSelectedBusinessProduct();

        if (selectedBusinessProduct == null) {
            eventBus.post(EventType.ENTER_SUBSCRIBE_IVR_FORM_IMPOSSIBLE);
            return;
        }

        String name = "";
        switch (SessionManager.getAccount().getLang()) {
            case RU:
                name = selectedBusinessProduct.getNameRu();
                break;
            case KK:
                name = selectedBusinessProduct.getNameKz();
                break;
        }
        formView.setServiceName(name);
        formView.setBonus($(product_subscribe_form_bonus, Format.edFormat.format(selectedBusinessProduct.getBonus())));

        formView.getPriceLabel().setVisible(false);
        if (selectedBusinessProduct.getDuration() != null && selectedBusinessProduct.getDuration() == 1) {
            formView.getPriceLabel().setValue($(product_subscribe_form_price_label_1, Format.edFormat.format(selectedBusinessProduct.getProductCost())));
            formView.getPriceLabel().setVisible(true);
        } else {
            formView.getPriceLabel().setValue($(product_subscribe_form_price_label, Format.edFormat.format(selectedBusinessProduct.getProductCost())));
            formView.getPriceLabel().setVisible(true);
        }

        formView.getMbPlusCostLabel().setVisible(false);
        if (selectedBusinessProduct.getMbPlusCost() != null && selectedBusinessProduct.getMbPlusCost() > 0) {
            formView.getMbPlusCostLabel().setValue($(product_subscribe_form_MbPlusCostLabel_caption, Format.edFractionFormat.format(selectedBusinessProduct.getMbPlusCost())));
            formView.getMbPlusCostLabel().setVisible(true);
        }


        String detailInfo = "";
        switch (SessionManager.getAccount().getLang()) {
            case RU:
                detailInfo = selectedBusinessProduct.getDetailInfoRu();
                break;
            case KK:
                detailInfo = selectedBusinessProduct.getDetailInfoKk();
                break;
        }

        if (StringUtils.isNoneEmpty(detailInfo)) {
            formView.getDetailInfo().setValue(detailInfo);
            formView.getDetailInfo().setVisible(true);
        } else {
            formView.getDetailInfo().setVisible(false);
        }


        String descrUrl = "";
        switch (SessionManager.getAccount().getLang()) {
            case RU:
                descrUrl = selectedBusinessProduct.getDescRu();
                break;
            case KK:
                descrUrl = selectedBusinessProduct.getDescKz();
                break;
        }

        formView.getShowDescrLink().setValue(String.format(
                "<a href='%s' target='_blank'>%s</a>"
                , descrUrl
                , $(product_subscribe_form_descr_btn_caption)));
    }

    @Override
    public String getProductName(BusinessProduct q) {
        String appendix = "";
        if (SessionManager.isAccessGroupAvailable(AccessGroup.DEBUG)) {
            appendix = String.format("%s / %s [%s]: <br />", q.getId(), q.getHumanCode(), q.getAccessGroup());
        }

        return appendix + PresenterHelper.buildLongProductDescr(q);
    }

    @Override
    public String getCtgrTitle(BusinessProductCtgr ctgr) {
        Account account = SessionManager.getAccount();

        String title = "";
        switch (account.getLang()) {
            case RU: {
                title = ctgr.getNameRu();
                break;
            }
            case KK: {
                title = ctgr.getNameKz();
                break;
            }
        }

        if (SessionManager.isAccessGroupAvailable(AccessGroup.DEBUG)) {
            title = ctgr.getId() + ": " + title;
        }

        return title;
    }

    @Override
    public void backFromProductDesc() {
        eventBus.post(EventType.PRODUCT_SELECTED);
    }

    @Override
    public void onSubscribe() throws BaseException, InvalidValueException {
        BusinessProduct selectedBusinessProduct = getSelectedBusinessProduct();

        EmptyStringValidator.validate($(product_subscribe_form_validate_msg1), orderFormView.getMsisdn());
        MsisdnValidator.validate($(product_subscribe_form_validate_msg2), orderFormView.getMsisdn());

//        subscriberMsisdn = new FastMsisdn(orderFormView.getMsisdn());
        setSubscriberMsisdn(new FastMsisdn(orderFormView.getMsisdn()));

        log.info("subscribe {} on {}:\"{}\"",
                getSubscriberMsisdn(), selectedBusinessProduct.getId(), selectedBusinessProduct.getNameEn());


        Long shareBonusInt = Long.valueOf(
                StringUtils.isBlank(orderFormView.getShareBonus())
                        ?"0"
                        :orderFormView.getShareBonus()
        );

//        if (shareBonusInt > 0 && SessionManager.isAccessGroupNotavailable(AccessGroup.SHAREBONUS) == false) {
//            throw new BusinessException(SPMOT_BusinessExceptionCodeEnum.SHAREBONUS_FACILITY_DENIED);
//            throw new AccessControlException("Sharebonus facilty not available for " +
//                    SessionManager.getAccount().getDealer().getMsisdn());
//        }


//        setSubscribe(new Subscribe());
        log.info("subscribed {}", getSubscribe().toString());
        eventBus.post(EventType.SUBSCRIBE_SERVICE);
    }


    @Override
    public void enterOrderIvr(OrderIvrViewImpl ivrView) {
        log.info("SHAREBONUS for {} is {}.",
                SessionManager.getAccount().getUser().getName(),
                SessionManager.isAccessGroupAvailable(AccessGroup.SHAREBONUS)?"available": "not available");

        ivrView.setShareBonusVisible(SessionManager.isAccessGroupAvailable(AccessGroup.SHAREBONUS));

        BusinessProduct selectedBusinessProduct = getSelectedBusinessProduct();

        String name = "";
        if (selectedBusinessProduct == null) {
            eventBus.post(EventType.ENTER_SUBSCRIBE_IVR_VIEW_IMPOSSIBLE);
            return;
        }
        switch (SessionManager.getAccount().getLang()) {
            case RU:
                name = selectedBusinessProduct.getNameRu();
                break;
            case KK:
                name = selectedBusinessProduct.getNameKz();
                break;
        }

        ivrView.setTitle("");
        ivrView.setIvr("");
        ivrView.setInfo("");
        ivrView.setMsisdn($(product_ivr_view_msisdn, getSubscriberMsisdn().format(Msisdn.Format.CANONICAL_PLUS)));

        ivrView.setServiceName(name);
        ivrView.setBonus($(product_ivr_view_bonus, Format.edFormat.format(selectedBusinessProduct.getBonus())));
        ivrView.setShareBonus($(product_ivr_view_sharebonus, Format.edFormat.format(getSubscribe().getShareBonus())));
//        String title = "Подписка оформленна";
        try {

            if (SessionManager.getAccount().getMsisdn().get().equals(getSubscribe().getDealer()) == false) {
                ivrView.showNotification($(product_ivr_view_other_dealer_gen_code), Notification.Type.HUMANIZED_MESSAGE);
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }

        String title = $(product_ivr_view_title);
        String ivrText = $(product_ivr_view_ivr, getSubscribe().getIvrCode());
        try {
            if (getSubscribe().getStartDate() != null) {
                ivrView.setReleaseDate($(product_ivr_view_release, getSubscribe().getStartDate().toLocalDateTime().format(Format.dateTimeFormat)));

            }
        } catch (Exception e) {
            title = $(product_ivr_view_reject_title);
            ErrorHandler.handle(e);
        }
        ivrView.setTitle(title);
        ivrView.setIvr(ivrText);
        ivrView.setInfo($(product_ivr_view_info, getSubscribe().getIvrCode()));

    }

    @Override
    public void onSubmit(EventObject source) {
        eventBus.post(EventType.PRODUCT_SELECTED);
    }

    @Override
    public void display(UI ui) {
//        ui.setContent(view);
//        view.showProductsScreen();
    }

    @Override
    protected ProductNavigatorView getView() {
        return view;
    }

    @Override
    public void translate() {
        view.translate();
        orderFormView.translate();
        orderIvrView.translate();
    }

    public Subscribe getSubscribe() {
        return (Subscribe) UI.getCurrent().getSession().getAttribute("subscribe");
    }

    public void setSubscribe(Subscribe subscribe) {
//        this.subscribe = subscribe;
        UI.getCurrent().getSession().setAttribute("subscribe", subscribe);
    }

    public FastMsisdn getSubscriberMsisdn() {
        return (FastMsisdn) UI.getCurrent().getSession().getAttribute("subscriberMsisdn");
//        return subscriberMsisdn;
    }

    public void setSubscriberMsisdn(FastMsisdn subscriberMsisdn) {
        UI.getCurrent().getSession().setAttribute("subscriberMsisdn", subscriberMsisdn);
//        this.subscriberMsisdn = subscriberMsisdn;
    }

    public BusinessProduct getSelectedBusinessProduct() {
        return (BusinessProduct) UI.getCurrent().getSession().getAttribute("selectedBusinessProduct");
//        return selectedBusinessProduct;
    }

    public void setSelectedBusinessProduct(BusinessProduct selectedBusinessProduct) {
        UI.getCurrent().getSession().setAttribute("selectedBusinessProduct", selectedBusinessProduct);

//        this.selectedBusinessProduct = selectedBusinessProduct;
    }
}
