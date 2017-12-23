package kz.kcell.apps.spmot.coda;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 15 04 2016
 */
public class BillingServicesException extends Exception {
    public BillingServicesException() {
    }

    public BillingServicesException(String message) {
        super(message);
    }

    public BillingServicesException(String message, Throwable cause) {
        super(message, cause);
    }

    public BillingServicesException(Throwable cause) {
        super(cause);
    }

    public BillingServicesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
