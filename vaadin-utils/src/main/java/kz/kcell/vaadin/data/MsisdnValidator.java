package kz.kcell.vaadin.data;


import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;
import kz.kcell.apps.common.msisdn.FastMsisdn;
import kz.kcell.apps.common.msisdn.IllegalFormatMsisdnException;
import kz.kcell.apps.fish.exceptions.InvalidValueException;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 05 11 2014
 */
@Deprecated
public class MsisdnValidator extends AbstractValidator<String> {

    /**
     * Constructs a validator for strings.
     * <p>
     * <p>
     * Null and empty string values are always accepted. To reject empty values,
     * set the field being validated as required.
     * </p>
     *
     * @param errorMessage the message to be included in an {@link com.vaadin.data.Validator.InvalidValueException}
     *                     (with "{0}" replaced by the value that failed validation).
     */
    public MsisdnValidator(String errorMessage) {
        super(errorMessage);
    }

    public MsisdnValidator() {
        super("Неверно ввели номер");
    }



    protected boolean isValidValue(String value) {
        try {
            FastMsisdn msisdn = new FastMsisdn(value);

        } catch (IllegalFormatMsisdnException e) {
            return false;
        }
        return true;
    }

    @Deprecated
    private static boolean isValidValueStat(String value) {
        try {
            FastMsisdn msisdn = new FastMsisdn(value);

        } catch (IllegalFormatMsisdnException e) {
            return false;
        }
        return true;
    }

    public static void validate(String message, String value) throws InvalidValueException {
        if(isValidValueStat(value) == false) {
            throw new InvalidValueException(message);
        }
    }


    @Override
    public ValidationResult apply(String s, ValueContext valueContext) {
        return null;
    }
}
