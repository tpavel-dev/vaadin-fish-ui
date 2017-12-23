package kz.kcell.apps.spmot.coda;

import kz.kcell.apps.common.Language;
import kz.kcell.apps.common.msisdn.Msisdn;
import kz.kcell.apps.common.resource.ResourceBundle;
import kz.kcell.apps.pentagon.coda.ws.client.stub.MessagingWS;
import kz.kcell.apps.spmot.Config;
import kz.kcell.apps.spmot.resources.ResourceManager;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 04 2016
 */
@Component
@Slf4j
public class SmsService {

    @Autowired
    private MessagingWS messagingWS;

    private AtomicLong smsCount = new AtomicLong(0);

    public void send(Language lang, Msisdn msisdn, ResourceBundle message, Object... args) {
        send(msisdn, ResourceManager.$(lang, message, args));
    }

    public void send(Msisdn msisdn, String message) {
        long count = smsCount.addAndGet(1);
        try {
            log.info("{}: Send for msisdn {} sms: {} ", count, msisdn.get(), message);
            messagingWS.sendSms(message, Config.SMS_FROM_FIELD, msisdn.get());
            log.info("{}: Send sms ok", count);
        } catch (Exception e) {
            log.info("{}: Send sms fail: {}", count, e.getLocalizedMessage());
            log.error(e.getLocalizedMessage(), e);
        }
    }
}
