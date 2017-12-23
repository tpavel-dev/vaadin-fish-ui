package kz.kcell.apps.spmot.exceptions;

import kz.kcell.apps.common.exceptions.BusinessException;
import kz.kcell.apps.common.exceptions.code.ExceptionCodeEnum;

import static java.lang.String.format;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 14 11 2014
 */
public class ProductNotExistException extends BusinessException {
//    private SPMOT_BusinessExceptionCodeEnum code;

//    @Override
//    public SPMOT_BusinessExceptionCodeEnum getCode() {
//        return code;
//    }

    public ProductNotExistException() {
        super();
    }

    public ProductNotExistException(String message) {
        super(message);
    }

    public ProductNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotExistException(Throwable cause) {
        super(cause);
    }

    public ProductNotExistException(ExceptionCodeEnum code) {
        super(code);
//        this.code = code;
    }

    public ProductNotExistException(ExceptionCodeEnum code, String message) {
        super(code, message);
    }

    public ProductNotExistException(ExceptionCodeEnum code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public ProductNotExistException(ExceptionCodeEnum code, Throwable cause) {
        super(code, cause);
    }

    public static ProductNotExistException notExist(Long productId ) {
        return notExist(productId.toString());
    }

    public static ProductNotExistException notExist(String productId ) {
        return new ProductNotExistException(format("Product with id %s not exist ", productId));
    }
}
