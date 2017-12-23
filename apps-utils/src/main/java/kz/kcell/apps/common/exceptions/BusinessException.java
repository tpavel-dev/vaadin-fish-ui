package kz.kcell.apps.common.exceptions;


import kz.kcell.apps.common.exceptions.code.ExceptionCodeEnum;

/**
 * Marked interface
 *
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 30 10 2014
 */
public class BusinessException extends BaseException {

    public BusinessException() {
    }

    public
    BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(ExceptionCodeEnum code) {
        super(code);
    }

    public BusinessException(ExceptionCodeEnum code, String message) {
        super(code, message);
    }

    public BusinessException(ExceptionCodeEnum code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BusinessException(ExceptionCodeEnum code, Throwable cause) {
        super(code, cause);
    }

}
