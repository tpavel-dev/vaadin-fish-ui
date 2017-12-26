package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kz.kcell.app.bonus_cmdr.ws.stub.BonusAssigmentState;
import kz.kcell.app.bonus_cmdr.ws.stub.BonusParams;
import kz.kcell.app.bonus_cmdr.ws.stub.Company;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompanyView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private HorizontalLayout bonusInfoLayout;
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");


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
        bonusInfoLayout = new HorizontalLayout();
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
                .add(grid.addColumn(company -> dateFormat
                        .format(company.getAllowanceStartDate().toGregorianCalendar().getTime()))
                        .setCaption("Start date"));
        collapsibleColumns
                .add(grid.addColumn(company -> dateFormat
                        .format(company.getAllowanceEndDate().toGregorianCalendar().getTime()))
                        .setCaption("End date"));
        collapsibleColumns
                .add(grid.addColumn(BonusParams::getStatus).setCaption("Status"));

        grid.addComponentColumn(bonus -> addBonusAction(bonus));

        grid.addSelectionListener(event -> {
            addSelectionEvent(event);
        });
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

    private void addSelectionEvent(SelectionEvent<BonusParams> event) {
        BonusParams bonus = event.getFirstSelectedItem().get();
        bonusInfoLayout.removeAllComponents();
        bonusInfoLayout.addComponent(buildBonusInfo(bonus));
    }

    private HorizontalLayout buildBonusInfo(BonusParams bonus) {
        HorizontalLayout hr = new HorizontalLayout();
        hr.setWidth(100, Unit.PERCENTAGE);

        Panel kcellAllowancePanel = new Panel("Kcell Description");
        kcellAllowancePanel.setSizeUndefined();
        hr.addComponent(kcellAllowancePanel);

        FormLayout kcellAllowance = new FormLayout();
        kcellAllowance.addComponent(textField("Allowance ID", bonus.getAllowanceId()));
        kcellAllowance.addComponent(textField("Quota", String.valueOf(bonus.getAllowanceQuota())));
        kcellAllowance.addComponent(textField("Start date", dateFormat.format(bonus.getAllowanceStartDate().toGregorianCalendar().getTime())));
        kcellAllowance.addComponent(textField("End date", dateFormat.format(bonus.getAllowanceEndDate().toGregorianCalendar().getTime())));
        kcellAllowance.setSizeUndefined();
        kcellAllowance.setMargin(true);
        kcellAllowancePanel.setContent(kcellAllowance);

        Panel activAllowancePanel = new Panel("Activ Description");
        activAllowancePanel.setSizeUndefined();
        hr.addComponent(activAllowancePanel);

        FormLayout activAllowance = new FormLayout();
        activAllowance.addComponent(textField("Pocket name", bonus.getOrgaPocketName()));
        activAllowance.addComponent(textField("Balance name", bonus.getOrgaBalanceName()));
        activAllowance.addComponent(textField("Comment", bonus.getOrgaComment()));
        activAllowance.addComponent(textField("Amount", String.valueOf(bonus.getOrgaAmount())));
        activAllowance.addComponent(textField("Start date", dateFormat.format(bonus.getOrgaStartDate().toGregorianCalendar().getTime())));
        activAllowance.addComponent(textField("End date", dateFormat.format(bonus.getOrgaExpDate().toGregorianCalendar().getTime())));

        activAllowance.setSizeUndefined();
        activAllowance.setMargin(true);
        activAllowancePanel.setContent(activAllowance);

        Panel state = new Panel("State");
        state.setSizeUndefined();
        hr.addComponent(state);

        BonusAssigmentState assignmentState = listener.getBonusAssigmentState(bonus);
        //Test
        assignmentState.setStart(XMLGregorianCalendarImpl.createDate(2017,12,24,6));
        assignmentState.setFinish(XMLGregorianCalendarImpl.createDate(2017,12,27,6));

        FormLayout stateInfo = new FormLayout();
        stateInfo.addComponent(textField("All counts", String.valueOf(assignmentState.getAllCountSet())));
        stateInfo.addComponent(textField("Complated counts", String.valueOf(assignmentState.getComplatedCount())));
        stateInfo.addComponent(textField("Failed counts", String.valueOf(assignmentState.getFailedCount())));
        stateInfo.addComponent(textField("Start date", dateFormat.format(assignmentState.getStart().toGregorianCalendar().getTime())));
        stateInfo.addComponent(textField("End date", dateFormat.format(assignmentState.getFinish().toGregorianCalendar().getTime())));

        stateInfo.setSizeUndefined();
        stateInfo.setMargin(true);
        state.setContent(stateInfo);

        return hr;
    }

    private TextField textField(String caption, String val) {
        TextField field = new TextField(caption);
        field.setValue(val == null ? "" : val);
        field.setEnabled(false);
        return field;
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
        content.addComponent(bonusInfoLayout);

//        content.addComponent(optionGroup);
    }


    public void translate() {
        setTitle("Компания: " + company.getName());
    }

}
