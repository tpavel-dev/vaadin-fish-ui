package kz.kcell.apps.fish.mobile.vaadin.controller;

import com.vaadin.ui.UI;
import kz.kcell.apps.fish.mobile.vaadin.annotation.SpringPresenter;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.LogoutView;
import kz.kcell.vaadin.ui.EventBus;
import kz.kcell.vaadin.ui.EventType;
import kz.kcell.vaadin.ui.Presenter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.EventObject;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 24 09 2014
 */
@SpringPresenter
public class LogoutPresenter implements Presenter, LogoutView.Listener {

    @Setter
    private LogoutView view;

    @Autowired
    private EventBus eventBus;

    @Override
    public void onSubmit(EventObject source) {
        eventBus.post(EventType.LOGOUT_SUCCESS);
    }

    @Override
    public void display(UI ui) {
//        ui.setContent(view);
    }

    @Override
    public void translate() {
        view.translate();
    }

}
