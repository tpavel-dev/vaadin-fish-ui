package kz.kcell.apps.common.exceptions;

import kz.kcell.apps.common.exceptions.code.ExceptionCodeEnum;

/**
 * Marked interface
 *
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 30 10 2014
 */
public class SystemException extends BaseException {

    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(ExceptionCodeEnum code) {
        super(code);
    }

    public SystemException(ExceptionCodeEnum code, String message) {
        super(code, message);
    }

    public SystemException(ExceptionCodeEnum code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public SystemException(ExceptionCodeEnum code, Throwable cause) {
        super(code, cause);
    }
}
