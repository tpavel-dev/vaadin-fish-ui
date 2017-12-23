package kz.kcell.apps.fish.exceptions;

import kz.kcell.apps.common.exceptions.BusinessException;
import kz.kcell.apps.common.exceptions.code.ExceptionCodeEnum;
import kz.kcell.apps.common.msisdn.Msisdn;

import static java.lang.String.format;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 14 11 2014
 */
public class ProductNotAllowedException extends BusinessException {
//    private SPMOT_BusinessExceptionCodeEnum code;

//    @Override
//    public SPMOT_BusinessExceptionCodeEnum getCode() {
//        return code;
//    }

    public ProductNotAllowedException() {
        super();
    }

    public ProductNotAllowedException(String message) {
        super(message);
    }

    public ProductNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotAllowedException(Throwable cause) {
        super(cause);
    }

    public ProductNotAllowedException(ExceptionCodeEnum code) {
        super(code);
//        this.code = code;
    }

    public ProductNotAllowedException(ExceptionCodeEnum code, String message) {
        super(code, message);
    }

    public ProductNotAllowedException(ExceptionCodeEnum code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public ProductNotAllowedException(ExceptionCodeEnum code, Throwable cause) {
        super(code, cause);
    }

    public static ProductNotAllowedException notAllowedForMsisdn(
            Msisdn msisdn,
            Long productId,
            String productName,
            String dealerAccessGroup,
            String productAccessGroup
    ) {
        return new ProductNotAllowedException(
                format("Product not allowed for %s. %s:%s. DealerAccessGroup %s; ProductAccessGroup %s  "
                        ,  msisdn, productId, productName, dealerAccessGroup, productAccessGroup));
    }
}
