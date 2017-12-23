package kz.kcell.apps.spmot.mobile.vaadin.ui.view;

import kz.kcell.vaadin.ui.EventType;
import kz.kcell.vaadin.ui.View;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 23 10 2014
 */
public interface MainMenu extends View<MainMenu.Listener>  {

    public interface Listener {
        void onChooseMenuItem(EventType eventType);
    }
}
