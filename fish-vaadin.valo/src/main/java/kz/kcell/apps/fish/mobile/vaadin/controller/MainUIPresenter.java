package kz.kcell.apps.fish.mobile.vaadin.controller;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.spring.navigator.ViewActivationListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import kz.kcell.apps.common.Language;
import kz.kcell.apps.fish.mobile.vaadin.ui.MainUI;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.*;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.impl.*;
import kz.kcell.vaadin.ui.EventType;
import kz.kcell.vaadin.ui.Presenter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 29 09 2014
 */
@Slf4j
@SpringComponent
@UIScope
public class MainUIPresenter extends AbstractPresenter<MainUI> implements Presenter, MainUI.Listener {

    @Getter
    @Setter
    private ValoMainUIImpl mainUI;
    //    @Autowired
    private LangSwitcherView langSwitcherView;
    @Autowired
//    @Getter @Setter
    private UIController eventBus;
    @Autowired
    private SpringViewProvider viewProvider;

    private TransferBonusesView transferBonusesView;
    //    @Autowired
    private BonusesInfoView bonusesInfoView;
    //    @Autowired
    private ProductNavigatorView productNavigatorView;
    //    @Autowired
    private SubscribeLogView subscribeLogView;
    //    @Autowired
    private LoginView loginView;
    //    @Autowired
    private CaptchaView captchaView;
    //    @Autowired
    private LogoutView logoutView;
    //    @Autowired
    private ErrorView errorView;
    //    @Autowired
    private OrderIvrFormViewImpl subscribeFormView;
    //    @Autowired
    private ProductDescViewImpl productDescView;
    //    @Autowired
    private OrderIvrViewImpl subscribeIvrView;
    //    @Autowired
    private DashboardView dashboardView;
    @Autowired
    private SpringNavigator navigator;

    public MainUIPresenter() {
    }

    public void setMsisdn(String msisdn) {
        mainUI.setMsisdn(msisdn);
    }

    public void setBonus(Double bonus) {
        mainUI.setBonus(bonus);
    }

    @PostConstruct
    public void init() {
        eventBus.setMainUIPresenter(this);
//        mainUI.setListener(this);
        initNavigator();
    }

    private void update() {
        updateBalance();
        setMsisdn(getAccount().getMsisdn().get());
    }

    ///*
    private Navigator initNavigator() {

        navigator.addViewActivationListener((ViewActivationListener) event -> {
            log.trace("view activation {}: {}", event.getViewName(), event.isActivated());
        });

        navigator.addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(final ViewChangeEvent event) {
                log.trace("-- BEFORE ENTER '{}'", event.getViewName());

                ViewsCode state = ViewsCode.valueOf(event.getViewName());
                return eventBus.beforeViewChange(state);
            }

            @Override
            public void afterViewChange(final ViewChangeEvent event) {
                log.info("open view {}", event.getViewName());
                mainUI.selectMenuItem(event.getViewName());

                ViewsCode state = ViewsCode.valueOf(event.getViewName());
                eventBus.afterViewChange(state);
            }
        });

        return navigator;
    }

    public void setFullScreen() {
        mainUI.setFullScreen();
    }

    public void offFullScreen() {
        mainUI.offFullScreen();
    }

    public void updateBalance() {
        // todo disabled
//        getAccount().setBonus(fetchBalance());
//        setBonus( getAccount().getBonus() );
    }

    /**
     * fetch balance from server
     * if throw exception then balance set into null
     */
    public Double fetchBalance() {
        Double balance = null;
        return balance;
    }

    @Deprecated
    public void navigateTo(ViewsCode state) {

/*
        // this hack
        if ((Page.getCurrent() != null && Page.getCurrent().getLocation() != null) == false) {
            // page is not initialized
            // todo init replace init(request)
            return;
        }
*/
        if (navigator.getState().equals(state.name()) == false) {
            UI.getCurrent().getNavigator().navigateTo(state.name());
        }
    }

    @Override
    protected MainUI getView() {
        return mainUI;
    }

    @Override
    public void setView(ValoMainUIImpl components) {
        mainUI = components;
    }

    @Override
    public void onChangeLanguage(Language language) {
        //navigateTo(ValoMainUIImpl.State.change_lang);
        SessionManager.getAccount().setLang(language);
        eventBus.post(EventType.TRANSLATE);
    }

    @Override
    public void initFerstState() {
        eventBus.post(EventType.START_SESSION);
    }

    @Override
    public void onClick(ViewsCode state) {
        // todo restore
        navigator.navigateTo(state.name());
    }

    @Override
    public void showNotification(String msg, Notification.Type typeMsg) {
        if (navigator.getCurrentView() instanceof BaseView) {
            ((BaseView) navigator.getCurrentView()).showNotification(msg, typeMsg);
        }
    }

    public void loginSuccessful() {
//        getMainUI().offFullScreen();
        mainUI.setMsisdn(getAccount().getMsisdn().get());
        update();
    }

    @Override
    public void translate() {
        mainUI.translate();
        // todo ???
//        langSwitcherView.translate();
    }

    private void refreshMsisdnAndBonusLabel() {
        if (SessionManager.getAccount().getMsisdn() != null) {
            setMsisdn(SessionManager.getAccount().getMsisdn().get());
        }
//        setBonus(SessionManager.getAccount().getBonus());
        updateTitle();
    }

    private void updateTitle() {

//        String bonusStr = bonus == null ? "--- "+ SpecSymbol.₸: String.format("%10.2f "+SpecSymbol.₸, bonus);
//        String titleStr = String.format("<h3>%s <strong>%s</strong> %s</h3> ", msisdn.getNDC(), msisdn.getSN(), bonusStr);
//        title.setValue(titleStr);

//        String bonusStr = bonus == null ? main_no_bonus_label.$(): main_bonus_label.$(bonus);
//        title.setValue(bonusStr);
    }

//    public void login() {
//        navigateTo(ViewsCode.login);
//    }
}
