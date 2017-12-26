package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kz.kcell.app.bonus_cmdr.ws.stub.BonusParams;
import kz.kcell.app.bonus_cmdr.ws.stub.Company;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompanyView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@SpringView(name = ViewsCode.name_company)
public class CompanyViewImpl extends BaseNavigationView implements CompanyView {
    public static final String VIEW_NAME = "company";

    private Company company;
    private List<BonusParams> bonusParamsList;

    private Grid<BonusParams> grid;
    private static final Set<Grid.Column<BonusParams, ?>>
            collapsibleColumns = new LinkedHashSet<>();

    @Autowired
    @Getter
    private CompanyView.Listener listener;

    public CompanyViewImpl() {
    }

    @PostConstruct
    public void init() {
//        listener.setView(this);
        super.init();
    }

    @Override
    protected void injectInit() {
        company = listener.getCompany();
        bonusParamsList = listener.getBonusParamsByCompanyId(company.getCid());
    }

    private Grid<BonusParams> buildGrid() {
        grid = new Grid<>();
        grid.setWidth(100, Unit.PERCENTAGE);
        collapsibleColumns
                .add(grid.addColumn(BonusParams::getBid).setCaption("BID"));
        collapsibleColumns
                .add(grid.addColumn(BonusParams::getAllowanceName).setCaption("Name"));
        collapsibleColumns
                .add(grid.addColumn(BonusParams::getAllowanceDescr).setCaption("Description"));
        collapsibleColumns
                .add(grid.addColumn(BonusParams::getExeOrder).setCaption("Order"));
        collapsibleColumns
                .add(grid.addColumn(BonusParams::getAllowanceId).setCaption("Allowance ID"));
        collapsibleColumns
                .add(grid.addColumn(BonusParams::getAllowanceQuota).setCaption("Quota"));
        collapsibleColumns
                .add(grid.addColumn(BonusParams::getAllowanceStartDate).setCaption("Start date"));
        collapsibleColumns
                .add(grid.addColumn(BonusParams::getAllowanceEndDate).setCaption("End date"));
        collapsibleColumns
                .add(grid.addColumn(BonusParams::getStatus).setCaption("Status"));

        grid.addComponentColumn(bonus -> addBonusAction(bonus));

        grid.setItems(bonusParamsList);

        return grid;
    }

    private Component addBonusAction(BonusParams bonus) {
        Button actionBtn = new Button();
        switch (bonus.getStatus()) {
            case STOPED:
                actionBtn.setCaption("Start");
                actionBtn.addClickListener(event -> {
                    listener.startBonusJob(bonus);
                    refreshTable();
                });
                break;
            case RUNNING:
                actionBtn.setCaption("Stop");
                actionBtn.addClickListener(event -> {
                    listener.stopBonusJob(bonus);
                    refreshTable();
                });
                break;
            case COMPLATED: return null;
        }
        return actionBtn;
    }

    private void refreshTable() {
        grid.setItems(bonusParamsList);
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

        HorizontalLayout h = new HorizontalLayout();
        h.setWidth(100, Unit.PERCENTAGE);
        h.addComponent(buildGrid());
        h.setSpacing(false);
        h.setMargin(false);

        content.addComponent(h);

//        content.addComponent(optionGroup);
    }


    public void translate() {
        setTitle("Компания: " + company.getName());
    }

}
