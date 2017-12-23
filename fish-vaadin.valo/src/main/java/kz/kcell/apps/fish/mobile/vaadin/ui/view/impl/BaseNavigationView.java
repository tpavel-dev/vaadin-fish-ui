package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 29 10 2014
 */
@Slf4j
public abstract class BaseNavigationView extends BaseView implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        log.debug(" -- ENTER -- "+getTitleLabel().getValue());
        clearNotification();
    }

}
