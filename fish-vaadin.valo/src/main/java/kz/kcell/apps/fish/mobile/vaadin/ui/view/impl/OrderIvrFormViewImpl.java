package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import kz.kcell.apps.fish.mobile.vaadin.ui.ErrorHandler;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ProductNavigatorView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import kz.kcell.vaadin.HTML;
import kz.kcell.vaadin.HackUtils;
import kz.kcell.vaadin.ui.ClearFix;
import kz.kcell.vaadin.ui.Style;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static kz.kcell.apps.fish.domain.spmot.entity.SpmotResourceBundle.*;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 29 10 2014
 */
@SpringView(name = ViewsCode.name_subscribeForm)
public class OrderIvrFormViewImpl extends BaseNavigationView {

    private Button showDescrInBrowser;
    private Button showDescrInner;
    @Getter
    @Setter
    private Label showDescrLink;

    private Button submit;
    //    private MaskedTextField msisdnField;
    private TextField msisdnField;
    private Label serviceNameLabel;

    private TextField shareBonus;

    private Label bonusLabel;
    @Getter
    private Label priceLabel;
    @Getter
    private Label mbPlusCostLabel = new Label();
    @Getter
    private Label detailInfo = new Label("", ContentMode.HTML);


//    private Label notificationLabel = new Label();

    //    @Getter
//    private Label infoLabel;
    @Autowired
    @Getter
    private ProductNavigatorView.Listener listener;

    public OrderIvrFormViewImpl() {
    }

    public void setVisibleShareBonus(boolean visibble) {
        shareBonus.setVisible(visibble);
    }

    @PostConstruct
    public void init() {
        listener.setOrderFormView(this);
        super.init();
    }

    @Override
    public void translate() {
        super.translate();
        setTitle($(product_subscribe_form_title));
        msisdnField.setCaption($(product_subscribe_form_msisdn_field_caption));
        shareBonus.setCaption($(product_subscribe_form_sharebonus_field_caption));
        submit.setCaption($(product_subscribe_form_submit_btn_caption));
        showDescrInBrowser.setCaption($(product_subscribe_form_descr_btn_caption));
    }

    @Override
    protected void initIds() {
        super.initIds();
        setId($(product_subscribe_form_title));
        msisdnField.setId(product_subscribe_form_msisdn_field_caption.name());
        shareBonus.setId(product_subscribe_form_sharebonus_field_caption.name());
        submit.setId(product_subscribe_form_submit_btn_caption.name());
        showDescrInBrowser.setId(product_subscribe_form_descr_btn_caption.name());
    }

    private void hackInputType() {
        HackUtils.setInputType(product_subscribe_form_msisdn_field_caption.name(), HTML.input.type.tel);
        HackUtils.setInputType(product_subscribe_form_sharebonus_field_caption.name(), HTML.input.type.number);
    }

    @Override
    protected void injectInit() {
        setTitle($(product_subscribe_form_title));
        bonusLabel = new Label();
        serviceNameLabel = new Label();
        priceLabel = new Label();
//        notificationLabel.setVisible(false);
//        msisdnField = new MaskedTextField("Номер абонента","(###) ###-####");
        msisdnField = new TextField($(product_subscribe_form_msisdn_field_caption));
        shareBonus = new TextField($(product_subscribe_form_sharebonus_field_caption));
        shareBonus.setVisible(false);
                // todo restore
//        msisdnField.setInputPrompt(Config.INPUT_PROMPT_CELL);
/*
        msisdnField.addValidator(new NullValidator("необходимо ввести номер абонента", false));
        msisdnField.addValidator(new MsisdnValidator());
*/

        submit = new Button($(product_subscribe_form_submit_btn_caption));
        submit.addStyleName(ValoTheme.BUTTON_PRIMARY);
        submit.focus();
        submit.addClickListener(e -> {
            try {
                listener.onSubscribe();
            } catch (Exception exc) {
                ErrorHandler.handle(exc);
            }
        });

        showDescrInBrowser = new Button($(product_subscribe_form_descr_btn_caption), e -> listener.showDescriptionInBrowser());
        showDescrInBrowser.addStyleName(ValoTheme.BUTTON_LINK);

        showDescrInner = new Button($(product_subscribe_form_descr_btn_caption), e -> listener.showDescriptionInner());
        showDescrInner.addStyleName(ValoTheme.BUTTON_LINK);

        showDescrLink = new Label("", ContentMode.HTML);

    }

    @Override
    protected void buildLayout() {
        super.buildLayout();

        content.setSizeUndefined();
        content.setMargin(new MarginInfo(false, true, true, true));
        content.setSpacing(true);
        content.setWidth(Style._100p);

        msisdnField.setWidth(Style._100p);
        shareBonus.setWidth(Style._100p);
        bonusLabel.setWidth(Style._100p);
        serviceNameLabel.setWidth(Style._100p);
        mbPlusCostLabel.setWidth(Style._100p);
        priceLabel.setWidth(Style._100p);
        detailInfo.setWidth(Style._100p);


//        Responsive.makeResponsive(content);

//        content.addComponent(notificationLabel);

        // todo restore
        content.addComponent(serviceNameLabel);
        content.addComponent(priceLabel);
        content.addComponent(mbPlusCostLabel);
        content.addComponent(bonusLabel);

//        if(detailInfo.getValue() != null && detailInfo.getValue().length() > 0) {

        // todo restore
        content.addComponent(detailInfo);
//        }
        content.addComponent(msisdnField);
        content.addComponent(shareBonus);

        // version 1
/*
        HorizontalLayout h = new HorizontalLayout();
        h.addComponents(submit, showDescrInBrowser);
        h.setSpacing(true);
        content.addComponent(h);
*/
        // version 2

        content.addComponent(submit);
//        content.addComponent(showDescrInBrowser);
        content.addComponent(ClearFix.h(1));
        content.addComponent(showDescrLink);
//        content.addComponent(showDescrInner);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        super.enter(viewChangeEvent);
        hackInputType();
        listener.enterOrderForm(this);
    }

    public String getMsisdn() {
        return msisdnField.getValue();
    }

    public void setMsisdn(String s) {
        msisdnField.setValue(s);
    }

    public String getShareBonus() {
        // todo
        return shareBonus.getValue();
    }

    public void setShareBonus(String s) {
        shareBonus.setValue(s);
    }

    public void setServiceName(String serviceName) {
        serviceNameLabel.setValue(serviceName);
    }

    public void setBonus(String bonus) {
        bonusLabel.setValue(bonus);
    }

/*
    @Override
    public void showNotification(String msg, Notification.Type typeMsg) {
        ErrorHandler.showNotification(notificationLabel, msg, typeMsg);
    }
*/
}
