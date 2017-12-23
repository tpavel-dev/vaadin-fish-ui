package kz.kcell.apps.common.msisdn;

import lombok.Getter;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 10 2014
 */
public class IllegalFormatMsisdnException extends IllegalArgumentException {

    @Getter
    private String value;

    public IllegalFormatMsisdnException() {
        super();
    }

    public IllegalFormatMsisdnException(String s) {
        super(s);
    }

    public IllegalFormatMsisdnException(String value, String s) {
        super(String.format("Invalid value '%s': %s ",value,s));
        this.value = value;
    }

    public IllegalFormatMsisdnException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalFormatMsisdnException(Throwable cause) {
        super(cause);
    }
}
