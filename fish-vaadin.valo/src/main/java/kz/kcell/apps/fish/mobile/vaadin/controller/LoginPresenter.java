package kz.kcell.apps.fish.mobile.vaadin.controller;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.UI;
import kz.kcell.apps.common.Language;
import kz.kcell.apps.common.exceptions.BaseException;
import kz.kcell.apps.common.msisdn.FastMsisdn;
import kz.kcell.apps.fish.exceptions.InvalidValueException;
import kz.kcell.apps.fish.logging.MDCKeys;
import kz.kcell.apps.fish.mobile.vaadin.RequestParams;
import kz.kcell.apps.fish.mobile.vaadin.annotation.SpringPresenter;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.LoginView;
import kz.kcell.vaadin.CookiesUtils;
import kz.kcell.vaadin.data.EmptyStringValidator;
import kz.kcell.vaadin.data.MsisdnValidator;
import kz.kcell.vaadin.ui.EventType;
import kz.kcell.vaadin.ui.Presenter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;

import javax.annotation.PostConstruct;
import java.util.EventObject;

import static kz.kcell.apps.fish.domain.spmot.entity.SpmotResourceBundle.*;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 18 09 2014
 */
//@Stateful
//@Alternative
//@SessionScoped
@Slf4j
@SpringPresenter
public class LoginPresenter extends AbstractPresenter<LoginView> implements Presenter, LoginView.LoginViewListener {

    private static String PASSWORD_COOKIE_NAME = "lsd";
    private static String MSISDN_COOKIE_NAME = "psd";
    private static String SALT = "kasdjv9334kjn34f";
    // 15 days
    private static int PASSWORD_COOKIE_EXPIRE_SEC = 60*60*24*15;

    @Getter @Setter
    private LoginView view;

    @Autowired
    private UIController eventBus;

    public LoginPresenter() {
    }

    @PostConstruct
    public void init() {
        eventBus.setLoginPresenter(this);
    }

    @Override
    public void onSubmit(EventObject source) throws BaseException, InvalidValueException {

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
//        restTemplateBuilder.


        String password = view.getPassword();
        EmptyStringValidator.validate($(login_validate_msg_enter_msisdn), view.getUserName());
        MsisdnValidator.validate($(login_validate_msg_unvalid_msisdn), view.getUserName());
        EmptyStringValidator.validate($(login_validate_msg_enter_password), password);
        FastMsisdn msisdn = new FastMsisdn(view.getUserName());

        MDC.put(MDCKeys.msisdn.name(),msisdn.get());
        getAccount().setMsisdn(msisdn);


        getAccount().setAuthorized(true);

        doRememberPassword(password, msisdn);

        eventBus.post(EventType.LOGIN_SUCCESFULL);
    }

    public void doRememberPassword(String password, FastMsisdn msisdn) {
        try {
            if (view.isRememberPassword()) {
                CookiesUtils.setPasswordInCookie(msisdn.get(), password);
                log.info("Save password in cookie");
            } else {
                CookiesUtils.resetPasswordInCookie();
                log.info("Reset password in cookie");
            }
        } catch (Exception exc) {
            log.error(exc.getLocalizedMessage(), exc);
        }
    }


    @Override
    public void display(UI ui) {
//        ui.setContent(view);
    }

    @Override
    public void onChangeLang(Language language) {
        SessionManager.getAccount().setLang(language);
        eventBus.post(EventType.TRANSLATE);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        initMsisdnPasswordFieldsFromParams();
//        initMsisdnPasswordFromEnv();
        view.showNotification(null, null);
    }

    private void initMsisdnPasswordFieldsFromParams() {
        try {
            String msisdn = VaadinService.getCurrentRequest().getParameter(RequestParams.msisdn.name());
            if(msisdn != null) view.setUserName(msisdn);
        } catch(Exception nothing) {

        }
        try {
            String password = VaadinService.getCurrentRequest().getParameter(RequestParams.password.name());
            if(password != null) view.setPassword(password);
        } catch(Exception nothing) {

        }
        try {
            CookiesUtils.dumpListCookies();
            view.setRemeberPassword(CookiesUtils.hasPasswordInCookie());
            if(CookiesUtils.hasPasswordInCookie()) {
                view.setUserName(CookiesUtils.getMsisdnFromCookie());
                String password = CookiesUtils.getPasswordEncryptedValueFromCookie();
                view.setPassword(password);
            }
        } catch (Exception nothing) {

        }
    }

    @Override
    public void translate() {
        view.translate();
    }
}
