package kz.kcell.apps.fish.mobile.vaadin.ui.view.window;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Window;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Slf4j
public class UploadMsisdnWindow extends Window {

    public UploadMsisdnWindow() {
        init();
    }

    private void init() {
        center();
        setWidth(400, Unit.PIXELS);
        buildLayout();
    }

    private void buildLayout() {
        HorizontalLayout uploadLayout = new HorizontalLayout();
        uploadLayout.setSpacing(true);
        uploadLayout.setMargin(true);

        Upload upload = new Upload("Upload msisdn set here", (Upload.Receiver) (filename, mimeType) -> {
            try {
                OutputStream outputStream = new FileOutputStream(filename);
                return outputStream;
            } catch (FileNotFoundException e) {
                log.error(e.getMessage());
            }
            return null;
        });
        upload.setImmediateMode(false);
        upload.addFinishedListener(e -> {
            close();
            UI.getCurrent().showNotification("File uploaded Successfully!");
        });
        uploadLayout.addComponent(upload);
        setContent(uploadLayout);
    }

}
