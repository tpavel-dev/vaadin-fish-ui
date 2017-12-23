package kz.kcell.apps.common.exceptions;

import java.io.Serializable;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 30 10 2014
 */
public class NotAuthException extends BusinessException implements  Serializable {

    private CommonSystemExceptionCode code = CommonSystemExceptionCode.AUTH_FAILED;

    @Override
    public CommonSystemExceptionCode getCode() {
        return code;
    }
}
