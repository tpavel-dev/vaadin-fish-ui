package kz.kcell.apps.common.exceptions;

import java.io.Serializable;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 30 10 2014
 */
public class IvrPollEmptyException extends BusinessException implements  Serializable {

    private CommonSystemExceptionCode code = CommonSystemExceptionCode.IVR_POLL_EMPTY;

    @Override
    public CommonSystemExceptionCode getCode() {
        return code;
    }
}
