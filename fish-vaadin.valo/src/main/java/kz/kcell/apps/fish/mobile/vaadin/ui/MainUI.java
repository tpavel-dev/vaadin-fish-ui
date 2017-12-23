package kz.kcell.apps.fish.mobile.vaadin.ui;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import kz.kcell.apps.common.Language;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.impl.ValoMainUIImpl;

import javax.ws.rs.NotSupportedException;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 18 09 2014
 */

public abstract class MainUI extends UI {

    // TODO
    @Override
    public void showNotification(String msg, Notification.Type humanizedMessage) {
    }

    public abstract void translate();

    public abstract ComponentContainer getComponentContainer();

    public abstract void setFullScreen();

    public abstract void offFullScreen();

    public abstract void selectMenuItem(String viewName);

    public void setBonus(Double bonus) {
        throw new NotSupportedException();
    }

    public void setMsisdn(String msisdn) {
        throw new NotSupportedException();
    }

    public interface Listener {
        void onChangeLanguage(Language languageo);

        void initFerstState();

        void onClick(ViewsCode name);

        void showNotification(String msg, Notification.Type typeMsg);

        void setView(ValoMainUIImpl components);
    }


}
