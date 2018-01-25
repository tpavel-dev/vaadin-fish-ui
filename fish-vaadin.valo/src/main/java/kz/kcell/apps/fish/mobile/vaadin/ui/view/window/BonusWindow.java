package kz.kcell.apps.fish.mobile.vaadin.ui.view.window;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.data.ValidationException;
import com.vaadin.data.validator.DateRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Setter;
import com.vaadin.ui.*;
import kz.kcell.app.bonus_cmdr.ws.stub.BonusParams;
import kz.kcell.app.bonus_cmdr.ws.stub.BonusStatus;
import kz.kcell.app.bonus_cmdr.ws.stub.Company;
import kz.kcell.apps.bonus_cmdr.model.NotificationUtils;
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
//        binder.bind(description, BonusParams::getAllowanceName, BonusParams::setAllowanceName);
//        binder.bind(order, BonusParams::getAllowanceName, BonusParams::setAllowanceName);
//        binder.bind(codaAllowanceId, BonusParams::getAllowanceName, BonusParams::setAllowanceName);
//        binder.bind(codaQuota, b -> String.valueOf(b.getAllowanceQuota()), (Setter<BonusParams, String>) (bonusParams, s) -> {
//            bonusParams.setAllowanceQuota(Double.parseDouble(s));
//        });
//        binder.bind(codaFrom, b -> b.getAllowanceStartDate().toGregorianCalendar().toZonedDateTime().toLocalDate(),
//                (Setter<BonusParams, LocalDate>) (bonusParams, localDate) ->
//                        bonusParams.setAllowanceStartDate(convertToXMLGregorianCalendar(localDate)));
//        binder.bind(codaTo, b -> b.getAllowanceEndDate().toGregorianCalendar().toZonedDateTime().toLocalDate(),
//                (Setter<BonusParams, LocalDate>) (bonusParams, localDate) ->
//                        bonusParams.setAllowanceEndDate(convertToXMLGregorianCalendar(localDate)));
//        binder.bind(orgaPocketName, BonusParams::getOrgaPocketName, BonusParams::setOrgaPocketName);
//        binder.bind(orgaBalanceName, BonusParams::getOrgaBalanceName, BonusParams::setOrgaBalanceName);
//        binder.bind(orgaComment, BonusParams::getOrgaComment, BonusParams::setOrgaComment);
//        binder.bind(orgaAmount, b -> String.valueOf(b.getOrgaAmount()), (Setter<BonusParams, String>) (bonusParams, s) -> {
//            bonusParams.setOrgaAmount(Double.parseDouble(s));
//        });
//        binder.bind(orgaFrom, b -> b.getOrgaStartDate().toGregorianCalendar().toZonedDateTime().toLocalDate(),
//                (Setter<BonusParams, LocalDate>) (bonusParams, localDate) ->
//                        bonusParams.setOrgaStartDate(convertToXMLGregorianCalendar(localDate)));
//        binder.bind(orgaTo, b -> b.getOrgaExpDate().toGregorianCalendar().toZonedDateTime().toLocalDate(),
//                (Setter<BonusParams, LocalDate>) (bonusParams, localDate) ->
//                        bonusParams.setOrgaExpDate(convertToXMLGregorianCalendar(localDate)));
//
//        // Validation
        binder.forField(name)
                .withValidator(new StringLengthValidator(
                        "Name must be between 1 and 40 characters long",
                        1, 40))
                .bind(BonusParams::getAllowanceName, BonusParams::setAllowanceName);
//
//        binder.forField(codaAllowanceId).withValidator(new StringLengthValidator(
//                        "Allowance must be between 1 and 30 characters long",
//                        1,30))
//                .bind(BonusParams::getAllowanceId, BonusParams::setAllowanceId);
//
//        binder.forField(codaQuota).withValidator(new ParseToDoubleValidator("Quota must be numeric"))
//                .bind(b -> String.valueOf(b.getAllowanceQuota()), (Setter<BonusParams, String>) (bonusParams, s) -> {
//                    bonusParams.setAllowanceQuota(Double.parseDouble(s));
//                });
//        binder.forField(codaFrom).withValidator(new EmptyDateValidator("Select date"))
//                .bind(b -> toLocalDate(b.getAllowanceStartDate()), (Setter<BonusParams, LocalDate>) (bonusParams, localDate) -> {
//                    bonusParams.setAllowanceStartDate(convertToXMLGregorianCalendar(localDate));
//                });
//        binder.forField(codaTo).withValidator(new EmptyDateValidator("Select date"))
//                .bind(b -> toLocalDate(b.getAllowanceEndDate()), (Setter<BonusParams, LocalDate>) (bonusParams, localDate) -> {
//                    bonusParams.setAllowanceEndDate(convertToXMLGregorianCalendar(localDate));
//                });
//
//        binder.forField(orgaPocketName).withValidator(new StringLengthValidator(
//                "Allowance must be between 1 and 40 characters long",
//                1,40))
//                .bind(BonusParams::getOrgaPocketName, BonusParams::setOrgaPocketName);
//        binder.forField(orgaBalanceName).withValidator(new StringLengthValidator(
//                "Allowance must be between 1 and 40 characters long",
//                1,40))
//                .bind(BonusParams::getOrgaBalanceName, BonusParams::setOrgaBalanceName);
//        binder.forField(orgaAmount).withValidator(new ParseToDoubleValidator("Amount must be numeric"))
//                .bind(b -> String.valueOf(b.getOrgaAmount()), (Setter<BonusParams, String>) (bonusParams, s) -> {
//                    bonusParams.setOrgaAmount(Double.parseDouble(s));
//                });
//        binder.forField(orgaFrom).withValidator(new EmptyDateValidator("Select date"))
//                .bind(b -> toLocalDate(b.getOrgaStartDate()), (Setter<BonusParams, LocalDate>) (bonusParams, localDate) -> {
//                    bonusParams.setOrgaStartDate(convertToXMLGregorianCalendar(localDate));
//                });
//        binder.forField(orgaTo).withValidator(new EmptyDateValidator("Select date"))
//                .bind(b -> toLocalDate(b.getOrgaStartDate()), (Setter<BonusParams, LocalDate>) (bonusParams, localDate) -> {
//                    bonusParams.setOrgaExpDate(convertToXMLGregorianCalendar(localDate));
//                });

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
        content.addComponent(getActionButtons());
        return content;
    }

    @Override
    void save() {
        if (!validate()) {
            return;
        }

        BonusParams bonus = generateBonusParams();
        bonus.setCid(company.getCid());
        listener.saveBonus(bonus);
        close();
        NotificationUtils.show("Bonus successfully added!", Notification.Type.HUMANIZED_MESSAGE);
    }

    @Override
    boolean validate() {
        BinderValidationStatus<BonusParams> status = binder.validate();
        return status.getValidationErrors().isEmpty();
    }

    private BonusParams generateBonusParams() {
        BonusParams bonusParams = new BonusParams();
        bonusParams.setCid(company.getCid());
        bonusParams.setStatus(BonusStatus.STOPED);
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

    private XMLGregorianCalendar convertToXMLGregorianCalendar(LocalDate localDate) {
        if (localDate == null) return null;
        return XMLGregorianCalendarImpl.createDateTime(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), 00, 00, 00);
    }

    private Double convertToDouble(String str) {
        if (str == null || str.isEmpty()) return null;
        return Double.parseDouble(str);
    }

    private LocalDate toLocalDate(XMLGregorianCalendar calendar) {
        if (calendar == null) return null;
        return calendar.toGregorianCalendar().toZonedDateTime().toLocalDate();
    }
}
