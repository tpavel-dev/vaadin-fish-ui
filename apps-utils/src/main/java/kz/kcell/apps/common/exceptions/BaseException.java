package kz.kcell.apps.common.exceptions;

import kz.kcell.apps.common.exceptions.code.ExceptionCodeEnum;

import java.io.IOException;
import java.io.Serializable;

/**
 * Marked interface
 *
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 30 10 2014
 */
public class BaseException extends IOException implements CodeException<ExceptionCodeEnum>, Serializable {

    protected ExceptionCodeEnum code = CommonSystemExceptionCode.Ox0000;
    private String remoteStackTrace;
    private String remoteMessage;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

/*
    public Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
*/

    public BaseException(ExceptionCodeEnum code) {
        this.code = code;
    }

    public BaseException(ExceptionCodeEnum code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(ExceptionCodeEnum code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BaseException(ExceptionCodeEnum code, Throwable cause) {
        super(cause);
        this.code = code;
    }

/*
    public Exception(ExceptionCodeEnum code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
*/

    public String getMessageText() {
        return super.getMessage();
    }

    @Override
    public String getMessage() {
        String message = super.getMessage() == null
                ? ":"
                : ": "+super.getMessage();
        ExceptionCodeEnum code = getCode() == null? CommonSystemExceptionCode.Ox0000:getCode();
        if(remoteMessage != null) message += "\n\t Remote message: "+remoteMessage;
//        if(remoteStackTrace != null) message += "\n\t Remote stack trace: "+ remoteStackTrace;
        return code.name()+ message;
    }

    @Override
    public ExceptionCodeEnum getCode() {
        return code;
    }

    public void  setCode(ExceptionCodeEnum code) {
        this.code = code;
    }

    public void setRemoteMessage(String message) {
        this.remoteMessage = remoteMessage;
    }

    public void setRemoteStackTrace(String remoteStackTrace) {
        this.remoteStackTrace = remoteStackTrace;
    }

    public String getRemoteStackTrace() {
        return remoteStackTrace;
    }
}
