package kz.kcell.apps.fish.mobile.vaadin.controller;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import kz.kcell.apps.bonus_cmdr.model.MasterConfig;
import kz.kcell.apps.fish.mobile.vaadin.annotation.SpringPresenter;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.UploadFileView;
import kz.kcell.vaadin.ui.EventBus;
import kz.kcell.vaadin.ui.Presenter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;

@Slf4j
@SpringPresenter
public class UploadFilePresenter
        extends AbstractPresenter<UploadFileView>
        implements Presenter, UploadFileView.Listener {

    @Autowired
    private MasterConfig config;

    @Autowired
    private EventBus eventBus;

    @Setter
    private UploadFileView view;

    @Override
    protected UploadFileView getView() {
        return view;
    }

    @Override
    public void translate() {

    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        try {
            return new FileOutputStream(Paths.get(config.getDataDirName(), filename).toString());
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            Notification.show("Неудается найти файл. ", e.getMessage(), Notification.Type.ERROR_MESSAGE);

        } catch (IOException e) {
            log.error(e.getMessage());
            Notification.show("Ошибка загрузки файл. ", e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public void uploadFinished(Upload.FinishedEvent event) {

        Notification.show("File uploaded Successfully!");
    }

}
