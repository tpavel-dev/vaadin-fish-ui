package kz.kcell.apps.fish.exceptions;

import kz.kcell.apps.common.exceptions.BusinessException;

/**
 * Created by x on 4/24/17.
 */
public class CustomerMsisdnNotFoundException extends BusinessException {
    private SPMOT_BusinessExceptionCodeEnum code = SPMOT_BusinessExceptionCodeEnum.CUSTOMER_MSISDN_NOT_FOUND;

    public CustomerMsisdnNotFoundException(Exception e) {
        super(e);

    }
}
