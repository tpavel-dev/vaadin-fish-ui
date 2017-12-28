package kz.kcell.apps.fish.mobile.vaadin.ui.view.component;

import com.vaadin.ui.HorizontalLayout;
import kz.kcell.app.bonus_cmdr.ws.stub.BonusParams;

public abstract class AbstractInfo<T> extends HorizontalLayout {

    void init() {
        buildLayout();
        setFieldsValue();
    }

    abstract void buildLayout();

    abstract void setBonus(BonusParams bonus);

    abstract void setFieldsValue();

    abstract T getBean();
}
