package kz.kcell.apps.fish.mobile.vaadin.ui.view;

import kz.kcell.vaadin.ui.View;
import kz.kcell.vaadin.ui.behaviors.SubmitBehavior;

import java.util.EventObject;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 17 09 2014
 */
public interface SelectProductView extends View<SelectProductView.SelectProductViewListener>, SubmitBehavior {
    /**
     * @author Pavel.Terechshenkov@kcell.kz
     * @since 17 09 2014
     */
    interface SelectProductViewListener  {
        void subscribe(EventObject source);
    }
}
