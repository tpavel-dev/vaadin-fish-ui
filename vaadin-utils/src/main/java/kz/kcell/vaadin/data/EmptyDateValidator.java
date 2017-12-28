package kz.kcell.vaadin.data;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;

import java.time.LocalDate;

public class EmptyDateValidator implements Validator<LocalDate> {

    private String errorMessage;

    public EmptyDateValidator(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public ValidationResult apply(LocalDate value, ValueContext context) {
        if (value == null) return ValidationResult.error(errorMessage);
        return ValidationResult.ok();
    }
}
