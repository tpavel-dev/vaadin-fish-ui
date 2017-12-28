package kz.kcell.apps.fish.mobile.vaadin.controller;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import kz.kcell.app.bonus_cmdr.ws.stub.*;
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
        companyService.startAssigmentBonus(bonusParams.getCid(), bonusParams.getBid());
    }

    @Override
    public void stopBonusJob(BonusParams bonusParams) {
        companyService.stopAssigmentBonus(bonusParams.getCid(), bonusParams.getBid());
    }

    @Override
    public BonusAssigmentState getBonusAssigmentState(BonusParams bonusParams) {
        return companyService.getAssigmentState(bonusParams.getCid(), bonusParams.getBid());
    }

    @Override
    public void updateBonus(BonusParams bonusParams) {
        companyService.updateBonus(bonusParams);
    }

    @Override
    public void showCompaniesView() {
        eventBus.post(EventType.SHOW_COMPANIES_SCREEN);
    }

    @Override
    public User getCurrentUser() {
        return getAccount().getUser();
    }

    @Override
    public void removeBonus(BonusParams bonusParams) {
        companyService.removeBonus(bonusParams);
    }

    @Override
    public Company getCompany() {
        return VaadinSession.getCurrent().getAttribute(Company.class);
    }

    @Override
    public void saveBonus(BonusParams bonusParams) {
        companyService.addBonus(bonusParams);
    }

    @Override
    public List<String> getFileNames() {
        return companyService.getListFile();
    }

    @Override
    public void uploadFile(String fileName, Long cid) {
        companyService.updloadFile(cid, fileName);
    }

    @Override
    public void display(UI ui) {
//        ui.setContent(view);
    }

    @Override
    public void buildCompany(Long cid) {
        companyService.buildCompanyStatus(cid);
    }

    @Override
    public void clearCompany(Long cid) {
        companyService.clearCompany(cid);
    }

    @Override
    public void translate() {
        view.translate();
    }

}
