package kz.kcell.apps.bonus_cmdr.model;

import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class NotificationUtils {

    public static void show(String msg, Notification.Type typeMsg) {
        Notification notification = new Notification(msg, typeMsg);
        notification.setDelayMsec(300);
        notification.setPosition(Position.TOP_RIGHT);
        UI.getCurrent().showNotification(notification);
    }

    public static void showWithDescription(String msg, String description, Notification.Type typeMsg) {
        Notification notification = new Notification(msg, typeMsg);
        notification.setDelayMsec(300);
        notification.setPosition(Position.TOP_RIGHT);
        UI.getCurrent().showNotification(notification);
    }

}
