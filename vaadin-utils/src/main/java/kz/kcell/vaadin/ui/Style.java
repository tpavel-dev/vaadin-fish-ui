package kz.kcell.vaadin.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

import java.util.Arrays;

public final class Style {
    public final static String _100p = "100%";
    public final static String NOTIFICATINO_PREFIX = "notification_";


    public static <T extends Component> T applyStyleFullWithView(T component) {
        component.setWidth(Style._100p);
        return component;
    }

    public static void clearNotificationStyle(Label notificationLabel) {
        Arrays.asList(Notification.Type.values()).forEach(t -> {
                notificationLabel.removeStyleName(NOTIFICATINO_PREFIX+t.getStyle());
        });

    }

    public static void addNotificationStyle(Label notificationLabel, Notification.Type typeMsg) {
        notificationLabel.addStyleName(NOTIFICATINO_PREFIX+typeMsg.getStyle());
    }

    public static void setNotificationStyle(Label notificationLabel, Notification.Type typeMsg) {
        clearNotificationStyle(notificationLabel);
        notificationLabel.addStyleName(NOTIFICATINO_PREFIX+typeMsg.getStyle());
    }
}
