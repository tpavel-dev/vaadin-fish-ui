package kz.kcell.apps.spmot.exceptions;

import kz.kcell.apps.common.exceptions.code.BusinessExceptionCodeEnum;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 30 10 2014
 */
public enum SPMOT_BusinessExceptionCodeEnum implements BusinessExceptionCodeEnum {
    DILLER_SELF_CANNOT_SUBSCRIBE,
    NON_ACTIV_BRAND,
    ILLEGAL_VALUE,
    ILLEGAL_AMOUNT_VALUE,
    INVALID_VALUE_DATA,
    BALANCE_NOT_FOUND,
    SIMILAR_SERVICE_ALREADY_SUBSCRIBED,
    TP_AK_PAST_12_HOURS_ALREADY_HAVE,
    CUSTOMER_MSISDN_NOT_FOUND,
    CODA_INTERNAL_FAILED,
    INVALID_INPUT_DATA,
    MSISDN_NOT_FOUND,
    TRANSFER_BONUS_NOT_ALLOWED,
    SHAREBONUS_FACILITY_DENIED,
    SHAREBONUS_EXCEEDS_PRODUCT_BONUS,
    INVALID_SHAREBONUS
}
