package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import kz.kcell.apps.fish.mobile.vaadin.ui.ErrorHandler;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ErrorView;

import javax.ws.rs.NotSupportedException;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 29 10 2014
 */
//@SpringView(name = ViewsCode.name_error)
@SpringComponent
@UIScope
public class ErrorViewImpl extends BaseView implements View, ErrorView{
    private Label notificationLabel = new Label();

    public ErrorViewImpl() {
//        super(1, 1);
        Label label = new Label("Здесь отображаются ошибки");
        addComponents(label, notificationLabel);
        setComponentAlignment(label, Alignment.MIDDLE_CENTER);
    }

    @Override
    public ErrorView.Listener getListener() {
        throw new NotSupportedException();
//        return null;
    }

    @Override
    public void showNotification(String msg, Notification.Type typeMsg) {
        ErrorHandler.showNotification(notificationLabel, msg, typeMsg);
    }

    @Override
    public void translate() {

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
