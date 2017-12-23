package kz.kcell.apps.fish.mobile.vaadin.ui.view;

import com.vaadin.navigator.ViewChangeListener;
import kz.kcell.apps.common.exceptions.BaseException;
import kz.kcell.vaadin.ui.View;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 23 10 2014
 */
public interface BonusesInfoView extends View<BonusesInfoView.Listener> {

    public abstract void setBonus(Double bonus);

    public interface Listener {
        void requestBonus() throws BaseException;

        void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent);

        void setView(BonusesInfoView components);
    }
}
