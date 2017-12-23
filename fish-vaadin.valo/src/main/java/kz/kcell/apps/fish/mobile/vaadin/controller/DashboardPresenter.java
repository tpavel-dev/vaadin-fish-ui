package kz.kcell.apps.fish.mobile.vaadin.controller;

import kz.kcell.apps.fish.mobile.vaadin.annotation.SpringPresenter;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.DashboardView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import kz.kcell.vaadin.ui.Presenter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 18 09 2014
 */
@Slf4j
@SpringPresenter
public class DashboardPresenter extends AbstractPresenter<DashboardView> implements Presenter, DashboardView.DashboardViewListener {

    @Getter @Setter
    DashboardView view;

    @Autowired
    MainUIPresenter mainUIPresenter;


    @Override
    public void translate() {
        view.translate();
    }

    @Override
    public void onClick(ViewsCode state) {
        mainUIPresenter.onClick(state);
    }
}
