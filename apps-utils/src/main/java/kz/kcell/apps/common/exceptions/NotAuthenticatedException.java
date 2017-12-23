package kz.kcell.apps.common.exceptions;

import java.io.Serializable;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 30 10 2014
 */
public class NotAuthenticatedException extends BusinessException implements Serializable {

    private CommonSystemExceptionCode code = CommonSystemExceptionCode.AUTHENTICATED_FAILED;

    @Override
    public CommonSystemExceptionCode getCode() {
        return code;
    }
}
