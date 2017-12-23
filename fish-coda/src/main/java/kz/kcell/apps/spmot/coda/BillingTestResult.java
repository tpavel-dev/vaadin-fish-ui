package kz.kcell.apps.spmot.coda;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 15 04 2016
 */
public enum BillingTestResult {
    /**
     * Тариф уже продан
     *
     * @see BillingServices.changeTariffNowWithBonus
     */
    AlreadySoldException,
    /**
     * Услуга уже подключена
     *
     * @see BillingServices.addBusinessProductWithBonus
     */
    AlreadySubscribedException,
    /**
     * Ошибка в билинге
     */
    BillingServicesException,
    /**
     * Недостаточно средств на счету
     */
    NoMoneyOnBalanceException,
    /**
     * Подключен томато
     */
    TomatoSubscribeException,
    /**
     * Тариф или услуга успешно подключен
     */
    success,
    /**
     * emulate behavery, wait call in sec
     * format: success_wait_sec-XXX
     * success_wait_sec-10 wait 10 sec.
     */
    success_wait_sec,

    AddProductException,

    AddBusinessProductException_Exception,
    BalanceUpdateException_Exception,
    BillingConnectionFailure_Exception,
    BusinessProductConfigException_Exception,
    CDDBInternalFailure_Exception,
    CODAInternalFailure_Exception,
    IllegalChannelException_Exception,
    InvalidInputDataException_Exception,
    ProductAlreadySoldException_Exception,
    ProductNotFoundException_Exception,
    TariffChangeException_Exception
}
