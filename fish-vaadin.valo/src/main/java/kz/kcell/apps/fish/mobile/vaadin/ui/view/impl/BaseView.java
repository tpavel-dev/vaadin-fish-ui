package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import kz.kcell.apps.fish.mobile.vaadin.ui.ErrorHandler;
import kz.kcell.vaadin.ui.Style;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.notification_label;
import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.title_label;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 29 10 2014
 */
@Slf4j
public abstract class BaseView extends VerticalLayout {
//public class BaseView extends CssLayout {

    VerticalLayout content;//= new VerticalLayout();

    @Getter
    private Label titleLabel;

    //    @Getter
    private Label notificationLabel = new Label();

/*
    public BaseView() {
        init();
    }
*/

    public void init() {
        titleLabel = new Label("");
        content = this;
        notificationLabel.setVisible(false);
        injectInit();
        buildLayout();
        translate();
        initIds();
//        log.debug(" -- INIT -- "+titleLabel.getValue());
    }

    protected void injectInit() {

    }


    protected void initIds() {
        notificationLabel.setId(notification_label.name());
        titleLabel.setId(title_label.name());
    }

    ;

    protected void  buildLayout() {
        setSizeFull();
        addStyleName("content-common");
//        setWidth(Style._100p);
//        setSpacing(true);
//        setMargin(true);
//        setMargin(new MarginInfo(false, true, false, true));
        content.setMargin(new MarginInfo(false, false, false, true));

        titleLabel.addStyleName("h3");
        titleLabel.setWidth(Style._100p);
        addComponents(titleLabel, notificationLabel);
    }

    BaseView getContent() {
        return this;
    }

    public void setTitle(String title) {
        titleLabel.setValue(title);
    }

    //    @Override
    public void showNotification(String msg, Notification.Type typeMsg) {
        ErrorHandler.showNotification(notificationLabel, msg, typeMsg);
//            Notification.show(msg, typeMsg);
    }

    public void notifyUser(String msg, Notification.Type type) {
        Notification.show(msg, type);
    }

    public void clearNotification() {
        ErrorHandler.showNotification(notificationLabel, null, null);
    }

    public void translate() {

    }
}
