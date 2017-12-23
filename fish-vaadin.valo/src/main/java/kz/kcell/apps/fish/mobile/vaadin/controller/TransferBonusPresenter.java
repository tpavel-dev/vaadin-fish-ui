package kz.kcell.apps.fish.mobile.vaadin.controller;


import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import kz.kcell.apps.fish.exceptions.InvalidValueException;
import kz.kcell.apps.fish.mobile.vaadin.annotation.SpringPresenter;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.TransferBonusesView;
import kz.kcell.vaadin.data.EmptyStringValidator;
import kz.kcell.vaadin.data.MsisdnValidator;
import kz.kcell.vaadin.ui.EventBus;
import kz.kcell.vaadin.ui.EventType;
import kz.kcell.vaadin.ui.Presenter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.EventObject;

import static kz.kcell.apps.fish.domain.spmot.entity.SpmotResourceBundle.*;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 23 10 2014
 */
@Slf4j
@SpringPresenter
public class TransferBonusPresenter extends AbstractPresenter<TransferBonusesView> implements Presenter, TransferBonusesView.Listener {

    @Setter
    private TransferBonusesView view;

    @Autowired
    private EventBus eventBus;

    @Override
    protected TransferBonusesView getView() {
        return null;
    }

    @Override
    public void onSubmit(EventObject source) throws InvalidValueException {
        EmptyStringValidator.validate($(transfer_validate_msg_enter_msisdn), view.getMsisdn());
        MsisdnValidator.validate($(transfer_validate_msg_unvalid_msisdn), view.getMsisdn());
        if(view.getSum() <= 0) {
            throw new InvalidValueException($(transfer_validate_msg_check_sum));
        }

        view.showConfirmWindow();
    }

    @Override
    public void display(UI ui) {

//        ui.setContent(view);
    }

    @Override
    public void onConfirmSubmit(Button.ClickEvent e) {

            try {
                log.info("transfer bonus {} to {}", view.getSum(), view.getMsisdn());
            }catch (Exception nothing) {}


            view.showNotification($(transfer_notify_msg_transfer_successfull), Notification.Type.ASSISTIVE_NOTIFICATION);
            view.setMsisdn("");
            view.setSum("");
            eventBus.post(EventType.TRANSFER_BONUS_SUCCESS);
    }

    @Override
    public void requestBonus() {
        eventBus.post(EventType.UPDATE_BALANCE);
    }

    @Override
    public void translate() {
        view.translate();
    }
}
