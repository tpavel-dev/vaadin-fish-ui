package kz.kcell.apps.fish.exceptions;

import kz.kcell.apps.common.exceptions.BusinessException;

/**
 * Created by x on 4/24/17.
 */
public class MsisdnNotFoundException extends BusinessException {
    private SPMOT_BusinessExceptionCodeEnum code = SPMOT_BusinessExceptionCodeEnum.MSISDN_NOT_FOUND;

    public MsisdnNotFoundException(String message) {
        super(message);
    }

    public MsisdnNotFoundException(String message, Exception e) {
        super(message, e);
    }

    public MsisdnNotFoundException(Exception e) {
        super(e);
    }

    @Override
    public SPMOT_BusinessExceptionCodeEnum getCode() {
        return code;
    }
}
