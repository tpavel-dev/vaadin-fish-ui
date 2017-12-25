package kz.kcell.apps.fish.mobile.vaadin.controller;

import com.vaadin.ui.UI;
import kz.kcell.app.bonus_cmdr.ws_api.CompanyService;
import kz.kcell.app.bonus_cmdr.model.Company;
import kz.kcell.apps.fish.mobile.vaadin.annotation.SpringPresenter;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompanyView;
import kz.kcell.vaadin.ui.EventBus;
import kz.kcell.vaadin.ui.Presenter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Slf4j
@SpringPresenter
public class CompanyPresenter extends AbstractPresenter<CompanyView> implements Presenter, CompanyView.Listener {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private CompanyService companyService;

    @Setter
    private CompanyView view;

    @Override
    protected CompanyView getView() {
        return view;
    }

    @Override
    public Collection<Company> getAllCompanies() {
        return companyService.getCompanyAll();
    }

    @Override
    public void setView(CompanyView components) {

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
