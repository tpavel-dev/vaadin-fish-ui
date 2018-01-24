package kz.kcell.apps.fish.mobile.vaadin.controller;


import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;
import kz.kcell.apps.bonus_cmdr.model.AccessGroup;
import kz.kcell.apps.bonus_cmdr.model.AccessGroupUtils;
import kz.kcell.apps.common.msisdn.Msisdn;
import kz.kcell.apps.fish.audit.Audit;
import kz.kcell.apps.fish.mobile.vaadin.SpmotWebConfig;
import kz.kcell.apps.fish.mobile.vaadin.data.Account;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.impl.ValoMainUIImpl;
import kz.kcell.vaadin.ui.Controller;
import kz.kcell.vaadin.ui.EventBus;
import kz.kcell.vaadin.ui.EventType;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static kz.kcell.vaadin.ui.EventType.*;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 16 09 2014
 */
@SpringComponent
@UIScope
@Slf4j
public class UIController implements Controller, EventBus {

    //    @Autowired
    @Setter
    private MainUIPresenter mainUIPresenter;

    @Autowired
    private SpmotWebConfig webConfig;

    @Autowired
    private Audit audit;

    @Setter
    private LoginPresenter loginPresenter;

    //    private ViewsCode captchaBlockedView = null;
    private EventType captchaBlockedEvent;

    public UIController() {
    }

    @PostConstruct
    public void postConstructor() {
//        mainUIPresenter.setEventBus(this);
    }

    public ValoMainUIImpl getView() {
        return (ValoMainUIImpl) UI.getCurrent();
    }

    public void post(EventType eventType) {
        log.info("post event: {}", eventType);
//        audit.registrationEvent(eventType);

        /*if (isNotCaptchaBlockedState() && eventType != BLOCK_CAPTCHA && audit.isWarrantForBlockWithCaptcha()) {
//            post(BLOCK_CAPTCHA);
            captchaBlockedEvent = eventType;
            eventType = BLOCK_CAPTCHA;
//            return;
        }*/

        if (isCaptchaBlockedState() && (eventType != BLOCK_CAPTCHA && eventType != CAPCTHA_PASS)) {
//            post(BLOCK_CAPTCHA);
            captchaBlockedEvent = eventType;
            eventType = BLOCK_CAPTCHA;
//            return;
        }

        if (isNotAuthorizedState() &&
                (eventType != LOGIN_FAILED &&
                        eventType != START_SESSION &&
                        eventType != SHOW_LOGIN_SCREEN &&
                        eventType != BLOCK_CAPTCHA &&
                        eventType != CAPCTHA_PASS)) {
            eventType = SHOW_LOGIN_SCREEN;
//            post(SHOW_LOGIN_SCREEN);
//            return;
        }

        log.info("process event: {}", eventType);

        switch (eventType) {
            case CAPCTHA_PASS: {
                if (webConfig.getCaptcha().enabled) {
                    getAccount().unblockCaptcha();
//                    ViewsCode savedNavigatedView = captchaBlockedView;
//                    captchaBlockedView = null;
                    audit.clearWarrantForBlockWithCaptcha();
//                    if(savedNavigatedView != null) {
//                        mainUIPresenter.navigateTo(savedNavigatedView);
//                    }
                    post(captchaBlockedEvent);
                }
                break;
            }
            case BLOCK_CAPTCHA: {
                if (webConfig.getCaptcha().enabled) {
                    getAccount().blockCaptcha();
//                    captchaBlockedView = ViewsCode.valueOf(UI.getCurrent().getNavigator().getState());
                    mainUIPresenter.navigateTo(ViewsCode.captcha);
                }
                break;
            }
            case LOGIN_FAILED: {
                mainUIPresenter.navigateTo(ViewsCode.login);
                break;
            }
            case LOGIN_SUCCESFULL: {
                mainUIPresenter.loginSuccessful();
                if (AccessGroupUtils.checkAccess(
                        Arrays.asList(AccessGroup.BONUS_CMDR_SUPERVISOR.name(), AccessGroup.BONUS_CMDR_EXECUTOR.name()),
                        getAccount().getUser().getAccessGroups())) {
                    post(EventType.SHOW_COMPANIES_SCREEN);
                } else if (AccessGroupUtils.checkAccess(
                        AccessGroup.BONUS_CMDR_UPLOADER.name(),
                        getAccount().getUser().getAccessGroups())) {
                    post(EventType.SHOW_UPLOAD_SCREEN);
                } else {
                    post(EventType.SHOW_PRODUCT_SCREEN);
                }
                break;
            }
            case SHOW_UPLOAD_SCREEN: {
                mainUIPresenter.navigateTo(ViewsCode.upload_file);
                break;
            }
            case EXIT: {
                post(EventType.SHOW_LOGOUT_SCREEN);
                break;
            }
            case LOGOUT_SUCCESS: {
                SessionManager.closeSession();
                VaadinSession.getCurrent().close();
                Page.getCurrent().setLocation(VaadinService.getCurrentRequest().getContextPath());
                break;
            }
            case START_SESSION: {
                post(SHOW_LOGIN_SCREEN);
//                mainUIPresenter.navigateTo(ViewsCode.login);
                break;
            }
            case PRODUCT_SELECTED: {
                mainUIPresenter.navigateTo(ViewsCode.subscribeForm);
                // todo ???
//                try {
//                    log.info("select product {}:\"{}\""
//                            , productNavigatorPresenter.getSelectedBusinessProduct().getId()
//                            , productNavigatorPresenter.getSelectedBusinessProduct().getNameEn());
//                } catch (Exception noting) {
//                }

                break;
            }
            case SHOW_PRODUCT_DESC: {
                mainUIPresenter.navigateTo(ViewsCode.productDesc);
                break;
            }
            case SHOW_LOGOUT_SCREEN: {
                mainUIPresenter.navigateTo(ViewsCode.logout);
                break;
            }
            case SHOW_SUBSCRIBE_LOG_SCREEN: {
                mainUIPresenter.navigateTo(ViewsCode.log);
                break;
            }
            case SHOW_TRANSFER_BONUS_SCREEN: {
                mainUIPresenter.navigateTo(ViewsCode.transfer);
                break;
            }
            case SHOW_PRODUCT_SCREEN: {
                mainUIPresenter.navigateTo(ViewsCode.services);
                break;
            }
            case SHOW_LOGIN_SCREEN: {
                mainUIPresenter.navigateTo(ViewsCode.login);
                break;
            }
            case SUBSCRIBE_SERVICE: {
                mainUIPresenter.navigateTo(ViewsCode.subscribeIvr);
                break;
            }
            case UPDATE_BALANCE:
            case TRANSFER_BONUS_SUCCESS: {
                mainUIPresenter.updateBalance();
//                bonusesInfoPresenter.updateBalance();
                break;
            }
            case TRANSLATE: {
                log.info("translate");
                loginPresenter.translate();
//                capcthaPresenter.translate();
//                logoutPresenter.translate();
//                mainMenuPresenter.translate();
                // todo ??? может перенести внурь
                mainUIPresenter.translate();
//                productNavigatorPresenter.translate();
//                transferBonusPresenter.translate();
//                bonusesInfoPresenter.translate();
//                dashboardPresenter.translate();
                break;
            }
            case ENTER_SUBSCRIBE_IVR_FORM_IMPOSSIBLE:
            case ENTER_SUBSCRIBE_IVR_VIEW_IMPOSSIBLE: {
                post(EventType.SHOW_PRODUCT_SCREEN);
                break;
            }
            case SHOW_COMPANIES_SCREEN: {
                mainUIPresenter.navigateTo(ViewsCode.companies);
                break;
            }
            case SHOW_COMPANY_SCREEN: {
                mainUIPresenter.navigateTo(ViewsCode.company);
                break;
            }
        } // end switch
    }

    public boolean beforeViewChange(ViewsCode state) {
        if (isCaptchaBlockedState()) {
            if (state != ViewsCode.captcha) {
                post(BLOCK_CAPTCHA);
                return false;
            }
        } else {
            if (isAuthorizedState()) {
                // if have login but it authorized then first do logout
                if (state == ViewsCode.login) {
                    post(SHOW_LOGOUT_SCREEN);
                }
                if (state == ViewsCode.upload_file) {
                    return AccessGroupUtils.checkAccess(
                            AccessGroup.BONUS_CMDR_UPLOADER.name(),
                            getAccount().getUser().getAccessGroups());
                }
                if (state == ViewsCode.companies) {
                    return AccessGroupUtils.checkAccess(
                            Arrays.asList(AccessGroup.BONUS_CMDR_SUPERVISOR.name(), AccessGroup.BONUS_CMDR_EXECUTOR.name()),
                            getAccount().getUser().getAccessGroups());
                }
            } else {
                // if not authorized then go to login
                if (state != ViewsCode.login) {
                    post(SHOW_LOGIN_SCREEN);
                }
            }
        }
        return true;
    }

    public void afterViewChange(ViewsCode state) {
        if (state == ViewsCode.login || state == ViewsCode.captcha) {
            mainUIPresenter.setFullScreen();
        } else {
            mainUIPresenter.offFullScreen();
        }
    }

    private void eventNotAllowedForThisState(EventType eventType) {
        log.error("Event {} not allowed for state {}", eventType, getAccount().getStateInfo());

    }

    protected Account getAccount() {
        return SessionManager.getAccount();
    }

    private Msisdn getCurrentUserId() {
        return getAccount().getMsisdn();
    }

    private boolean isCaptchaBlockedState() {
            return false;
//        return webConfig.getCaptcha().enabled && getAccount().isCaptchaBlocked();
    }

    private boolean isNotCaptchaBlockedState() {
        return getAccount().isCaptchaBlocked() == false;
    }

    private boolean isNotAuthorizedState() {
        return isAuthorizedState() == false;
    }

    private boolean isAuthorizedState() {
        return getAccount().isAuthorized();
    }

    private boolean isUnblockedState() {
        return isAuthorizedState() && !isCaptchaBlockedState();
    }

}
