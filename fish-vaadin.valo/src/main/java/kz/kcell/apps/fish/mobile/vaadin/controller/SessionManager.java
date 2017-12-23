package kz.kcell.apps.fish.mobile.vaadin.controller;

import com.vaadin.server.RequestHandler;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import kz.kcell.apps.fish.domain.spmot.entity.AccessGroup;
import kz.kcell.apps.fish.domain.spmot.entity.AccessGroupUtils;
import kz.kcell.apps.fish.logging.MDCKeys;
import kz.kcell.apps.fish.mobile.vaadin.data.Account;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 04 11 2014
 */
@Slf4j
public final class SessionManager {

    public static Account getAccount() {
        Account account;
        VaadinSession session = VaadinSession.getCurrent();

        if (session == null) {
            return null;
        }

        try {
            session.lock();
            account = session.getAttribute(Account.class);

            if (account == null) {
                account = createEmptyAccount();
                session.setAttribute(Account.class, account);
            }

        } finally {
            session.unlock();
        }

        return account;
    }

    public static Account createEmptyAccount() {
        return new Account();
    }

    public static boolean isAuthorized() {
        Account account = getAccount();
        return account != null && account.isAuthorized();
    }

    public static void closeSession() {
        VaadinSession session = UI.getCurrent().getSession();
        session.setAttribute(Account.class, null);
        UI.getCurrent().getSession().close();
    }

    public static String info() {
        Account account = getAccount();
        return String.format("%4$s : %1$s : %2$s : %3$s"
                , account.getLang()
                , account.getMsisdn()
                , account.getBonus()
                , account.isAuthorized() ? " Authorized" : "Non authorized"
        );
    }

    public static RequestHandler setupMDC_RequestHandler() {
        return (session1, request, response) -> {
            if (getAccount().getMsisdn() != null) {
                MDC.put(MDCKeys.msisdn.name(), getAccount().getMsisdn().get());
            }
            return false;
        };
    }

    public static boolean isAccessGroupAvailable(AccessGroup accessGroup) {
        boolean access = AccessGroupUtils.checkAccess(getAccount().getDealer().getAccess_group(), accessGroup);
        return access;
    }

    public static boolean isAccessGroupNotavailable(AccessGroup accessGroup) {
        return isAccessGroupAvailable(accessGroup) == false;
    }
}
