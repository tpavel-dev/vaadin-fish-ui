package kz.kcell.vaadin.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 17 09 2014
 */
public interface View<LISTENER> extends Component {
//    void setListener(LISTENER listener);
    LISTENER  getListener();

    void showNotification(String msg, Notification.Type typeMsg);
    void clearNotification();
    void translate();
}
/*
public interface View<VIEW> extends Component {

    VIEW build();
}
*/
