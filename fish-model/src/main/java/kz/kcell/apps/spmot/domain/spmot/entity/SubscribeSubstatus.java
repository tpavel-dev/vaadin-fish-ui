package kz.kcell.apps.spmot.domain.spmot.entity;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 11 2014
 */
public enum SubscribeSubstatus {
    /**
     * Заявка в обработке
     */
    INPROCESS,
    /**
     * Заявка обработана
     */
    PROCESSED,
    /**
     * Вовремя обработки произошла ошибка в билинге
     */
    BILLING_SERVICE_ERROR,
    /**
     * Недостаточно денег на счете
     */
    NOT_ENOUGH_MONEY_IN_THE_ACCOUNT,
    /**
     * Ошибка во время добавление тарифа
     */
    SUBSCRIBE_FAIL
}
