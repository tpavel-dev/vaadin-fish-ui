package kz.kcell.apps.fish.mobile.vaadin.ui.view.window;

import com.vaadin.data.Binder;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

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

    HorizontalLayout getActionButtons() {
        HorizontalLayout actionBtns = new HorizontalLayout();
        actionBtns.setWidth(100, Unit.PERCENTAGE);
        actionBtns.setMargin(new MarginInfo(false, true, false, false));
        Button saveBtn = new Button("Save");
        saveBtn.setIcon(FontAwesome.SAVE);
        saveBtn.addClickListener(event -> {
            save();
        });
        actionBtns.addComponent(saveBtn);

        Button closeBtn = new Button("Close");
        closeBtn.setIcon(FontAwesome.CLOSE);
        closeBtn.addClickListener(event -> {
            close();
        });
        actionBtns.addComponent(closeBtn);
        actionBtns.setComponentAlignment(closeBtn, Alignment.MIDDLE_RIGHT);
        return actionBtns;
    }
}
