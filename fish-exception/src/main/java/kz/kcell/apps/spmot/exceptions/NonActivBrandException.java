package kz.kcell.apps.spmot.exceptions;

import kz.kcell.apps.common.exceptions.BusinessException;

import java.io.Serializable;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 13 11 2014
 */
public class NonActivBrandException extends BusinessException implements Serializable {
    private SPMOT_BusinessExceptionCodeEnum code = SPMOT_BusinessExceptionCodeEnum.NON_ACTIV_BRAND;

    @Override
    public SPMOT_BusinessExceptionCodeEnum getCode() {
        return code;
    }

}
