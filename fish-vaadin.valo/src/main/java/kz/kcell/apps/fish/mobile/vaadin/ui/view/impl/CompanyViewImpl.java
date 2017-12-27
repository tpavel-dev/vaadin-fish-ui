package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
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
import java.util.*;

@SpringView(name = ViewsCode.name_company)
public class CompanyViewImpl extends BaseNavigationView implements CompanyView {
    public static final String VIEW_NAME = "company";

    private Company company;
    private BonusParams selectedBonus;
    private List<BonusParams> bonusParamsList;

    private Grid<BonusParams> grid;

    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    private TextField name;
    private TextField description;
    private ComboBox order;

    private TextField codaAllowanceId;
    private TextField codaQuota;
    private DateField codaFrom;
    private DateField codaTo;

    private TextField orgaPocketName;
    private TextField orgaBalanceName;
    private TextField orgaComment;
    private TextField orgaAmount;
    private DateField orgaFrom;
    private DateField orgaTo;

    private TextField allCountSet;
    private TextField complatedCount;
    private TextField failedCount;
    private DateField finish;
    private DateField start;

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

        name = new TextField("Name");
        description = new TextField("Description");
        order = new ComboBox("Order", Arrays.asList(new Integer[]{1,2,3,4,5}));

        codaAllowanceId = new TextField("Allowance ID");
        codaQuota = new TextField("Quota");
        codaFrom = new DateField("From");
        codaTo = new DateField("To");

        orgaPocketName = new TextField("Pocket name");
        orgaBalanceName = new TextField("Balance name");
        orgaComment = new TextField("Comment");
        orgaAmount = new TextField("Amount");
        orgaFrom = new DateField("From");
        orgaTo = new DateField("To");

        allCountSet = new TextField("All counts");
        allCountSet.setEnabled(false);
        complatedCount = new TextField("Complated counts");
        complatedCount.setEnabled(false);
        failedCount = new TextField("Failed counts");
        failedCount.setEnabled(false);
        start = new DateField("Start date");
        start.setEnabled(false);
        finish = new DateField("Finish date");
        finish.setEnabled(false);
    }

    private Grid<BonusParams> buildGrid() {
        grid = new Grid<>();
        grid.setWidth(100, Unit.PERCENTAGE);
        grid.addColumn(BonusParams::getBid).setCaption("BID");
        grid.addColumn(BonusParams::getAllowanceName).setCaption("Name");
        grid.addColumn(BonusParams::getAllowanceDescr).setCaption("Description");
        grid.addColumn(BonusParams::getExeOrder).setCaption("Order");
        grid.addComponentColumn(bonus -> addBonusAction(bonus));
        grid.addSelectionListener(event -> {
            updateBonusLayout(event);
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

    private void updateBonusLayout(SelectionEvent<BonusParams> event) {
        selectedBonus = event.getFirstSelectedItem().get();
        BonusAssigmentState assignmentState = listener.getBonusAssigmentState(selectedBonus);

        //Test
        assignmentState.setStart(XMLGregorianCalendarImpl.createDate(2017,12,24,6));
        assignmentState.setFinish(XMLGregorianCalendarImpl.createDate(2017,12,27,6));

        if (selectedBonus.getAllowanceName() != null) name.setValue(selectedBonus.getAllowanceName());
        if (selectedBonus.getAllowanceDescr() != null) description.setValue(selectedBonus.getAllowanceDescr());
        if (selectedBonus.getExeOrder() != null) order.setValue(selectedBonus.getExeOrder());

        if (selectedBonus.getAllowanceId() != null) codaAllowanceId.setValue(selectedBonus.getAllowanceId());
        if (selectedBonus.getAllowanceQuota() != null) codaQuota.setValue(String.valueOf(selectedBonus.getAllowanceQuota()));
        if (selectedBonus.getAllowanceStartDate() != null) codaFrom.setValue(selectedBonus.getAllowanceStartDate().toGregorianCalendar()
                .toZonedDateTime().toLocalDate());
        if (selectedBonus.getAllowanceEndDate() != null) codaTo.setValue(selectedBonus.getAllowanceEndDate().toGregorianCalendar()
                .toZonedDateTime().toLocalDate());

        if (selectedBonus.getOrgaPocketName() != null) orgaPocketName.setValue(selectedBonus.getOrgaPocketName());
        if (selectedBonus.getOrgaBalanceName() != null) orgaBalanceName.setValue(selectedBonus.getOrgaBalanceName());
        if (selectedBonus.getOrgaAmount() != null) orgaAmount.setValue(String.valueOf(selectedBonus.getOrgaAmount()));
        if (selectedBonus.getOrgaComment() != null) orgaComment.setValue(selectedBonus.getOrgaComment());
        if (selectedBonus.getOrgaStartDate() != null) orgaFrom.setValue(selectedBonus.getOrgaStartDate().toGregorianCalendar()
                .toZonedDateTime().toLocalDate());
        if (selectedBonus.getOrgaExpDate() != null) orgaTo.setValue(selectedBonus.getOrgaExpDate().toGregorianCalendar()
                .toZonedDateTime().toLocalDate());

        if (assignmentState.getComplatedCount() != null) allCountSet.setValue(String.valueOf(assignmentState.getAllCountSet()));
        if (assignmentState.getComplatedCount() != null) complatedCount.setValue(String.valueOf(assignmentState.getComplatedCount()));
        if (assignmentState.getFailedCount() != null) failedCount.setValue(String.valueOf(assignmentState.getFailedCount()));
        if (assignmentState.getStart() != null) start.setValue(assignmentState.getStart().toGregorianCalendar().toZonedDateTime().toLocalDate());
        if (assignmentState.getFinish() != null) finish.setValue(assignmentState.getFinish().toGregorianCalendar().toZonedDateTime().toLocalDate());
    }

    private VerticalLayout buildBonusInfo() {
        VerticalLayout bonusInfo = new VerticalLayout();
        HorizontalLayout hr = new HorizontalLayout();

        Panel commonDescription = new Panel("Common Description");
        commonDescription.setSizeUndefined();
        commonDescription.setHeight(400, Unit.PIXELS);
        hr.addComponent(commonDescription);

        Panel kcellAllowancePanel = new Panel("Kcell Description");
        kcellAllowancePanel.setSizeUndefined();
        kcellAllowancePanel.setHeight(400, Unit.PIXELS);
        hr.addComponent(kcellAllowancePanel);

        Panel activAllowancePanel = new Panel("Activ Description");
        activAllowancePanel.setSizeUndefined();
        activAllowancePanel.setHeight(400, Unit.PIXELS);
        hr.addComponent(activAllowancePanel);

        Panel state = new Panel("State");
        state.setSizeUndefined();
        state.setHeight(400, Unit.PIXELS);
        hr.addComponent(state);

        FormLayout commonInfo = new FormLayout();
        commonInfo.addComponent(name);
        commonInfo.addComponent(description);
        commonInfo.addComponent(order);
        commonInfo.setSizeUndefined();
        commonInfo.setMargin(true);
        commonDescription.setContent(commonInfo);

        FormLayout kcellAllowance = new FormLayout();
        kcellAllowance.addComponent(codaAllowanceId);
        kcellAllowance.addComponent(codaQuota);
        kcellAllowance.addComponent(codaFrom);
        kcellAllowance.addComponent(codaTo);
        kcellAllowance.setSizeUndefined();
        kcellAllowance.setMargin(true);
        kcellAllowancePanel.setContent(kcellAllowance);

        FormLayout activAllowance = new FormLayout();
        activAllowance.addComponent(orgaPocketName);
        activAllowance.addComponent(orgaBalanceName);
        activAllowance.addComponent(orgaAmount);
        activAllowance.addComponent(orgaComment);
        activAllowance.addComponent(orgaFrom);
        activAllowance.addComponent(orgaTo);

        activAllowance.setSizeUndefined();
        activAllowance.setMargin(true);
        activAllowancePanel.setContent(activAllowance);

        FormLayout stateInfo = new FormLayout();
        stateInfo.addComponent(allCountSet);
        stateInfo.addComponent(complatedCount);
        stateInfo.addComponent(failedCount);
        stateInfo.addComponent(finish);
        stateInfo.addComponent(start);

        stateInfo.setSizeUndefined();
        stateInfo.setMargin(true);
        state.setContent(stateInfo);

        Button saveBtn = new Button("Save", event -> {
            saveBonusInfo();
        });
        saveBtn.setIcon(FontAwesome.SAVE);

        bonusInfo.addComponent(hr);
        bonusInfo.addComponent(saveBtn);

        return bonusInfo;
    }

    private void saveBonusInfo() {
        BonusParams bonusParams = selectedBonus;

        bonusParams.setAllowanceName(name.getValue());
        bonusParams.setAllowanceDescr(description.getValue());
        bonusParams.setExeOrder((Integer) order.getValue());
        bonusParams.setAllowanceId(codaAllowanceId.getValue());
        bonusParams.setAllowanceQuota(Double.parseDouble(codaQuota.getValue()));

        XMLGregorianCalendar codaFromGregorian = selectedBonus.getAllowanceStartDate();
        codaFromGregorian.setDay(codaFrom.getValue().getDayOfMonth());
        codaFromGregorian.setMonth(codaFrom.getValue().getMonthValue());
        codaFromGregorian.setYear(codaFrom.getValue().getYear());
        bonusParams.setAllowanceStartDate(codaFromGregorian);

        XMLGregorianCalendar codaToGregorian = selectedBonus.getAllowanceEndDate();
        codaToGregorian.setDay(codaTo.getValue().getDayOfMonth());
        codaToGregorian.setMonth(codaTo.getValue().getMonthValue());
        codaToGregorian.setYear(codaTo.getValue().getYear());
        bonusParams.setAllowanceEndDate(codaToGregorian);

        bonusParams.setOrgaPocketName(orgaPocketName.getValue());
        bonusParams.setOrgaBalanceName(orgaBalanceName.getValue());
        bonusParams.setOrgaAmount(Double.parseDouble(orgaAmount.getValue()));
        bonusParams.setOrgaComment(orgaComment.getValue());

        XMLGregorianCalendar orgaFromGregorian = selectedBonus.getOrgaStartDate();
        orgaFromGregorian.setDay(orgaFrom.getValue().getDayOfMonth());
        orgaFromGregorian.setMonth(orgaFrom.getValue().getMonthValue());
        orgaFromGregorian.setYear(orgaFrom.getValue().getYear());
        bonusParams.setOrgaStartDate(orgaFromGregorian);

        XMLGregorianCalendar orgaToGregorian = selectedBonus.getOrgaExpDate();
        orgaToGregorian.setDay(orgaTo.getValue().getDayOfMonth());
        orgaToGregorian.setMonth(orgaTo.getValue().getMonthValue());
        orgaToGregorian.setYear(orgaTo.getValue().getYear());
        bonusParams.setOrgaExpDate(orgaToGregorian);

        listener.updateBonusParams(bonusParams);
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
        h.setMargin(new MarginInfo(false, true, false, true));

        content.addComponent(h);
        content.addComponent(buildBonusInfo());
    }


    public void translate() {
        setTitle("Компания: " + company.getName());
    }

}
