package kz.kcell.apps.spmot.mobile.vaadin.controller;

import kz.kcell.apps.spmot.mobile.vaadin.annotation.SpringPresenter;
import kz.kcell.apps.spmot.mobile.vaadin.ui.view.MainMenu;
import kz.kcell.vaadin.ui.EventBus;
import kz.kcell.vaadin.ui.EventType;
import kz.kcell.vaadin.ui.Presenter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 23 10 2014
 */
@SpringPresenter
public class MainMenuPresenter extends AbstractPresenter<MainMenu> implements Presenter, MainMenu.Listener {

    @Autowired @Getter
    private MainMenu view;

    @Autowired
    private EventBus eventBus;

    @Override
    public void onChooseMenuItem(EventType eventType) {
        eventBus.post(eventType);
    }

    @Override
    public void translate() {
//        view.translate();
    }

}
