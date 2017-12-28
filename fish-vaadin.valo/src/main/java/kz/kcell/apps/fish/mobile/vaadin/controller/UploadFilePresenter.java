package kz.kcell.apps.fish.mobile.vaadin.controller;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import kz.kcell.app.bonus_cmdr.ws.stub.*;
import kz.kcell.apps.fish.mobile.vaadin.annotation.SpringPresenter;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompanyView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.UploadFileView;
import kz.kcell.vaadin.ui.EventBus;
import kz.kcell.vaadin.ui.EventType;
import kz.kcell.vaadin.ui.Presenter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@SpringPresenter
public class UploadFilePresenter extends AbstractPresenter<UploadFileView> implements Presenter, UploadFileView.Listener {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private CompanyService companyService;

    @Setter
    private UploadFileView view;

    @Override
    protected UploadFileView getView() {
        return view;
    }

    @Override
    public User getCurrentUser() {
        return getAccount().getUser();
    }

    @Override
    public void translate() {

    }
}
