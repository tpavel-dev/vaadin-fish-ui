package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ProductNavigatorView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import kz.kcell.vaadin.ui.Style;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 29 10 2014
 */
@SpringView(name = ViewsCode.name_subscribeIvr)
public class OrderIvrViewImpl extends BaseNavigationView {

    private Label msisdnLabel        = new Label();
    private Label serviceNameLabel   = new Label();
    private Label ivrLabel           = new Label();
    private Label bonusLabel         = new Label();
    private Label sharebonusLabel    = new Label();
    private Label infoLabel          = new Label();
    private Label releaseDateLabel   = new Label();


    @Autowired @Getter
    private ProductNavigatorView.Listener listener;

    public OrderIvrViewImpl() {
    }

    @PostConstruct
    public void init() {
        listener.setOrderIvrView(this);
        super.init();
    }


    @Override
    protected void injectInit() {
        super.injectInit();
//        setTitle("Подписка");
        setTitle("");

        serviceNameLabel.setContentMode(ContentMode.HTML);
        msisdnLabel.setContentMode(ContentMode.HTML);
        ivrLabel.setContentMode(ContentMode.HTML);
        bonusLabel.setContentMode(ContentMode.HTML);
        sharebonusLabel.setContentMode(ContentMode.HTML);
        infoLabel.setContentMode(ContentMode.HTML);
        releaseDateLabel.setContentMode(ContentMode.HTML);
    }

    @Override
    protected void initIds() {
        super.initIds();
    }

    @Override
    protected void buildLayout() {
        super.buildLayout();

        content.setSizeUndefined();
        content.setMargin(new MarginInfo(false, true, false, true));
        content.setWidth(Style._100p);
        content.setSpacing(true);
//        Responsive.makeResponsive(content);

        serviceNameLabel.setWidth(Style._100p);
        bonusLabel.setWidth(Style._100p);
        sharebonusLabel.setWidth(Style._100p);
        ivrLabel.setWidth(Style._100p);
        msisdnLabel.setWidth(Style._100p);
        releaseDateLabel.setWidth(Style._100p);
        infoLabel.setWidth(Style._100p);

        addComponent(serviceNameLabel);
        addComponent(bonusLabel);
        addComponent(sharebonusLabel);
        addComponent(ivrLabel);
        addComponent(msisdnLabel);
        addComponent(releaseDateLabel);
        addComponent(infoLabel);
//        addComponent(getNotificationLabel());
//        getNotificationLabel().setValue("111");
//        getNotificationLabel().setVisible(true);


//        showNotification("test", Notification.Type.ASSISTIVE_NOTIFICATION);
    }

    public void setMsisdn(String msisdn) {
        msisdnLabel.setValue(msisdn);
    }

    public void setIvr(String ivr) {
        ivrLabel.setValue(ivr);
    }

    public void setServiceName(String serviceName) {
        serviceNameLabel.setValue(serviceName);
    }
    public void setBonus(String bonus) {
        bonusLabel.setValue(bonus);
    }
    public void setShareBonus(String bonus) {
        sharebonusLabel.setValue(bonus);
    }
    public void setShareBonusVisible(boolean visible) {
        sharebonusLabel.setVisible(visible);
    }



    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        super.enter(viewChangeEvent);
        listener.enterOrderIvr(this);
    }

    public void setInfo(String s) {
        infoLabel.setValue(s);
    }

    public void setReleaseDate(String s) {
        releaseDateLabel.setValue(s);
    }


/*
    @Override
    public void showNotification(String msg, Notification.Type typeMsg) {
        ErrorHandler.showNotification(notificationLabel, msg, typeMsg);
    }
*/

}
