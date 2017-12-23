package kz.kcell.apps.spmot.coda;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 14 04 2016
 */
public class TomatoSubscribeException extends Exception {
    public TomatoSubscribeException() {
    }

    public TomatoSubscribeException(String message) {
        super(message);
    }

    public TomatoSubscribeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TomatoSubscribeException(Throwable cause) {
        super(cause);
    }

    public TomatoSubscribeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
