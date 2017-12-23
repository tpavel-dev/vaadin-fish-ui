package kz.kcell.vaadin.ui;

import com.vaadin.ui.Label;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 11 11 2014
 */
public class ClearFix extends Label {

    private void ClearFix() {
    }

    public static ClearFix v() {
        ClearFix c = new ClearFix();
        c.setWidth(1, Unit.EM);
        return c;
    }

    public static ClearFix v(float width) {
        ClearFix c = new ClearFix();
        c.setWidth(width, Unit.EM);
        return c;
    }

    public static ClearFix h() {
        ClearFix c = new ClearFix();
        c.setHeight(2, Unit.EM);
        return c;
    }

    public static ClearFix h(float width) {
        ClearFix c = new ClearFix();
        c.setHeight(width, Unit.EM);
        return c;
    }
}
