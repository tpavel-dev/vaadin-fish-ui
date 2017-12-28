package kz.kcell.apps.fish.mobile.vaadin.ui.view;

import kz.kcell.app.bonus_cmdr.ws.stub.User;
import kz.kcell.vaadin.ui.View;

public interface UploadFileView extends View<UploadFileView.Listener> {

    interface Listener {

        User getCurrentUser();

    }

}
