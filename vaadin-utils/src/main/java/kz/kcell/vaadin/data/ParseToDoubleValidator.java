package kz.kcell.vaadin.data;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;

public class ParseToDoubleValidator implements Validator<String> {

    private String errorMessage;

    public ParseToDoubleValidator(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public ValidationResult apply(String value, ValueContext context) {
        try {
            Double.parseDouble(value);
            return ValidationResult.ok();
        } catch (Exception e) {
            return ValidationResult.error(errorMessage);
        }
    }

}
