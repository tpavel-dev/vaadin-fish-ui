package kz.kcell.vaadin.ui.behaviors;


import kz.kcell.apps.common.exceptions.BaseException;
import kz.kcell.apps.fish.exceptions.InvalidValueException;

import java.util.EventObject;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 17 09 2014
 */
public interface SubmitBehavior {
    void onSubmit(EventObject source) throws BaseException, InvalidValueException;
}
