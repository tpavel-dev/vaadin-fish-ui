package kz.kcell.apps.fish.mobile.vaadin.ui.view.window;

import com.vaadin.data.Binder;
import com.vaadin.ui.Component;
import com.vaadin.ui.Window;

public abstract class AbstractWindow<T> extends Window {

    protected Binder<T> binder;

    public AbstractWindow() {
        init();
    }

    abstract void init();

    abstract void initBinder();

    abstract Component buildLayout();

    abstract void save();

    abstract boolean validate();
}
