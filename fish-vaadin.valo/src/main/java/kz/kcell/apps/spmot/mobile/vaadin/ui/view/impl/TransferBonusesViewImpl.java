package kz.kcell.apps.spmot.mobile.vaadin.ui.view.impl;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kz.kcell.apps.common.msisdn.FastMsisdn;
import kz.kcell.apps.common.msisdn.Msisdn;
import kz.kcell.apps.spmot.mobile.vaadin.SpmotWebConfig;
import kz.kcell.apps.spmot.mobile.vaadin.ui.ErrorHandler;
import kz.kcell.apps.spmot.mobile.vaadin.ui.view.TransferBonusesView;
import kz.kcell.apps.spmot.mobile.vaadin.ui.view.ViewsCode;
import kz.kcell.vaadin.HTML;
import kz.kcell.vaadin.HackUtils;
import kz.kcell.vaadin.ui.Style;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static kz.kcell.apps.spmot.domain.spmot.entity.SpmotResourceBundle.*;
import static kz.kcell.apps.spmot.mobile.vaadin.SpmotMobileResourceManager.$;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 23 10 2014
 */
@SpringView(name = ViewsCode.name_transfer)
public class TransferBonusesViewImpl extends BaseNavigationView implements TransferBonusesView {

    @Autowired
    @Getter
    private TransferBonusesView.Listener listener;

    private TextField msisdnField;
    //    private MaskedTextField msisdnField;
    private TextField sumField;
    private PopupView pv;
    private Window confirmPopupWindows;
    private Button confirm;
    private Button submite;
    private Button cancel;
    private Button requestBonusBtn = new Button();
    private Label confirmLabel;
    private Label notifLable;

    public TransferBonusesViewImpl() {
    }

    @PostConstruct
    public void init() {
        listener.setView(this);
        super.init();
    }

    @Override
    public void translate() {
        super.translate();
        setTitle($(transfer_title));
        msisdnField.setCaption($(transfer_msisdn_field_caption));
        sumField.setCaption($(transfer_sum_field_caption));
        confirm.setCaption($(transfer_confirm_field_caption));
        notifLable.setValue($(transfer_notif_label));
        submite.setCaption($(transfer_confirm_submit_btn_caption));
        cancel.setCaption($(transfer_confirm_cancel_btn_caption));
        requestBonusBtn.setCaption($(transfer_request_bonus_caption));
    }

    @Override
    protected void injectInit() {
        setTitle($(transfer_title));

        msisdnField = new TextField($(transfer_msisdn_field_caption));
        msisdnField.setPlaceholder(SpmotWebConfig.INPUT_PROMPT_CELL);
//        msisdnField.setInputPrompt(Config.INPUT_PROMPT_CELL);
//        msisdnField = new MaskedTextField("Телефон адресата", "(###) ###-####");

//         msisdnField.addValidator(new NullValidator("Please, submit addresses phone number", false));
//         msisdnField.addValidator(new MsisdnValidator());
        sumField = new TextField($(transfer_sum_field_caption));
//         sumField.addValidator(new NullValidator("Please, submit sum field", false));
//         sumField.addValidator(new IntegerRangeValidator("Invalid value",1,99999));
        confirm = new Button($(transfer_confirm_field_caption));
        confirm.addStyleName(ValoTheme.BUTTON_PRIMARY);
        confirm.focus();

        notifLable = new Label($(transfer_notif_label));
        createConfimPopuWindow();

        confirm.addClickListener(e -> {
            try {
                listener.onSubmit(e);
            } catch (Exception exc) {
                ErrorHandler.handle(exc);
            }
        });

        requestBonusBtn.addClickListener(e -> {
            listener.requestBonus();
        });
    }

    @Override
    protected void initIds() {
        super.initIds();
        msisdnField.setId(transfer_msisdn_field_caption.name());
        sumField.setId(transfer_sum_field_caption.name());
        confirm.setId(transfer_confirm_field_caption.name());
        notifLable.setId(transfer_notif_label.name());
        submite.setId(transfer_confirm_submit_btn_caption.name());
        cancel.setId(transfer_confirm_cancel_btn_caption.name());
        requestBonusBtn.setId(transfer_request_bonus_caption.name());
    }

    private void hackInputType() {
        HackUtils.setInputType(transfer_msisdn_field_caption.name(), HTML.input.type.tel);
        HackUtils.setInputType(transfer_sum_field_caption.name(), HTML.input.type.number);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        super.enter(viewChangeEvent);
        hackInputType();
    }

    @Override
    public void showConfirmWindow() {
        if (confirmPopupWindows.getParent() == null) {
            getUI().addWindow(confirmPopupWindows);
        }
        FastMsisdn msisdn = new FastMsisdn(msisdnField.getValue());

        confirmLabel.setValue($(transfer_confirm_label, sumField.getValue(), msisdn.format(Msisdn.Format.CANONICAL)));
        confirmPopupWindows.setVisible(true);
    }

    @Override
    protected void buildLayout() {
        super.buildLayout();
        content.setSizeUndefined();
        content.setSpacing(true);
        content.setMargin(new MarginInfo(false, true, true, true));
//        Responsive.makeResponsive(content);

        content.addComponents(notifLable, msisdnField, sumField, confirm/*, requestBonusBtn*/);
        msisdnField.setWidth(Style._100p);
        sumField.setWidth(Style._100p);

    }

    private void createConfimPopuWindow() {
        submite = new Button($(transfer_confirm_submit_btn_caption), e -> {
            confirmPopupWindows.setVisible(false);
            listener.onConfirmSubmit(e);
        });

        cancel = new Button($(transfer_confirm_cancel_btn_caption), e -> {
            confirmPopupWindows.setVisible(false);
            showNotification($(transfer_reject_notify), Notification.Type.ASSISTIVE_NOTIFICATION);
        });

        confirmLabel = new Label("Are you  sure transfer ?", ContentMode.HTML);

        confirmPopupWindows = new Window();
        confirmPopupWindows.setModal(true);
        confirmPopupWindows.setClosable(false);
        confirmPopupWindows.setDraggable(false);
        confirmPopupWindows.setResizable(false);
        confirmPopupWindows.setHeight(12, Unit.EM);
        confirmPopupWindows.setWidth(17, Unit.EM);
        confirmPopupWindows.setWindowMode(WindowMode.NORMAL);
//        confirmPopupWindows.setPositionX(1);
//        confirmPopupWindows.setPositionY(1);
        confirmPopupWindows.center();

        // layout and style
        submite.setStyleName(ValoTheme.BUTTON_PRIMARY);

        confirmLabel.setWidthUndefined();

        VerticalLayout row = new VerticalLayout();
        row.setSizeFull();
        row.setSpacing(true);
        row.setMargin(true);
        row.setHeightUndefined();

//        confirmLabel.setStyleName("h2");
        HorizontalLayout col = new HorizontalLayout();
        col.setSizeFull();
        col.setSpacing(true);
        col.addComponents(submite, cancel);

        row.addComponent(confirmLabel);
        row.addComponent(col);
        row.setComponentAlignment(confirmLabel, Alignment.MIDDLE_CENTER);
        row.setComponentAlignment(col, Alignment.MIDDLE_CENTER);

        confirmPopupWindows.setContent(row);
    }

    @Override
    public String getMsisdn() {
        return msisdnField.getValue();
    }

    @Override
    public void setMsisdn(String string) {
        msisdnField.setValue(string);
    }

    @Override
    public Integer getSum() {
        try {
            return Integer.valueOf(sumField.getValue());
        } catch (java.lang.NumberFormatException e) {
            // todo выдать предупреждение о том что неверное значеие
            return 0;
        }
    }

    @Override
    public void setSum(String sum) {
        sumField.setValue(sum);
    }

}
