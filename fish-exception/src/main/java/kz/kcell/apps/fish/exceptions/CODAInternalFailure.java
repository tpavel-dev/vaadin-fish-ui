package kz.kcell.apps.fish.exceptions;

import kz.kcell.apps.common.exceptions.BusinessException;

/**
 * Created by x on 4/24/17.
 */
public class CODAInternalFailure extends BusinessException {
    private SPMOT_BusinessExceptionCodeEnum code = SPMOT_BusinessExceptionCodeEnum.MSISDN_NOT_FOUND;

    public CODAInternalFailure(String message) {
        super(message);
    }

    public CODAInternalFailure(String message, Exception e) {
        super(message, e);
    }

    public CODAInternalFailure(Exception e) {
        super(e);
    }

    @Override
    public SPMOT_BusinessExceptionCodeEnum getCode() {
        return code;
    }
}
