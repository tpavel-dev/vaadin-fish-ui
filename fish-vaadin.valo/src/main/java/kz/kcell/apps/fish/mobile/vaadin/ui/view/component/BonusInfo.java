package kz.kcell.apps.fish.mobile.vaadin.ui.view.component;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import kz.kcell.app.bonus_cmdr.ws.stub.BonusAssigmentState;
import kz.kcell.app.bonus_cmdr.ws.stub.BonusParams;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompanyView;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.TimeZone;

public class BonusInfo extends AbstractInfo<BonusParams> {

    private CompanyView.Listener listener;
    private BonusParams bonus;
    private BonusAssigmentState bonusState;
    private boolean editable;

    private Panel bonusCommonPanel;
    private Panel bonusKcellPanel;
    private Panel bonusActivPanel;
    private Panel bonusStatePanel;

    private TextField name;
    private TextField description;
    private ComboBox order;

    private TextField codaAllowanceId;
    private TextField codaQuota;
    private DateField codaFrom;
    private DateField codaTo;

    private TextField orgaPocketName;
    private TextField orgaBalanceName;
    private TextField orgaAmount;
    private TextField orgaComment;
    private DateField orgaFrom;
    private DateField orgaTo;

    private TextField stateAllCountSet;
    private TextField stateComplatedCount;
    private TextField stateFailedCount;
    private DateField stateFinish;
    private DateField stateStart;

    public BonusInfo(CompanyView.Listener listener, BonusParams bonus, boolean editable) {
        this.listener = listener;
        this.bonus = bonus == null ? new BonusParams() : bonus;
        this.editable = editable;
        init();
    }

    @Override
    protected void init() {
        bonusState = new BonusAssigmentState();

        name = new TextField("Name:");
        description = new TextField("Description:");
        order = new ComboBox("Order:", Arrays.asList(new Integer[]{1,2,3,4,5}));
        order.setEmptySelectionAllowed(false);

        name.setEnabled(editable);
        description.setEnabled(editable);
        order.setEnabled(editable);

        codaAllowanceId = new TextField("Allowance ID:");
        codaQuota = new TextField("Quota:");
        codaFrom = new DateField("Date from:");
        codaTo = new DateField("Date to:");

        codaAllowanceId.setEnabled(editable);
        codaQuota.setEnabled(editable);
        codaFrom.setEnabled(editable);
        codaTo.setEnabled(editable);

        orgaPocketName = new TextField("Pocket name:");
        orgaBalanceName = new TextField("Balance name:");
        orgaComment = new TextField("Comment:");
        orgaAmount = new TextField("Amount:");
        orgaFrom = new DateField("Date from:");
        orgaTo = new DateField("Date to:");

        orgaPocketName.setEnabled(editable);
        orgaBalanceName.setEnabled(editable);
        orgaComment.setEnabled(editable);
        orgaAmount.setEnabled(editable);
        orgaFrom.setEnabled(editable);
        orgaTo.setEnabled(editable);

        stateAllCountSet = new TextField("All Counts");
        stateComplatedCount = new TextField("Complated counts");
        stateFailedCount = new TextField("Failed counts");
        stateStart = new DateField("Date from");
        stateFinish = new DateField("Date to");

        stateAllCountSet.setEnabled(false);
        stateComplatedCount.setEnabled(false);
        stateFailedCount.setEnabled(false);
        stateStart.setEnabled(false);
        stateFinish.setEnabled(false);

        bonusCommonPanel = new Panel("Common Description");
        bonusCommonPanel.setSizeUndefined();
        bonusCommonPanel.setHeight(400, Unit.PIXELS);

        bonusKcellPanel = new Panel("Kcell Description");
        bonusKcellPanel.setSizeUndefined();
        bonusKcellPanel.setHeight(400, Unit.PIXELS);

        bonusActivPanel = new Panel("Activ Description");
        bonusActivPanel.setSizeUndefined();
        bonusActivPanel.setHeight(400, Unit.PIXELS);

        bonusStatePanel = new Panel("State");
        bonusStatePanel.setSizeUndefined();
        bonusStatePanel.setHeight(400, Unit.PIXELS);

        super.init();
    }

    @Override
    protected void buildLayout() {
        FormLayout commonInfo = new FormLayout();
        commonInfo.addComponent(name);
        commonInfo.addComponent(description);
        commonInfo.addComponent(order);
        commonInfo.setSizeUndefined();
        commonInfo.setMargin(true);
        bonusCommonPanel.setContent(commonInfo);

        FormLayout kcellInfo = new FormLayout();
        kcellInfo.addComponent(codaAllowanceId);
        kcellInfo.addComponent(codaQuota);
        kcellInfo.addComponent(codaFrom);
        kcellInfo.addComponent(codaTo);
        kcellInfo.setSizeUndefined();
        kcellInfo.setMargin(true);
        bonusKcellPanel.setContent(kcellInfo);

        FormLayout activInfo = new FormLayout();
        activInfo.addComponent(orgaPocketName);
        activInfo.addComponent(orgaBalanceName);
        activInfo.addComponent(orgaAmount);
        activInfo.addComponent(orgaComment);
        activInfo.addComponent(orgaFrom);
        activInfo.addComponent(orgaTo);
        activInfo.setSizeUndefined();
        activInfo.setMargin(true);
        bonusActivPanel.setContent(activInfo);

        FormLayout stateInfo = new FormLayout();
        stateInfo.addComponent(stateAllCountSet);
        stateInfo.addComponent(stateComplatedCount);
        stateInfo.addComponent(stateFailedCount);
        stateInfo.addComponent(stateStart);
        stateInfo.addComponent(stateFinish);
        stateInfo.setSizeUndefined();
        stateInfo.setMargin(true);
        bonusStatePanel.setContent(stateInfo);

        addComponent(bonusCommonPanel);
        addComponent(bonusKcellPanel);
        addComponent(bonusActivPanel);
        addComponent(bonusStatePanel);
    }

    @Override
    public void setBonus(BonusParams bonus) {
        this.bonus = bonus;
        this.bonusState = listener.getBonusAssigmentState(bonus);
        setFieldsValue();
    }

    @Override
    protected void setFieldsValue() {

        name.setValue(bonus.getAllowanceName() != null ? bonus.getAllowanceName() : "");
        description.setValue(bonus.getAllowanceDescr() != null ? bonus.getAllowanceDescr() : "");
        order.setValue(bonus.getExeOrder() != null ? bonus.getExeOrder() : null);

        codaAllowanceId.setValue(bonus.getAllowanceId() != null ? bonus.getAllowanceId() : "");
        codaQuota.setValue(bonus.getAllowanceQuota() != null ? String.valueOf(bonus.getAllowanceQuota()) : "");
        codaFrom.setValue(bonus.getAllowanceStartDate() != null ? bonus.getAllowanceStartDate().toGregorianCalendar().toZonedDateTime().toLocalDate() : null);
        codaTo.setValue(bonus.getAllowanceEndDate() != null ? bonus.getAllowanceEndDate().toGregorianCalendar().toZonedDateTime().toLocalDate() : null);

        orgaPocketName.setValue(bonus.getOrgaPocketName() != null ? bonus.getOrgaPocketName() : "");
        orgaBalanceName.setValue(bonus.getOrgaBalanceName() != null ? bonus.getOrgaBalanceName() : "");
        orgaAmount.setValue(bonus.getOrgaAmount() != null ? String.valueOf(bonus.getOrgaAmount()) : "");
        orgaComment.setValue(bonus.getOrgaComment() != null ? bonus.getOrgaComment() : "");
        orgaFrom.setValue(bonus.getOrgaStartDate() != null ? bonus.getOrgaStartDate().toGregorianCalendar().toZonedDateTime().toLocalDate() : null);
        orgaTo.setValue(bonus.getOrgaExpDate() != null ? bonus.getOrgaExpDate().toGregorianCalendar().toZonedDateTime().toLocalDate() : null);

        stateAllCountSet.setValue(bonusState.getAllCountSet() != null ? String.valueOf(bonusState.getAllCountSet()) : "");
        stateComplatedCount.setValue(bonusState.getComplatedCount() != null ? String.valueOf(bonusState.getComplatedCount()) : "");
        stateFailedCount.setValue(bonusState.getFailedCount() != null ? String.valueOf(bonusState.getFailedCount()) : "");
        stateStart.setValue(bonusState.getStart() != null ? bonusState.getStart().toGregorianCalendar().toZonedDateTime().toLocalDate() : null);
        stateFinish.setValue(bonusState.getFinish() != null ? bonusState.getFinish().toGregorianCalendar().toZonedDateTime().toLocalDate() : null);
    }

    @Override
    public BonusParams getBean() {
        BonusParams bonusParams = new BonusParams();
        bonusParams.setCid(bonus.getCid());
        bonusParams.setBid(bonus.getBid());
        bonusParams.setStatus(bonus.getStatus());
        bonusParams.setAllowanceName(name.getValue());
        bonusParams.setAllowanceDescr(description.getValue());
        bonusParams.setExeOrder((Integer) order.getValue());

        bonusParams.setAllowanceId(codaAllowanceId.getValue());
        bonusParams.setAllowanceQuota(convertToDouble(codaQuota.getValue()));
        bonusParams.setAllowanceStartDate(convertToXMLGregorianCalendar(codaFrom.getValue()));
        bonusParams.setAllowanceEndDate(convertToXMLGregorianCalendar(codaTo.getValue()));

        bonusParams.setOrgaPocketName(orgaPocketName.getValue());
        bonusParams.setOrgaBalanceName(orgaBalanceName.getValue());
        bonusParams.setOrgaComment(orgaComment.getValue());
        bonusParams.setOrgaAmount(convertToDouble(orgaAmount.getValue()));
        bonusParams.setOrgaStartDate(convertToXMLGregorianCalendar(orgaFrom.getValue()));
        bonusParams.setOrgaExpDate(convertToXMLGregorianCalendar(orgaTo.getValue()));

        return bonusParams;
    }

    private Double convertToDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return 0.0;
        }
    }

    private XMLGregorianCalendar convertToXMLGregorianCalendar(LocalDate date) {
        try {
            return XMLGregorianCalendarImpl.createDateTime(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 00, 00, 00);
        } catch (Exception e) {
            return null;
        }
    }
}
