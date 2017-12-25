package kz.kcell.apps.fish.mobile.vaadin.controller;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import kz.kcell.app.bonus_cmdr.ws.stub.Company;
import kz.kcell.app.bonus_cmdr.ws.stub.CompanyService;
import kz.kcell.apps.fish.mobile.vaadin.annotation.SpringPresenter;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompanyView;
import kz.kcell.vaadin.ui.EventBus;
import kz.kcell.vaadin.ui.EventType;
import kz.kcell.vaadin.ui.Presenter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    public List<Company> getAllCompanies() {
        return companyService.getCompanyAll();
    }

    @Override
    public void setView(CompanyView components) {

    }

    @Override
    public void onRowClick(SelectionEvent event) {
        Company company = (Company) event.getFirstSelectedItem().get();
        if (company == null) {
            log.error("row selection failed");
            return;
        }
        VaadinSession.getCurrent().setAttribute(Company.class, company);
        eventBus.post(EventType.SHOW_COMPANY_SCREEN);
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
