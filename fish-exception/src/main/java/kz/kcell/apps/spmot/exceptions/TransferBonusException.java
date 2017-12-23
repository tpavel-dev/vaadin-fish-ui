package kz.kcell.apps.spmot.exceptions;

import kz.kcell.apps.common.exceptions.BusinessException;
import kz.kcell.apps.common.exceptions.code.ExceptionCodeEnum;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 14 11 2014
 */
public class TransferBonusException extends BusinessException {
//    private SPMOT_BusinessExceptionCodeEnum code;

//    @Override
//    public SPMOT_BusinessExceptionCodeEnum getCode() {
//        return code;
//    }

    public TransferBonusException() {
        super();
    }

    public TransferBonusException(String message) {
        super(message);
    }

    public TransferBonusException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransferBonusException(Throwable cause) {
        super(cause);
    }

    public TransferBonusException(ExceptionCodeEnum code) {
        super(code);
//        this.code = code;
    }

    public TransferBonusException(ExceptionCodeEnum code, String message) {
        super(code, message);
    }

    public TransferBonusException(ExceptionCodeEnum code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public TransferBonusException(ExceptionCodeEnum code, Throwable cause) {
        super(code, cause);
    }
}
