package kz.kcell.apps.spmot.mobile.vaadin.ui;


import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import kz.kcell.apps.common.exceptions.*;
import kz.kcell.apps.spmot.exceptions.*;
import kz.kcell.vaadin.ui.Style;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.NotFoundException;

import java.security.AccessControlException;

import static kz.kcell.apps.spmot.domain.spmot.entity.SpmotResourceBundle.*;
import static kz.kcell.apps.spmot.mobile.vaadin.SpmotMobileResourceManager.$;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 02 11 2014
 */
@SpringComponent
@Slf4j
public class ErrorHandler extends DefaultErrorHandler {
    private enum SHOW_LEVEL_ERROR {
         AS_ERROR
        ,AS_INFO
        ,AS_WARNING
    }

    public static void handle(Throwable e) {
//        boolean errorHandled = false;
        boolean logFullThread = false;

        SHOW_LEVEL_ERROR errorShowAs = SHOW_LEVEL_ERROR.AS_ERROR;

/*
        NotFoundException nf = (NotFoundException) e;
        nf.getResponse().get
*/
        if (e instanceof InvalidValueException) {
//        String msg = e.getHtmlMessage();
            String msg = ((InvalidValueException)e).getMessageText();
//        Notification.show(msg, Notification.Type.HUMANIZED_MESSAGE);
            ((MainUI) UI.getCurrent()).showNotification(msg, Notification.Type.HUMANIZED_MESSAGE);
            errorShowAs = SHOW_LEVEL_ERROR.AS_INFO;

        } else if (e instanceof IvrPollEmptyException) {
            ((MainUI) UI.getCurrent()).showNotification($(ivr_poll_is_empty), Notification.Type.HUMANIZED_MESSAGE);

        } else if (e instanceof NotAuthenticatedException) {
            ((MainUI) UI.getCurrent()).showNotification($(error_not_authenticated), Notification.Type.HUMANIZED_MESSAGE);

        } else if (e instanceof NotAuthorizedException) {
            ((MainUI) UI.getCurrent()).showNotification($(error_not_authorized), Notification.Type.HUMANIZED_MESSAGE);

        } else if (e instanceof NotAuthException) {
            ((MainUI) UI.getCurrent()).showNotification($(error_not_authenticated), Notification.Type.HUMANIZED_MESSAGE);

        } else if (e instanceof NonActivBrandException) {
            ((MainUI) UI.getCurrent()).showNotification($(error_invalid_brend), Notification.Type.HUMANIZED_MESSAGE);

        } else if (e instanceof TransferBonusException) {

            if (((TransferBonusException) e).getCode() == SPMOT_BusinessExceptionCodeEnum.ILLEGAL_AMOUNT_VALUE) {
                ((MainUI) UI.getCurrent()).showNotification($(error_invalid_sum), Notification.Type.ASSISTIVE_NOTIFICATION);

            } else if (((TransferBonusException) e).getCode() == SPMOT_BusinessExceptionCodeEnum.INVALID_VALUE_DATA) {
                    ((MainUI) UI.getCurrent()).showNotification($(error_invalid_data), Notification.Type.ASSISTIVE_NOTIFICATION);

            } else if (((TransferBonusException) e).getCode() == SPMOT_BusinessExceptionCodeEnum.TRANSFER_BONUS_NOT_ALLOWED) {
                ((MainUI) UI.getCurrent()).showNotification($(transfer_bonus_not_allowed), Notification.Type.ASSISTIVE_NOTIFICATION);

            } else {
                ((MainUI) UI.getCurrent()).showNotification($(error_internal), Notification.Type.ERROR_MESSAGE);
                log.error("Unknow code TransferBonus exception.", e);
            }

        } else if (e instanceof BalanceNotFoundException) {
            ((MainUI) UI.getCurrent()).showNotification($(error_balance_not_found), Notification.Type.ERROR_MESSAGE);

        } else if (e instanceof BusinessException) {
            BusinessException be = (BusinessException) e;

            if (be.getCode().equals(SPMOT_BusinessExceptionCodeEnum.DILLER_SELF_CANNOT_SUBSCRIBE)) {
                ((MainUI) UI.getCurrent()).showNotification($(error_try_subscribe_self), Notification.Type.ASSISTIVE_NOTIFICATION);

            } else if (be.getCode().equals(SPMOT_BusinessExceptionCodeEnum.SIMILAR_SERVICE_ALREADY_SUBSCRIBED)) {
                ((MainUI) UI.getCurrent()).showNotification($(similar_service_already_subscribed), Notification.Type.ASSISTIVE_NOTIFICATION);

            } else if (be.getCode().equals(SPMOT_BusinessExceptionCodeEnum.TP_AK_PAST_12_HOURS_ALREADY_HAVE)) {
                ((MainUI) UI.getCurrent()).showNotification($(get_bonus_not_available), Notification.Type.ASSISTIVE_NOTIFICATION);

            } else if (be.getCode().equals(SPMOT_BusinessExceptionCodeEnum.INVALID_SHAREBONUS)) {
                ((MainUI) UI.getCurrent()).showNotification($(sharebonus_invalid), Notification.Type.ASSISTIVE_NOTIFICATION);

            } else if (be.getCode().equals(SPMOT_BusinessExceptionCodeEnum.SHAREBONUS_EXCEEDS_PRODUCT_BONUS)) {
                ((MainUI) UI.getCurrent()).showNotification($(sharebonus_exceeds_product_bonus), Notification.Type.ASSISTIVE_NOTIFICATION);

            } else if (be.getCode().equals(SPMOT_BusinessExceptionCodeEnum.SHAREBONUS_FACILITY_DENIED)) {
                ((MainUI) UI.getCurrent()).showNotification($(sharebonus_facility_denied), Notification.Type.ASSISTIVE_NOTIFICATION);

            } else {
                ((MainUI) UI.getCurrent()).showNotification(be.getCode().name(), Notification.Type.ASSISTIVE_NOTIFICATION);
            }

//        } else if (e instanceof AccessControlException) {
//            ((MainUI) UI.getCurrent()).showNotification(e.getMessage(), Notification.Type.ERROR_MESSAGE);

        } else if (e instanceof NotFoundException) {
            ((MainUI) UI.getCurrent()).showNotification($(error_internal_404), Notification.Type.ERROR_MESSAGE);

        } else {
            logFullThread = true;
            ((MainUI) UI.getCurrent()).showNotification($(error_internal), Notification.Type.ERROR_MESSAGE);
        }

        if(e instanceof BusinessException) {
            errorShowAs = SHOW_LEVEL_ERROR.AS_INFO;
        }

        if(logFullThread) {
            log.error(e.getLocalizedMessage(), e);
        } else {
            switch (errorShowAs) {
                case AS_ERROR   : log.error(e.getLocalizedMessage());break;
                case AS_INFO    : log.info(e.getLocalizedMessage()); break;
                case AS_WARNING : log.warn(e.getLocalizedMessage()); break;
                default         : log.error(e.getLocalizedMessage());
            }
        }
//        return errorHandled;
    }

    @Override
    public void error(com.vaadin.server.ErrorEvent event) {

        try {
            Throwable error = event.getThrowable();
            handle(error);

               // Find the final cause
/*
               String cause = "<b>The click failed because:</b><br/>";
               for (Throwable t = event.getThrowable(); t != null;
                    t = t.getCause())
                   if (t.getCause() == null) // We're at final cause
                       cause += t.getClass().getName() + "<br/>";
*/

               // Display the error message in a custom fashion
//                addComponent(new Label(cause, ContentMode.HTML));
//        Notification.show(event.getThrowable().toString(), Notification.Type.ERROR_MESSAGE);
//            Notification.show(ERROR_MESSAGE, Notification.Type.ERROR_MESSAGE);


               // Do the default error handling (optional)
//                doDefault(event);
//           }
        } catch (Exception exc) {
            log.error(exc.getLocalizedMessage(), exc);
        }

    }


    public static void showNotification(Label notificationLabel, String msg, Notification.Type typeMsg) {
        notificationLabel.setValue(msg);
        if(msg == null) {
            notificationLabel.setVisible(false);
            Style.clearNotificationStyle(notificationLabel);
        } else {
            notificationLabel.setVisible(true);
            Style.setNotificationStyle(notificationLabel, typeMsg);
        }
    }


}
