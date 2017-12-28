package kz.kcell.apps.fish.mobile.vaadin.ui.view.component;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.HorizontalLayout;
import kz.kcell.app.bonus_cmdr.ws.stub.BonusParams;

public abstract class AbstractInfo<T> extends HorizontalLayout {

    void init() {
        setWidth(100, Unit.PERCENTAGE);
        setSpacing(false);
        setMargin(new MarginInfo(false, true, false, false));

        buildLayout();
        setFieldsValue();
    }

    abstract void buildLayout();

    abstract void setBonus(BonusParams bonus);

    abstract void setFieldsValue();

    abstract T getBean();
}
