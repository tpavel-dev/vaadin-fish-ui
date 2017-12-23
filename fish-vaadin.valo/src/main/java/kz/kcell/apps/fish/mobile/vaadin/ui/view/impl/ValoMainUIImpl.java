package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kz.kcell.apps.common.msisdn.FastMsisdn;
import kz.kcell.apps.common.msisdn.Msisdn;
import kz.kcell.apps.fish.mobile.vaadin.ui.MainUI;
import kz.kcell.apps.fish.mobile.vaadin.ui.TestIcon;
import kz.kcell.apps.fish.mobile.vaadin.ui.ValoMenuLayout;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.Property;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import kz.kcell.vaadin.ui.Style;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static kz.kcell.apps.fish.domain.spmot.entity.SpmotResourceBundle.*;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 27 10 2014
 */
//@Theme("tests-valo")
@Theme("tsr")
//@Theme("dashboard")
@Title("Sales Point Motivation")
//@Widgetset("kz.kcell.apps.spmot.mobile.vaadin.ui.AppWidgetSet")
@Slf4j
//@SpringUI
@SpringUI(path = "/spmot-mobile")
public class ValoMainUIImpl extends MainUI  {

    @Getter
    private FastMsisdn msisdn = new FastMsisdn("77777777777");
    private boolean themeSelectShowEnable = false;
    private boolean testMode = false;
    private boolean settingsShowEnable = false;
    private boolean developMode = false;
    private Label title;
    private Label msisdnLabel = new Label();

    private Button main_menu_button_product;
    private Button main_menu_button_subscribe_log;
    private Button main_menu_button_transfer_bonus;
    private Button main_menu_button_bonus_info;
    private Button main_menu_button_change_lang;
    private Button main_menu_button_captcha;
    private Button main_menu_button_out;
    private Button main_menu_button_login;
    private Button main_menu_button_bonus;

    private Button showMenu;


//    @Autowired
    @Getter @Setter
    private kz.kcell.apps.fish.mobile.vaadin.ui.ErrorHandler errorHandler;

    @Autowired
    @Getter
//    @Setter
    private Listener listener;

//    @Autowired
//    private SpringNavigator nav;

//    @Autowired
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private static LinkedHashMap<String, String> themeVariants = new LinkedHashMap<String, String>();

    static {
        themeVariants.put("tests-valo", "Default");
        themeVariants.put("tests-valo-blueprint", "Blueprint");
        themeVariants.put("tests-valo-dark", "Dark");
        themeVariants.put("tests-valo-facebook", "Facebook");
        themeVariants.put("tests-valo-flatdark", "Flat dark");
        themeVariants.put("tests-valo-flat", "Flat");
        themeVariants.put("tests-valo-light", "Light");
        themeVariants.put("tests-valo-metro", "Metro");
        themeVariants.put("tsr", "Telesonera");
        themeVariants.put("dashboard", "Dashboard");
        themeVariants.put("tsr-ru", "RU");
        themeVariants.put("tsr-kx", "KZ");
    }

    private final TestIcon testIcon = new TestIcon(100);

    @Autowired
    ValoMenuLayout root;// = new ValoMenuLayout();
//    VerticalLayout root_fs = new VerticalLayout();

    CssLayout menu = new CssLayout();
    CssLayout menuItemsLayout = new CssLayout();

    {
        menu.setId("testMenu");
    }

    private final LinkedHashMap<String, Button> menuItems = new LinkedHashMap<String, Button>();

    public ValoMainUIImpl() {
    }

    @PostConstruct
    public void postConstructor() {
        listener.setView(this);
        getPage().setTitle($(page_title));
        setContent(root);
        root.setWidth(Style._100p);
        root.addMenu(buildMenu());
//        nav.init(this, getComponentContainer());
    }

    @Autowired
    private SpringViewProvider viewProvider;

    @Override
    protected void init(final VaadinRequest request) {
        viewProvider.setAccessDeniedViewClass(LoginViewImpl.class);
        getNavigator().setErrorView(ErrorViewImpl.class);

        UI.getCurrent().getSession().setErrorHandler(errorHandler);
//        SessionManager.initRequestHandler();
        initConstatnt(request);
        processTestMode();
        Responsive.makeResponsive(this);
        hackForIE9();
        //addGoogleAnalitycTracker();
        // todo !!!!!!!!
        initFirstState();
    }


    /*
    private void addGoogleAnalitycTracker() {
        GoogleAnalyticsTracker tracker = new GoogleAnalyticsTracker("UA-658457-8", "d");
        tracker.extend(this);
    }
    */

    private void processTestMode() {
        if (testMode) {
            Show_v_app_loading_valo_menu_badge();

            if (browserCantRenderFontsConsistently()) {
                getPage().getStyles().add(
                        ".v-app.v-app.v-app {font-family: Sans-Serif;}");
            }
        }
    }

    private void initConstatnt(VaadinRequest request) {
        testMode = request.getParameter("test") != null;
        themeSelectShowEnable = request.getParameter("themeselect") != null;
        settingsShowEnable = request.getParameter("settings") != null;
    }

    private void hackForIE9() {
        if (getPage().getWebBrowser().isIE()
                && getPage().getWebBrowser().getBrowserMajorVersion() == 9) {
            menu.setWidth("320px");
        }
    }

    private void initFirstState() {
//        IoController ioController = IoController.newSessionScopeInstance(this);
//        ioController.build();

        if(listener != null) {
            listener.initFerstState();
        }
    }

    @Override
    public void setFullScreen() {
        if (developMode) return;
        log.debug("Set FULLSCREEN mode");
        root.hideMainMenu();
    }

    @Override
    public void offFullScreen() {
        log.debug("Exit from FULLSCREEN mode");
        root.showMainMenu();
    }

    @Override
    public void selectMenuItem(String viewName) {
        // for all menu item remove selected style
        menuItemsLayout.forEach(p -> p.removeStyleName("selected"));

        for (final Map.Entry<String, Button> item : menuItems.entrySet()) {
            if (viewName.equals(item.getKey())) {
                for (final Iterator<Component> it = menuItemsLayout.iterator(); it.hasNext(); ) {
                    final Component c = it.next();
                    if (c.getCaption() != null && c.getCaption().startsWith(item.getValue().getCaption())) {
                        c.addStyleName("selected");
                        break;
                    }
                }
                break;
            }
        }
        menu.removeStyleName("valo-menu-visible");
    }


    @Override
    public void translate() {
        main_menu_button_product.setCaption($(main_menu_product));
        main_menu_button_subscribe_log.setCaption($(main_menu_subscribe_log));
        main_menu_button_transfer_bonus.setCaption($(main_menu_transfer_bonus));
        main_menu_button_change_lang.setCaption($(main_menu_change_lang));
        main_menu_button_captcha.setCaption("captcha");
        main_menu_button_out.setCaption($(main_menu_out));
        showMenu.setCaption($(menu_button));

//        updateTitle();
    }

    private void Show_v_app_loading_valo_menu_badge() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    private boolean browserCantRenderFontsConsistently() {
        // PhantomJS renders font correctly about 50% of the time, so
        // disable it to have consistent screenshots
        // https://github.com/ariya/phantomjs/issues/10592

        // IE8 also has randomness in its font rendering...

        return getPage().getWebBrowser().getBrowserApplication()
                .contains("PhantomJS")
                || (getPage().getWebBrowser().isIE() && getPage()
                .getWebBrowser().getBrowserMajorVersion() <= 9);
    }

    @Override
    public ComponentContainer getComponentContainer() {
        return root.getContentContainer();
    }


    private CssLayout buildMenu() {
        // Add items
/*
        main_menu_button_product        = new Button(main_menu_product        ), e-> {navigator.navigateTo(State.services.name()   );});
        main_menu_button_subscribe_log  = new Button(main_menu_subscribe_log  ), e-> {navigator.navigateTo(State.log.name()        );});
        main_menu_button_transfer_bonus = new Button(main_menu_transfer_bonus ), e-> {navigator.navigateTo(State.transfer.name()   );});
        main_menu_button_bonus_info     = new Button(main_menu_bonus_info     ), e-> {navigator.navigateTo(State.bonuses_info.name());});
        main_menu_button_change_lang    = new Button(main_menu_change_lang    ), e-> {navigator.navigateTo(State.change_lang.name());});
        main_menu_button_out            = new Button(main_menu_out            ), e-> {navigator.navigateTo(State.logout.name()     );});
//        main_menu_button_login          = new Button("Вход"                       , e-> {navigator.navigateTo(State.login.name()      );});
//        main_menu_button_bonus          = new Button("Мои бонусы"                 , e-> {navigator.navigateTo(State.my_balance.name() );});
*/

        main_menu_button_product = new Button($(main_menu_product),               e -> getNavigator().navigateTo(ViewsCode.services.name()));
        main_menu_button_subscribe_log = new Button($(main_menu_subscribe_log),   e -> getNavigator().navigateTo(ViewsCode.log.name()));//listener.onClick(ViewsCode.log));
        main_menu_button_transfer_bonus = new Button($(main_menu_transfer_bonus), e -> getNavigator().navigateTo(ViewsCode.transfer.name()));////listener.onClick(ViewsCode.transfer));
        main_menu_button_bonus_info = new Button($(main_menu_bonus_info),         e -> getNavigator().navigateTo(ViewsCode.bonuses_info.name()));////listener.onClick(ViewsCode.bonuses_info));
        main_menu_button_change_lang = new Button($(main_menu_change_lang),       e -> getNavigator().navigateTo(ViewsCode.change_lang.name()));////listener.onClick(ViewsCode.change_lang));
        main_menu_button_out = new Button($(main_menu_out),                       e -> getNavigator().navigateTo(ViewsCode.logout.name()));////listener.onClick(ViewsCode.logout));
        main_menu_button_captcha = new Button("captcha",                   e -> getNavigator().navigateTo(ViewsCode.captcha.name()));//listener.onClick(ViewsCode.captcha));

        main_menu_button_product.setId(main_menu_product.name());
        main_menu_button_subscribe_log.setId(main_menu_subscribe_log.name());
        main_menu_button_transfer_bonus.setId(main_menu_transfer_bonus.name());
        main_menu_button_bonus_info.setId(main_menu_bonus_info.name());
        main_menu_button_change_lang.setId(main_menu_change_lang.name());
        main_menu_button_out.setId(main_menu_out.name());
        main_menu_button_captcha.setId("captcha");

        menuItems.put(ViewsCode.services.name(), main_menu_button_product);
        menuItems.put(ViewsCode.log.name(), main_menu_button_subscribe_log);
        menuItems.put(ViewsCode.bonuses_info.name(), main_menu_button_bonus_info);
        menuItems.put(ViewsCode.transfer.name(), main_menu_button_transfer_bonus);
        menuItems.put(ViewsCode.change_lang.name(), main_menu_button_change_lang);
        menuItems.put(ViewsCode.logout.name(), main_menu_button_out);

        if (developMode) {
            menuItems.put(ViewsCode.login.name(), main_menu_button_login);
            menuItems.put(ViewsCode.login.name(), main_menu_button_captcha);

        }

        final HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        top.addStyleName("valo-menu-title");
        menu.addComponent(top);
        if (isThemeSelectEnable()) {
            menu.addComponent(createThemeSelect());
        }

        createShowMenu();
        menu.addComponent(showMenu);

        title = new Label($(main_title), ContentMode.HTML);
        title.setSizeUndefined();
        top.addComponent(title);
        top.setExpandRatio(title, 1);


        if (settingsShowEnable) {
            menu.addComponent(createSettingsMenu());
        }
//        menu.addComponent(msisdnLabel);
        menuItemsLayout.setPrimaryStyleName("valo-menuitems");
        menu.addComponent(menuItemsLayout);

        Label label = null;
        int count = -1;

        msisdnLabel.setContentMode(ContentMode.HTML);
        msisdnLabel.setPrimaryStyleName("valo-menu-subtitle");
        msisdnLabel.addStyleName("h3");
        msisdnLabel.setSizeUndefined();
        msisdnLabel.setId(msisdn_menu_title.name());
        menuItemsLayout.addComponent(msisdnLabel);

        for (final Map.Entry<String, Button> item : menuItems.entrySet()) {
/*
            if (item.getKey().equals("labels")) {
                label = new Label("Components", ContentMode.HTML);
                label.setPrimaryStyleName("valo-menu-subtitle");
                label.addStyleName("h4");
                label.setSizeUndefined();
                menuItemsLayout.addComponent(label);
            }
            if (item.getKey().equals("panels")) {
                label.setValue(label.getValue()
                        + " <span class=\"valo-menu-badge\">" + count
                        + "</span>");
                count = 0;
                label = new Label("Containers", ContentMode.HTML);
                label.setPrimaryStyleName("valo-menu-subtitle");
                label.addStyleName("h4");
                label.setSizeUndefined();
                menuItemsLayout.addComponent(label);
            }
            if (item.getKey().equals("forms")) {
                label.setValue(label.getValue()
                        + " <span class=\"valo-menu-badge\">" + count
                        + "</span>");
                count = 0;
                label = new Label("Other", ContentMode.HTML);
                label.setPrimaryStyleName("valo-menu-subtitle");
                label.addStyleName("h4");
                label.setSizeUndefined();
                menuItemsLayout.addComponent(label);
            }
*/

/*
            if (count == -1) {
                b.setCaption(b.getCaption()
                        + " <span class=\"valo-menu-badge\">123</span>");
            }
*/

            item.getValue().setHtmlContentAllowed(true);
            item.getValue().setPrimaryStyleName("valo-menu-item");
//            b.setIcon(testIcon.get());
            menuItemsLayout.addComponent(item.getValue());
            count++;
        }
/*
        if (label != null) {
            label.setValue(String.format("%s <span class=\"valo-menu-badge\">%d</span>", label.getValue(), count));
        }
*/

        return menu;
    }

    private void createShowMenu() {
        showMenu = new Button($(menu_button), (Button.ClickListener) event -> {
//                navigator.navigateTo(State.dashboard.name());
            listener.onClick(ViewsCode.dashboard);
/*
            if (menu.getStyleName().contains("valo-menu-visible")) {
                menu.removeStyleName("valo-menu-visible");
            } else {
                menu.addStyleName("valo-menu-visible");
            }
*/
        });
        showMenu.addStyleName(ValoTheme.BUTTON_PRIMARY);
        showMenu.addStyleName(ValoTheme.BUTTON_SMALL);
//        showMenu.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        showMenu.addStyleName("valo-menu-toggle");
        showMenu.setIcon(FontAwesome.LIST);
//        showMenu.setWidth(15, Unit.EM);
        showMenu.setId(menu_button.name());
//        showMenu.setHeight(3, Unit.EM);
    }

    private MenuBar createSettingsMenu() {
        final MenuBar settingsMenuBar = new MenuBar();
        settingsMenuBar.addStyleName("user-msenu");

        final MenuBar.MenuItem settingsItem = settingsMenuBar.addItem(getMsisdn().format(Msisdn.Format.COMPACT),
                new ThemeResource("../tests-valo/img/profile-pic-300px.jpg"),
                null);
        settingsItem.addItem("Edit Profile", null);
        settingsItem.addItem("Preferences", null);
        settingsItem.addSeparator();
        settingsItem.addItem("Sign Out", null);
        return settingsMenuBar;
    }

    private boolean isThemeSelectEnable() {
        return themeSelectShowEnable;
    }

    private Component createThemeSelect() {
        final NativeSelect ns = new NativeSelect();
        // todo restore
//        ns.setNullSelectionAllowed(false);
        ns.setId("themeSelect");
        // todo restore
//        ns.addContainerProperty("caption", String.class, "");
        // todo restore
//        ns.setItemCaptionPropertyId("caption");
        for (final String identifier : themeVariants.keySet()) {
            // todo restore
//            ns.addItem(identifier).getItemProperty("caption").setValue(themeVariants.get(identifier));
        }

        ns.setValue("tests-valo");
        // todo restore
        ns.addValueChangeListener(new Property.ValueChangeListener() {

            public void valueChange(final Property.ValueChangeEvent event) {
                setTheme((String) ns.getValue());
            }
        });
        return ns;
    }

    // todo вынести этот методв в утилитный, не его обязанность
    @Deprecated
    @Override
    public void showNotification(String msg, Notification.Type typeMsg) {
        listener.showNotification(msg, typeMsg);
    }

/*
    @Override
    public void setBonus(Double bonus) {
        this.bonus = bonus;
        updateTitle();
    }
*/

    @Override
    public void setMsisdn(String msisdnStr) {
        msisdn = new FastMsisdn(msisdnStr);
        msisdnLabel.setValue(msisdn.format(Msisdn.Format.CANONICAL));
//        updateTitle();
    }

}
