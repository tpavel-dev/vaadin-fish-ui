package kz.kcell.apps.fish.mobile.vaadin;

import kz.kcell.apps.common.Language;
import kz.kcell.apps.common.resource.ResourceBundle;
import kz.kcell.apps.fish.resources.ResourceManager;
import kz.kcell.apps.fish.mobile.vaadin.controller.SessionManager;
import kz.kcell.apps.fish.mobile.vaadin.data.Account;

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
