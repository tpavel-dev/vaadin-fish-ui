package kz.kcell.apps.fish.mobile.vaadin.ui.view;

import com.vaadin.ui.Button;
import kz.kcell.vaadin.ui.View;
import kz.kcell.vaadin.ui.behaviors.SubmitBehavior;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 23 10 2014
 */
public interface TransferBonusesView extends View<TransferBonusesView.Listener> {

    public String getMsisdn();
    public Integer getSum();
    public void setMsisdn(String string);
    public void setSum(String sum);


    void showConfirmWindow();

    public interface Listener extends SubmitBehavior {

        void onConfirmSubmit(Button.ClickEvent e);

        void requestBonus();

        void setView(TransferBonusesView view);
    }
}
