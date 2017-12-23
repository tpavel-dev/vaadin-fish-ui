package kz.kcell.apps.spmot.exceptions;

import kz.kcell.apps.common.exceptions.BusinessException;

/**
 * Created by x on 4/24/17.
 */
public class InvalidInputDataException extends BusinessException {
    private SPMOT_BusinessExceptionCodeEnum code = SPMOT_BusinessExceptionCodeEnum.INVALID_INPUT_DATA;

    public InvalidInputDataException(Exception e) {
        super(e);
    }

    @Override
    public SPMOT_BusinessExceptionCodeEnum getCode() {
        return code;
    }
}
