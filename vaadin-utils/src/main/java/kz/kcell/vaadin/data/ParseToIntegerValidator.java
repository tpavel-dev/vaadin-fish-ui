package kz.kcell.vaadin.data;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;

public class ParseToIntegerValidator implements Validator<String> {

    private String errorMessage;

    public ParseToIntegerValidator(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public ValidationResult apply(String value, ValueContext context) {
        try {
            Integer.parseInt(value);
            return ValidationResult.ok();
        } catch (Exception e) {
            return ValidationResult.error(errorMessage);
        }
    }

}
