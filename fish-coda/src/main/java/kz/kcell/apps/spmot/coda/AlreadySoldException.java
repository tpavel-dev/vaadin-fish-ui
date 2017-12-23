package kz.kcell.apps.spmot.coda;

/**
 * Выбрасывается методом billingServicesWS.changeTariffNowWithBonus
 *
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 14 04 2016
 */
public class AlreadySoldException extends Exception {

    public AlreadySoldException() {
    }

    public AlreadySoldException(String message) {
        super(message);
    }

    public AlreadySoldException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadySoldException(Throwable cause) {
        super(cause);
    }

    public AlreadySoldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
