package kz.kcell.apps.fish.mobile.vaadin.ui.view;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.StreamResource;
import kz.kcell.vaadin.ui.View;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 02 06 2016
 */
public interface CaptchaView extends View<CaptchaView.CaptchaViewListener> {
    void setInputValue(String s);

    interface CaptchaViewListener {
        void submit(String value);

        void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent);

        void setView(CaptchaView components);

        StreamResource getCaptchaImg();
    }

}
