package kz.kcell.apps.fish.mobile.vaadin.controller;

import com.vaadin.navigator.ViewChangeListener;
import kz.kcell.apps.common.exceptions.BaseException;
import kz.kcell.apps.fish.mobile.vaadin.annotation.SpringPresenter;
import kz.kcell.apps.fish.mobile.vaadin.ui.ErrorHandler;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.BonusesInfoView;
import kz.kcell.vaadin.CookiesUtils;
import kz.kcell.vaadin.ui.Presenter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 23 10 2014
 */
@Slf4j
@SpringPresenter
public class BonusesInfoPresenter extends AbstractPresenter<BonusesInfoView> implements Presenter, BonusesInfoView.Listener {

    private LocalDateTime lastAccessBonus;

    @Setter @Getter
    private BonusesInfoView view;


    @Override
    public void requestBonus() throws BaseException {
        doUpdateBalance();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        doUpdateBalance();
    }

    @Override
    public void translate() {
        view.translate();
    }

    public void doUpdateBalance()  {
        CookiesUtils.dumpListCookies();
        try {
            if(lastAccessBonus == null ||
                (Duration.between(lastAccessBonus, LocalDateTime.now()).getSeconds() > 60)
            ) {
                updateBalance();
            }
        } catch (Exception exc) {
            ErrorHandler.handle(exc);
        }
    }

    public void updateBalance()  {

        getAccount().setBonus(fetchBalance());
        view.setBonus(getAccount().getBonus());
        lastAccessBonus = LocalDateTime.now();
    }

    /**
     * fetch balance from server
     * if throw exception then balance set into null
     */
    public Double fetchBalance()  {
        Double balance = 0.0;
        try {
            balance = null;
            try {
                log.info("returned bonus {}", balance);
            } catch (Exception nothing){}
        } catch (Exception e) {
//            log.error(e.getLocalizedMessage(), e);
//             balance = Double.valueOf(-1);

        }
        return balance;
    }

}
