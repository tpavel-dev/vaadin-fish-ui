package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompanyView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.UploadFileView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Slf4j
@SpringView(name = ViewsCode.name_upload_file)
public class UploadFileViewImpl extends BaseNavigationView implements UploadFileView {
    public static final String VIEW_NAME = "upload_file";

    @Autowired
    @Getter
    private UploadFileView.Listener listener;

    public UploadFileViewImpl() {
    }

    @PostConstruct
    public void init() {
        super.init();
    }

    @Override
    protected void injectInit() {

    }

    @Override
    protected void initIds() {
        super.initIds();
    }

    @Override
    protected void buildLayout() {
        super.buildLayout();
        content.setSizeUndefined();
        content.setWidth(100, Unit.PERCENTAGE);
        content.setSpacing(true);

        HorizontalLayout uploadLayout = new HorizontalLayout();
        uploadLayout.setSpacing(true);
        uploadLayout.setMargin(true);

        Upload upload = new Upload("Upload msisdn set here", listener);

        upload.setImmediateMode(false);
        upload.addFinishedListener(listener);
        uploadLayout.addComponent(upload);

        content.addComponent(uploadLayout);
    }

}
