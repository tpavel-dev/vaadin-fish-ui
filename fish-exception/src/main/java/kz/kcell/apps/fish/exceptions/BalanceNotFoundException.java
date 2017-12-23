package kz.kcell.apps.fish.exceptions;

import kz.kcell.apps.common.exceptions.BusinessException;
import kz.kcell.apps.common.exceptions.code.ExceptionCodeEnum;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 14 11 2014
 */
public class BalanceNotFoundException extends BusinessException {
    private SPMOT_BusinessExceptionCodeEnum code = SPMOT_BusinessExceptionCodeEnum.BALANCE_NOT_FOUND;

    @Override
    public SPMOT_BusinessExceptionCodeEnum getCode() {
        return code;
    }
//    private SPMOT_BusinessExceptionCodeEnum code;

//    @Override
//    public SPMOT_BusinessExceptionCodeEnum getCode() {
//        return code;
//    }

    public BalanceNotFoundException() {
        super();
    }

    public BalanceNotFoundException(String message) {
        super(message);
    }

    public BalanceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BalanceNotFoundException(Throwable cause) {
        super(cause);
    }

    public BalanceNotFoundException(ExceptionCodeEnum code) {
        super(code);
//        this.code = code;
    }

    public BalanceNotFoundException(ExceptionCodeEnum code, String message) {
        super(code, message);
    }

    public BalanceNotFoundException(ExceptionCodeEnum code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BalanceNotFoundException(ExceptionCodeEnum code, Throwable cause) {
        super(code, cause);
    }
}
