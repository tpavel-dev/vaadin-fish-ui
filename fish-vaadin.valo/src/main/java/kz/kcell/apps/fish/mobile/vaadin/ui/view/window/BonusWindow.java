package kz.kcell.apps.fish.mobile.vaadin.ui.view.window;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.data.validator.DateRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Setter;
import com.vaadin.ui.*;
import kz.kcell.app.bonus_cmdr.ws.stub.BonusParams;
import kz.kcell.app.bonus_cmdr.ws.stub.Company;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompanyView;
import kz.kcell.vaadin.data.EmptyDateValidator;
import kz.kcell.vaadin.data.ParseToDoubleValidator;
import kz.kcell.vaadin.data.ParseToIntegerValidator;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.Arrays;

public class BonusWindow extends AbstractWindow<BonusParams> {

    private CompanyView.Listener listener;
    private Company company;

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

    public BonusWindow(CompanyView.Listener listener, Company company) {
        super();
        this.listener = listener;
        this.company = company;
    }

    @Override
    void init() {
        center();
        setWidth(900, Unit.PIXELS);
        setContent(buildLayout());
        initBinder();
    }

    @Override
    void initBinder() {
        binder = new Binder<>();
        binder.bind(name, BonusParams::getAllowanceName, BonusParams::setAllowanceName);
        binder.bind(description, BonusParams::getAllowanceName, BonusParams::setAllowanceName);
        binder.bind(order, BonusParams::getAllowanceName, BonusParams::setAllowanceName);
        binder.bind(codaAllowanceId, BonusParams::getAllowanceName, BonusParams::setAllowanceName);
        binder.bind(codaQuota, b -> String.valueOf(b.getAllowanceQuota()), (Setter<BonusParams, String>) (bonusParams, s) -> {
            bonusParams.setAllowanceQuota(Double.parseDouble(s));
        });
        binder.bind(codaFrom, b -> b.getAllowanceStartDate().toGregorianCalendar().toZonedDateTime().toLocalDate(),
                (Setter<BonusParams, LocalDate>) (bonusParams, localDate) ->
                        bonusParams.setAllowanceStartDate(toXmlGregorianCalendar(localDate)));
        binder.bind(codaTo, b -> b.getAllowanceEndDate().toGregorianCalendar().toZonedDateTime().toLocalDate(),
                (Setter<BonusParams, LocalDate>) (bonusParams, localDate) ->
                        bonusParams.setAllowanceEndDate(toXmlGregorianCalendar(localDate)));
        binder.bind(orgaPocketName, BonusParams::getOrgaPocketName, BonusParams::setOrgaPocketName);
        binder.bind(orgaBalanceName, BonusParams::getOrgaBalanceName, BonusParams::setOrgaBalanceName);
        binder.bind(orgaComment, BonusParams::getOrgaComment, BonusParams::setOrgaComment);
        binder.bind(orgaAmount, b -> String.valueOf(b.getOrgaAmount()), (Setter<BonusParams, String>) (bonusParams, s) -> {
            bonusParams.setOrgaAmount(Double.parseDouble(s));
        });
        binder.bind(orgaFrom, b -> b.getOrgaStartDate().toGregorianCalendar().toZonedDateTime().toLocalDate(),
                (Setter<BonusParams, LocalDate>) (bonusParams, localDate) ->
                        bonusParams.setOrgaStartDate(toXmlGregorianCalendar(localDate)));
        binder.bind(orgaTo, b -> b.getOrgaExpDate().toGregorianCalendar().toZonedDateTime().toLocalDate(),
                (Setter<BonusParams, LocalDate>) (bonusParams, localDate) ->
                        bonusParams.setOrgaExpDate(toXmlGregorianCalendar(localDate)));

        // Validation
        binder.forField(name)
                .withValidator(new StringLengthValidator(
                        "Name must be between 1 and 40 characters long",
                        1, 40))
                .bind(BonusParams::getAllowanceName, BonusParams::setAllowanceName);

        binder.forField(codaAllowanceId).withValidator(new StringLengthValidator(
                        "Allowance must be between 1 and 30 characters long",
                        1,30))
                .bind(BonusParams::getAllowanceId, BonusParams::setAllowanceId);

        binder.forField(codaQuota).withValidator(new ParseToDoubleValidator("Quota must be numeric"))
                .bind(b -> String.valueOf(b.getAllowanceQuota()), (Setter<BonusParams, String>) (bonusParams, s) -> {
                    bonusParams.setAllowanceQuota(Double.parseDouble(s));
                });
        binder.forField(codaFrom).withValidator(new EmptyDateValidator("Select date"))
                .bind(b -> toLocalDate(b.getAllowanceStartDate()), (Setter<BonusParams, LocalDate>) (bonusParams, localDate) -> {
                    bonusParams.setAllowanceStartDate(toXmlGregorianCalendar(localDate));
                });
        binder.forField(codaTo).withValidator(new EmptyDateValidator("Select date"))
                .bind(b -> toLocalDate(b.getAllowanceEndDate()), (Setter<BonusParams, LocalDate>) (bonusParams, localDate) -> {
                    bonusParams.setAllowanceEndDate(toXmlGregorianCalendar(localDate));
                });

        binder.forField(orgaPocketName).withValidator(new StringLengthValidator(
                "Allowance must be between 1 and 40 characters long",
                1,40))
                .bind(BonusParams::getOrgaPocketName, BonusParams::setOrgaPocketName);
        binder.forField(orgaBalanceName).withValidator(new StringLengthValidator(
                "Allowance must be between 1 and 40 characters long",
                1,40))
                .bind(BonusParams::getOrgaBalanceName, BonusParams::setOrgaBalanceName);
        binder.forField(orgaAmount).withValidator(new ParseToDoubleValidator("Amount must be numeric"))
                .bind(b -> String.valueOf(b.getOrgaAmount()), (Setter<BonusParams, String>) (bonusParams, s) -> {
                    bonusParams.setOrgaAmount(Double.parseDouble(s));
                });
        binder.forField(orgaFrom).withValidator(new EmptyDateValidator("Select date"))
                .bind(b -> toLocalDate(b.getOrgaStartDate()), (Setter<BonusParams, LocalDate>) (bonusParams, localDate) -> {
                    bonusParams.setOrgaStartDate(toXmlGregorianCalendar(localDate));
                });
        binder.forField(orgaTo).withValidator(new EmptyDateValidator("Select date"))
                .bind(b -> toLocalDate(b.getOrgaStartDate()), (Setter<BonusParams, LocalDate>) (bonusParams, localDate) -> {
                    bonusParams.setOrgaExpDate(toXmlGregorianCalendar(localDate));
                });

    }

    @Override
    Component buildLayout() {
        FormLayout content = new FormLayout();

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setMargin(false);
        topLayout.setSpacing(false);

        VerticalLayout leftSide = new VerticalLayout();
        VerticalLayout rightSide = new VerticalLayout();
        VerticalLayout middle = new VerticalLayout();

        name = new TextField("Name:");
        leftSide.addComponent(name);

        description = new TextField("Description:");
        leftSide.addComponent(description);

        order = new ComboBox("Order:", Arrays.asList(new Integer[]{1,2,3,4,5}));
        order.setEmptySelectionAllowed(false);
        order.setValue(1);
        leftSide.addComponent(order);

        codaAllowanceId = new TextField("Allowance ID");
        middle.addComponent(codaAllowanceId);

        codaQuota = new TextField("Quota");
        middle.addComponent(codaQuota);

        codaFrom = new DateField("From");
        middle.addComponent(codaFrom);

        codaTo = new DateField("To");
        middle.addComponent(codaTo);

        orgaPocketName = new TextField("Pocket name");
        rightSide.addComponent(orgaPocketName);

        orgaBalanceName = new TextField("Balance name");
        rightSide.addComponent(orgaBalanceName);

        orgaAmount = new TextField("Amount");
        rightSide.addComponent(orgaAmount);

        orgaComment = new TextField("Comment");
        rightSide.addComponent(orgaComment);

        orgaFrom = new DateField("From");
        rightSide.addComponent(orgaFrom);

        orgaTo = new DateField("To");
        rightSide.addComponent(orgaTo);

        topLayout.addComponent(leftSide);
        topLayout.addComponent(middle);
        topLayout.addComponent(rightSide);

        content.addComponent(topLayout);

        HorizontalLayout actionBtns = new HorizontalLayout();
        Button saveBtn = new Button("Save");
        saveBtn.setIcon(FontAwesome.SAVE);
        saveBtn.addClickListener(event -> {
            save();
        });
        actionBtns.addComponent(saveBtn);

        Button closeBtn = new Button("Close");
        closeBtn.setIcon(FontAwesome.CLOSE);
        closeBtn.addClickListener(event -> {
            close();
        });
        actionBtns.addComponent(closeBtn);
        actionBtns.setComponentAlignment(closeBtn, Alignment.MIDDLE_RIGHT);
        content.addComponent(actionBtns);
        return content;
    }

    @Override
    void save() {
        if (!validate()) {
            return;
        }

        BonusParams bonus = binder.getBean();
        bonus.setCid(company.getCid());
        listener.saveBonus(bonus);
    }

    @Override
    boolean validate() {
        BinderValidationStatus<BonusParams> status = binder.validate();
        return status.getValidationErrors().isEmpty();
    }

    private XMLGregorianCalendar toXmlGregorianCalendar(LocalDate localDate) {
        return XMLGregorianCalendarImpl.createDate(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), 6 * 60);
    }

    private LocalDate toLocalDate(XMLGregorianCalendar calendar) {
        if (calendar == null) return null;
        return calendar.toGregorianCalendar().toZonedDateTime().toLocalDate();
    }
}
