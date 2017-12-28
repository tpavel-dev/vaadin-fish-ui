package kz.kcell.apps.fish.mobile.vaadin.ui.view;

import com.vaadin.ui.Upload;
import kz.kcell.app.bonus_cmdr.ws.stub.User;
import kz.kcell.vaadin.ui.View;

public interface UploadFileView extends View<UploadFileView.Listener> {

    interface Listener extends Upload.Receiver, Upload.FinishedListener {

    }

}
