package kz.kcell.apps.fish.mobile.vaadin.ui.view.window;

import com.vaadin.ui.*;
import kz.kcell.app.bonus_cmdr.ws.stub.Company;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompanyView;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SelectFileMsisdnWindow extends Window {

    private CompanyView.Listener listener;
    private Company company;

    private List<String> fileNames;

    public SelectFileMsisdnWindow(CompanyView.Listener listener, Company company) {
        this.listener = listener;
        this.company = company;
        init();
    }

    private void init() {
        fileNames = listener.getFileNames();

        center();
        setWidth(240, Unit.PIXELS);
        buildLayout();
    }

    private void buildLayout() {
        VerticalLayout selectMsisdnFileLayout = new VerticalLayout();
        selectMsisdnFileLayout.setSpacing(true);
        selectMsisdnFileLayout.setMargin(true);

        ComboBox files = new ComboBox("File names", fileNames);
        files.setEmptySelectionAllowed(false);
        selectMsisdnFileLayout.addComponent(files);

        Button bindBtn = new Button("Save");
        bindBtn.addClickListener(e -> {
            if (files.getValue() == null) {
                Notification.show("File is not selected", Notification.Type.WARNING_MESSAGE);
                return;
            }
            listener.uploadFile((String) files.getValue(), company.getCid());
            close();
            Notification.show("File binded successfully!", Notification.Type.HUMANIZED_MESSAGE);
        });
        selectMsisdnFileLayout.addComponent(bindBtn);

        setContent(selectMsisdnFileLayout);
    }

}
