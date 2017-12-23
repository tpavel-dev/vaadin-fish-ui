package kz.kcell.apps.common.exceptions;

import java.io.Serializable;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 30 10 2014
 */
public class SampleBusinessCodeException extends BusinessException implements Serializable {

    private SampleBusinessExceptionCodes code;

    public SampleBusinessCodeException(SampleBusinessExceptionCodes code, String message) {
        super(message);
        this.code = code;

    }

    @Override
    public SampleBusinessExceptionCodes getCode() {
        return code;
    }
}
