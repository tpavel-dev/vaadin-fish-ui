package kz.kcell.apps.fish.mobile.vaadin.ui.view;

import com.vaadin.navigator.ViewChangeListener;
import kz.kcell.apps.common.Language;
import kz.kcell.vaadin.ui.View;
import kz.kcell.vaadin.ui.behaviors.SubmitBehavior;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 17 09 2014
 */
public interface LoginView extends View<LoginView.LoginViewListener> {


    public String getUserName();
    public String getPassword();
    public boolean isWithOutAuth();
    public boolean isRememberPassword();
    public void setRemeberPassword(boolean remeber);

    void setUserName(String s);

    void setPassword(String s);

    /**
     * @author Pavel.Terechshenkov@kcell.kz
     * @since 17 09 2014
     */
    interface LoginViewListener extends SubmitBehavior {
        void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent);

        void onChangeLang(Language language);

        void setView(LoginView components);
    }
}

