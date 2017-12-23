package kz.kcell.apps.fish.mobile.vaadin.ui.view;

import kz.kcell.vaadin.ui.View;
import kz.kcell.vaadin.ui.behaviors.SubmitBehavior;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 29 10 2014
 */
public interface ErrorView extends View<ErrorView.Listener> {
    interface Listener extends SubmitBehavior {
    }
}
