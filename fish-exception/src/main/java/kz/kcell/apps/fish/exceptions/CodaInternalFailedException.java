package kz.kcell.apps.fish.exceptions;

import kz.kcell.apps.common.exceptions.BusinessException;

/**
 * Created by x on 4/24/17.
 */
public class CodaInternalFailedException extends BusinessException {
    private SPMOT_BusinessExceptionCodeEnum code = SPMOT_BusinessExceptionCodeEnum.CODA_INTERNAL_FAILED;

    public CodaInternalFailedException(Exception e) {
        super(e);
    }

    @Override
    public SPMOT_BusinessExceptionCodeEnum getCode() {
        return code;
    }
}
