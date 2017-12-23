package kz.kcell.apps.fish.mobile.vaadin.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 19 12 2014
 */
public class UnsupportedWebBrowserUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout v = new VerticalLayout();
        Label label = new Label("Этот браузер не потдерживается, необходимо установить Google Chrome <a href='https://play.google.com/store/apps/details?id=com.android.chrome&hl=ru'>Установить</a>", ContentMode.HTML);
        v.addComponent(label);
        setContent(v);
    }
}
