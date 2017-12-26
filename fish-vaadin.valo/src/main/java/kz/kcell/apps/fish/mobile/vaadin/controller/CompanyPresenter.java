package kz.kcell.apps.fish.mobile.vaadin.controller;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import kz.kcell.app.bonus_cmdr.ws.stub.BonusAssigmentState;
import kz.kcell.app.bonus_cmdr.ws.stub.BonusParams;
import kz.kcell.app.bonus_cmdr.ws.stub.Company;
import kz.kcell.app.bonus_cmdr.ws.stub.CompanyService;
import kz.kcell.apps.fish.mobile.vaadin.annotation.SpringPresenter;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompaniesView;
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
    public List<BonusParams> getBonusParamsByCompanyId(Long cid) {
        return companyService.getBonusParams(cid);
    }

    @Override
    public void onRowClick(SelectionEvent event) {

    }

    @Override
    public void startBonusJob(BonusParams bonusParams) {
        companyService.startAssigmentBonus(bonusParams.getCid(), bonusParams.getBid().intValue());
    }

    @Override
    public void stopBonusJob(BonusParams bonusParams) {
        companyService.stopAssigmentBonus(bonusParams.getCid(), bonusParams.getBid().intValue());
    }

    @Override
    public BonusAssigmentState getBonusAssigmentState(BonusParams bonusParams) {
        return companyService.getAssigmentState(bonusParams.getCid(), bonusParams.getBid().intValue());
    }

    @Override
    public Company getCompany() {
        return VaadinSession.getCurrent().getAttribute(Company.class);
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
