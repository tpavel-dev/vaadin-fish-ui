package kz.kcell.apps.fish.mobile.vaadin.ui.view;

import kz.kcell.vaadin.ui.View;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 17 09 2014
 */
public interface DashboardView extends View<DashboardView.DashboardViewListener> {

    /**
     * @author Pavel.Terechshenkov@kcell.kz
     * @since 17 09 2014
     */
    interface DashboardViewListener {
        void onClick(ViewsCode state);

        void setView(DashboardView components);
//        void submit(ViewChangeListener.ViewChangeEvent viewChangeEvent);
    }
}

