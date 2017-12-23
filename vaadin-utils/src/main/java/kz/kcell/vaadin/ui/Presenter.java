package kz.kcell.vaadin.ui;

import com.vaadin.ui.UI;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 18 09 2014
 */
public interface Presenter extends Controller {
//    EventBus getEventBus();
//    void setEventBus(EventBus eventBus);
    public void translate();

    default void display(UI ui) {};
}
