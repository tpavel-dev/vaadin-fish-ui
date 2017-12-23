package kz.kcell.apps.spmot.coda;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 14 04 2016
 */
public class AddProductException extends Exception {
    public AddProductException() {
    }

    public AddProductException(String message) {
        super(message);
    }

    public AddProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddProductException(Throwable cause) {
        super(cause);
    }

    public AddProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
