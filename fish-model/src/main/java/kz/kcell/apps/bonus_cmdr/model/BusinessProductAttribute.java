package kz.kcell.apps.bonus_cmdr.model;

public enum BusinessProductAttribute {
     SPMOT_AVAILABLE
    ,SPMOT_BONUS_AMOUNT
    ,DESCR_URL
    ,SPMOT_DESCR_URL_RU
    ,SPMOT_DESCR_URL_KK
    ,SPMOT_DESCR_URL_EN
    ,SPMOT_ACCESS_GROUP
    ,PRODUCT_GROUP
    ,MB_AMOUNT
    ,PRODUCT_COST
    ,DURATION
    ,MB_PER_MONTH
    ,MB_PLUS_COST
    ;

    public static BusinessProductAttribute value(String name) {
        try {
            return valueOf(name);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
