package kz.kcell.apps.bonus_cmdr.model;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 11 2014
 */
public enum SubscribeStatus {
    /**
     * Заявка ждет обработки или в обработке
     */
    PENDING,
    /**
     * Заявка активированно. Услуга навешана, тариф сменен
     */
    ACTIVATED,
    /**
     *  Любая ошибка непредусмотренная логикой
     */
    SUBSCRIBE_FAIL,
    /**
     * Used in web app, for select REJECTED subsribe as prefix. Must match all REJECTED states
     */
    REJECTED,
    /**
     * Томато подключен
     */
    REJECTED_TOMATO,
    /**
     * Услуга уже подключена. Проверяется биллингом
     */
    REJECTED_SAME_SERVICE,
    /**
     * Заявка истекла.
     */
    EXPIRED

}
