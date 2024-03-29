package kz.kcell.apps.fish.mobile.vaadin.ui.view;

import kz.kcell.vaadin.ui.View;
import kz.kcell.vaadin.ui.behaviors.SubmitBehavior;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 17 09 2014
 */
public interface LogoutView extends View<LogoutView.Listener> {

    /**
     * @author Pavel.Terechshenkov@kcell.kz
     * @since 17 09 2014
     */
    interface Listener extends SubmitBehavior {
        void setView(LogoutView components);
    }
}
