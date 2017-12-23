package kz.kcell.apps.spmot.mobile.vaadin.ui.view.impl;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kz.kcell.apps.common.Language;
import kz.kcell.apps.spmot.mobile.vaadin.SpmotWebConfig;
import kz.kcell.apps.spmot.mobile.vaadin.ui.ErrorHandler;
import kz.kcell.apps.spmot.mobile.vaadin.ui.view.LoginView;
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
 * @since 17 09 2014
 */
@SpringView(name = ViewsCode.name_login)
public class LoginViewImpl extends BaseNavigationView implements LoginView {
    public static final String VIEW_NAME = "log";

    @Autowired
    @Getter
    private LoginViewListener listener;

    //    private MaskedTextField userName;
    private TextField userName;

    private PasswordField password;
    private Button submit;
    private Label helpLabel;
    private Label notificationLabel = new Label();
    private CheckBox remeberPasswordCheckBox = new CheckBox();
    private Label plus7Label = new Label("+7");
    private Button ruButton = new Button();
    private Button kkButton = new Button();

//    protected static ResourceBundle myBundle = getBundle("kz.kcell.apps.spmot.mobile.vaadin.spmot");


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
        helpLabel.setValue($(login_help_label));
    }


    @Override
    protected void initIds() {
        super.initIds();
        ruButton.setId(language_name_ru.name());
        kkButton.setId(language_name_kk.name());
        notificationLabel.setId("notification_label");
        remeberPasswordCheckBox.setId(remeber_password_checkbox.name());
//        remeberPasswordCheckBox.setVisible(false);
        userName.setId(login_msisdn_field_caption.name());
        password.setId(login_password_field_caption.name());
        submit.setId(login_submit_button_caption.name());
    }

    @Override
    protected void injectInit() {
        ruButton.setCaption($(language_name_ru));

        kkButton.setCaption($(language_name_kk));

        ruButton.addClickListener(e -> {
            listener.onChangeLang(Language.RU);
        });
        kkButton.addClickListener(e -> {
            listener.onChangeLang(Language.KK);
        });
        ruButton.addStyleName(ValoTheme.BUTTON_LINK);
        kkButton.addStyleName(ValoTheme.BUTTON_LINK);

        notificationLabel.setVisible(false);
        notificationLabel.addStyleName("error_notification");


//        userName = new MaskedTextField("Введите номер продавца (Activ)", "(###) ###-####");
//        userName = new TextField("Введите номер продавца (Activ)");
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
        helpLabel = new Label($(login_help_label), ContentMode.HTML);
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

        helpLabel.setWidth(Style._100p);

        HorizontalLayout h = new HorizontalLayout();
        h.addComponents(kkButton, ruButton);
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
                ,
                helpLabel
        );

        content.addComponent(v);


//        testOpenblankLink(v);
/*
        content.setComponentAlignment(h, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(notificationLabel, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(userName, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(submit, Alignment.MIDDLE_CENTER);
*/
    }

    protected void buildLayoutVerticalFot7() {
        super.buildLayout();

        userName.setWidth(Style._100p);
        password.setWidth(Style._100p);

        HorizontalLayout h = new HorizontalLayout();
        h.addComponents(kkButton, ruButton);
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
        v.addComponents(h, notificationLabel, userName, password, submit, remeberPasswordCheckBox, helpLabel);

        content.addComponent(v);


//        testOpenblankLink(v);
/*
        content.setComponentAlignment(h, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(notificationLabel, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(userName, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(submit, Alignment.MIDDLE_CENTER);
*/
    }


    private void testOpenblankLink(VerticalLayout v) {
        v.addComponent(new Label(" ---- ONLY FOR TEST PERIOD ----"));
        v.addComponent(new Label("<a href=\"http://activ.kz\" target=\"_blank\">link active _blank</a>", ContentMode.HTML));
        v.addComponent(new Label("<a href=\"http://activ.kz\" target=\"_top\">link active _top</a>", ContentMode.HTML));
        v.addComponent(new Label("<a href=\"http://activ.kz\" target=\"_parent\">link active _parent</a>", ContentMode.HTML));
        v.addComponent(new Label("<a href=\"http://activ.kz\" target=\"_self\">link active _self</a>", ContentMode.HTML));
        v.addComponent(new Label("<a href=\"http://activ.kz\" >link active no target</a>", ContentMode.HTML));

        v.addComponent(new Label("<button  onclick=\"window.open('http://activ.kz','_blank ')\">html active _blank   </button>", ContentMode.HTML));
        v.addComponent(new Label("<button  onclick=\"window.open('http://activ.kz','_top   ')\">html active _top     </button>", ContentMode.HTML));
        v.addComponent(new Label("<button  onclick=\"window.open('http://activ.kz','_parent')\">html active _parent  </button>", ContentMode.HTML));
        v.addComponent(new Label("<button  onclick=\"window.open('http://activ.kz','_self  ')\">html active _self    </button>", ContentMode.HTML));
        v.addComponent(new Label("<button  onclick=\"window.open('http://activ.kz','')       \">html active no target</button>", ContentMode.HTML));

        v.addComponent(new NativeButton("native _blank ", e -> Page.getCurrent().open("http://activ.kz", "_blank")));
        v.addComponent(new NativeButton("native _top   ", e -> Page.getCurrent().open("http://activ.kz", "_top")));
        v.addComponent(new NativeButton("native _parent", e -> Page.getCurrent().open("http://activ.kz", "_parent")));
        v.addComponent(new NativeButton("native _self  ", e -> Page.getCurrent().open("http://activ.kz", "_self")));
        v.addComponent(new NativeButton("native ", e -> Page.getCurrent().open("http://activ.kz", "")));


        v.addComponent(new Button("open activ test1 blank  link", e -> {
            Page.getCurrent().open("http://activ.kz", "_blank");
        }));
        v.addComponent(new Button("open activ test2 blank  link", e -> {
            Page.getCurrent().open("http://activ.kz", "_blank", true);
        }));
        v.addComponent(new Button("open activ test3        link", e -> {
            Page.getCurrent().open("http://activ.kz", "");
        }));
        v.addComponent(new Button("open activ test3 self   link", e -> {
            Page.getCurrent().open("http://activ.kz", "_self");
        }));
        v.addComponent(new Button("open activ test3 top    link", e -> {
            Page.getCurrent().open("http://activ.kz", "_top");
        }));
        v.addComponent(new Button("open activ test3 parent link", e -> {
            Page.getCurrent().open("http://activ.kz", "_parent");
        }));
    }


    protected void buildLayoutVertical2() {
        super.buildLayout();
        HorizontalLayout h = new HorizontalLayout();
        h.addComponents(kkButton, ruButton);
        h.setSpacing(false);
        h.setHeightUndefined();
        content.setMargin(new MarginInfo(false, true, false, true));
        content.setSizeUndefined();
        content.setSpacing(true);
//        content.addComponentAsFirst(h);
        content.addComponents(h, notificationLabel, userName, password, submit, helpLabel);
    }


    protected void buildLayoutVerticalResponsive() {
        super.buildLayout();
        content.removeAllComponents();
        VerticalLayout v = new VerticalLayout();
        v.setMargin(new MarginInfo(false, true, false, true));
        v.setSizeUndefined();
        v.setSpacing(true);

        v.addComponents(getTitleLabel(), userName, password, submit);


        HorizontalLayout h = new HorizontalLayout();
        h.setMargin(false);
        h.addComponents(v, helpLabel);
        Responsive.makeResponsive(h);

        content.addComponent(h);
    }


    //    @Override
    protected void buildLayout2() {
        super.buildLayout();
//        content.removeAllComponents();
        VerticalLayout loginPanel = new VerticalLayout();
        content.addComponent(loginPanel);
        setComponentAlignment(loginPanel, Alignment.TOP_CENTER);

        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        Responsive.makeResponsive(loginPanel);

        loginPanel.addComponents(userName, password, submit);
    }

    protected void buildLayoutDashboardThemes() {
        super.buildLayout();
        content.removeAllComponents();
        VerticalLayout loginPanel = new VerticalLayout();
        content.addComponent(loginPanel);
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);

        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        Responsive.makeResponsive(loginPanel);
        loginPanel.addStyleName("login-panel");

        HorizontalLayout fields = new HorizontalLayout();
        Responsive.makeResponsive(fields);
        loginPanel.addComponent(fields);

        fields.setSpacing(true);
        fields.addStyleName("fields");

        submit.addStyleName(ValoTheme.BUTTON_PRIMARY);
        submit.focus();

        fields.addComponents(userName, password, submit);
        fields.setComponentAlignment(submit, Alignment.BOTTOM_LEFT);

/*
        loginPanel.addComponent(userName);
        loginPanel.addComponent(password);
//        content.addComponent(visibledPassword);
        loginPanel.addComponent(submit);
*/

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
