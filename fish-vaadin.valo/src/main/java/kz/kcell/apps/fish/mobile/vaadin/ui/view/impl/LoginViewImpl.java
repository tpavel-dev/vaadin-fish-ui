package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kz.kcell.apps.common.Language;
import kz.kcell.apps.fish.mobile.vaadin.SpmotWebConfig;
import kz.kcell.apps.fish.mobile.vaadin.ui.ErrorHandler;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.LoginView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import kz.kcell.vaadin.HTML;
import kz.kcell.vaadin.HackUtils;
import kz.kcell.vaadin.ui.Style;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.*;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 17 09 2014
 */
@SpringView(name = ViewsCode.name_login)
public class LoginViewImpl extends BaseNavigationView implements LoginView {
    public static final String VIEW_NAME = "log";

    @Autowired
    @Getter
    private LoginViewListener listener;

    private TextField userName;

    private PasswordField password;
    private Button submit;
    private Label notificationLabel = new Label();
    private CheckBox remeberPasswordCheckBox = new CheckBox();

    public LoginViewImpl() {
    }

    @PostConstruct
    public void init() {
        listener.setView(this);
        super.init();
    }

    @Override
    public void translate() {
        setTitle($(login_title));
        userName.setCaption($(login_msisdn_field_caption));
        password.setCaption($(login_password_field_caption));
        remeberPasswordCheckBox.setCaption($(remeber_password_checkbox));
        submit.setCaption($(login_submit_button_caption));
    }


    @Override
    protected void initIds() {
        super.initIds();
        notificationLabel.setId("notification_label");
        remeberPasswordCheckBox.setId(remeber_password_checkbox.name());
//        remeberPasswordCheckBox.setVisible(false);
        userName.setId(login_msisdn_field_caption.name());
        password.setId(login_password_field_caption.name());
        submit.setId(login_submit_button_caption.name());
    }

    @Override
    protected void injectInit() {

        notificationLabel.setVisible(false);
        notificationLabel.addStyleName("error_notification");

        userName = new TextField($(login_msisdn_field_caption));

        // todo restore
        userName.setPlaceholder(SpmotWebConfig.INPUT_PROMPT_CELL);// setInputPrompt(Config.INPUT_PROMPT_CELL);

        password = new PasswordField($(login_password_field_caption));

//        password.addValidator(new EmptyStringValidator("Введите пароль"));

        submit = new Button($(login_submit_button_caption));

        submit.addStyleName(ValoTheme.BUTTON_PRIMARY);
        submit.focus();
        submit.addClickListener(e -> {
            try {
                listener.onSubmit(e);
            } catch (Exception exc) {
                ErrorHandler.handle(exc);
            }
        });
    }

    private void hackInputType() {
        HackUtils.setInputType(login_msisdn_field_caption.name(), HTML.input.type.tel);
//        Page.getCurrent().getJavaScript().execute("document.getElementById('login_msisdn_field_caption').type='tel'");

    }


    @Override
    protected void buildLayout() {
//        buildLayoutVerticalResponsive();
        buildLayoutVertical();
    }

    protected void buildLayoutVertical() {
        super.buildLayout();

        userName.setWidth(Style._100p);
        password.setWidth(Style._100p);

        HorizontalLayout h = new HorizontalLayout();
        h.setSpacing(false);
        h.setHeightUndefined();
        content.setSpacing(false);
        content.setMargin(new MarginInfo(false, true, false, true));
        content.setSizeUndefined();
        VerticalLayout v = new VerticalLayout();

//        v.setMargin(new MarginInfo(false, true, false, true));
        v.setMargin(false);
        v.setSizeUndefined();
        v.setSpacing(true);
//        content.addComponentAsFirst(h);
        v.addComponents(h,
                notificationLabel,
                userName, password,
                submit,
                remeberPasswordCheckBox
        );

        content.addComponent(v);

    }

    @Override
    public String getUserName() {
        return userName.getValue();
    }

    @Override
    public void setUserName(String s) {
        userName.setValue(s);
    }

    @Override
    public String getPassword() {
        return password.getValue();
    }

    @Override
    public void setPassword(String s) {
        password.setValue(s);
    }

    @Override
    public boolean isWithOutAuth() {
        return false;
    }

    @Override
    public boolean isRememberPassword() {
        return remeberPasswordCheckBox.getValue();
    }

    @Override
    public void setRemeberPassword(boolean remeber) {
        remeberPasswordCheckBox.setValue(remeber);
    }

    @Override
    public void showNotification(String msg, Notification.Type typeMsg) {
        ErrorHandler.showNotification(notificationLabel, msg, typeMsg);
/*
        notificationLabel.setValue(msg);
        if(msg == null) {
            notificationLabel.setVisible(false);
            Style.clearNotificationStyle(notificationLabel, typeMsg);
        } else {
            notificationLabel.setVisible(true);
            Style.setNotificationStyle(notificationLabel, typeMsg);
        }
*/
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        super.enter(viewChangeEvent);
        hackInputType();
        listener.enter(viewChangeEvent);
    }

}
