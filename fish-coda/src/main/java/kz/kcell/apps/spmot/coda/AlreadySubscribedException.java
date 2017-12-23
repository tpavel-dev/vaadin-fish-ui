package kz.kcell.apps.spmot.coda;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 14 04 2016
 */
public class AlreadySubscribedException extends Exception {
    public AlreadySubscribedException() {
    }

    public AlreadySubscribedException(String message) {
        super(message);
    }

    public AlreadySubscribedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadySubscribedException(Throwable cause) {
        super(cause);
    }

    public AlreadySubscribedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
