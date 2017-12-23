package kz.kcell.apps.spmot.mobile.vaadin.controller;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Component;
import kz.kcell.apps.spmot.mobile.vaadin.annotation.SpringPresenter;
import kz.kcell.apps.spmot.mobile.vaadin.data.Account;
import kz.kcell.vaadin.ui.Presenter;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 30 09 2014
 */
@SpringPresenter
public abstract class AbstractPresenter<VIEW extends Component> implements Presenter {

    // todo refact - extract to session manager
    protected Account getAccount() {
        Account account = getSession().getAttribute(Account.class);
        if(account == null) {
            Account newAccount = new Account();
            newAccount.setAuthorized(false);
            account = newAccount;
            getSession().setAttribute(Account.class, account);
        }
        return account;
    }

    private VaadinSession getSession() {
        return VaadinSession.getCurrent();
//        return getView().getUI().getSession();
    }

    protected abstract VIEW getView();

}
