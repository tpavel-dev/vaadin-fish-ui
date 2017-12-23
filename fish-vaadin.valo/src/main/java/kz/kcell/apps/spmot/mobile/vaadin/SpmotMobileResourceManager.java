package kz.kcell.apps.spmot.mobile.vaadin;

import kz.kcell.apps.common.Language;
import kz.kcell.apps.common.resource.ResourceBundle;
import kz.kcell.apps.spmot.resources.ResourceManager;
import kz.kcell.apps.spmot.mobile.vaadin.controller.SessionManager;
import kz.kcell.apps.spmot.mobile.vaadin.data.Account;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 04 2016
 */
//@Component
public class SpmotMobileResourceManager extends ResourceManager {

    public static String $(ResourceBundle resourceBundle, Object... args) {
        return String.format($(detectLang(), resourceBundle, args), args);
//        return MessageFormat.format($(detectLang(), resourceBundle, args), args);
    }

    private static Language detectLang() {
        Account account = SessionManager.getAccount();
        if(account == null) {
            return Language.RU;
        } else {
            return account.getLang();
        }
    }

}
