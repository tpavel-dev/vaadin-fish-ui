package kz.kcell.apps.spmot.coda;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 14 04 2016
 */
public class NoMoneyOnBalanceException extends Exception {
    public NoMoneyOnBalanceException() {
    }

    public NoMoneyOnBalanceException(String message) {
        super(message);
    }

    public NoMoneyOnBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMoneyOnBalanceException(Throwable cause) {
        super(cause);
    }

    public NoMoneyOnBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
