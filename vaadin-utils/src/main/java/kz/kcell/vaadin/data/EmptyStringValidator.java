package kz.kcell.vaadin.data;

import kz.kcell.apps.spmot.exceptions.InvalidValueException;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 05 11 2014
 */
@Deprecated
public class EmptyStringValidator extends AbstractValidator<String> {


    public EmptyStringValidator(String errorMessage) {
        super(errorMessage);
    }

    public EmptyStringValidator() {
        super("Пустое поле");
    }

    protected boolean isValidValue(String value) {
        if (value != null && value.trim().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Deprecated
    private static boolean isValidValueStat(String value) {
        if (value != null && value.trim().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void validate(String message, String value) throws InvalidValueException {
        if (isValidValueStat(value) == false) {
            throw new InvalidValueException(message);
        }
    }

    @Override
    public ValidationResult apply(String s, ValueContext valueContext) {
        return null;
    }
}
