package kz.kcell.apps.fish.audit;

import kz.kcell.apps.bonus_cmdr.model.User;
import kz.kcell.apps.common.msisdn.Msisdn;
import kz.kcell.apps.bonus_cmdr.model.User;
import kz.kcell.apps.fish.mobile.vaadin.SpmotWebConfig;
import kz.kcell.apps.fish.mobile.vaadin.controller.SessionManager;
import kz.kcell.apps.fish.mobile.vaadin.data.Account;
import kz.kcell.vaadin.ui.EventType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class Audit {

    @Autowired
    private SpmotWebConfig webConfig;

    @Autowired
    private EventCache cache;

    public void registrationEvent(EventType eventType) {
        logEvent(eventType);

        EventCache.UserState userState = cache.userState(getUserId());
        EventCache.Event event = EventCache.Event.newEvent(eventType);



        switch (eventType) {
            case LOGIN_SUCCESFULL:
            case LOGIN_FAILED: {
                userState.loginEvents.add(event);
                break;
            }
            case SUBSCRIBE_SERVICE: {
                userState.ivrEvents.add(event);
            }
        }
    }

    private void logEvent(EventType eventType) {
        log.info("Reg event: " + eventType.name());
        switch (eventType) {
            case LOGIN_SUCCESFULL: {
                User user = SessionManager.getAccount().getUser();
                log.info("Login {} with bdd {} accessGroup {}",
                        user.getMsisdn(),
                        user.getBdd_code(),
                        user.getAccess_group()
                );
                break;
            }
        }
    }

    private Msisdn getUserId() {
        return getAccount().getMsisdn();
    }

    public void clearCountAuthFailed() {
        getCountAuthFailed().set(0);
    }

    public AtomicInteger getCountAuthFailed() {
        Msisdn msisdn = getAccount().getMsisdn();
        return cache.userState(msisdn).countAuthAttempts;
    }

    private Account getAccount() {
        return SessionManager.getAccount();
    }

    public AtomicBoolean isBlockedCaptcha() {
        Msisdn msisdn = getAccount().getMsisdn();
        return cache.userState(msisdn).isBlockedCaptcha;
    }



    public void clearWarrantForBlockWithCaptcha() {
        cache.userState(getAccount().getMsisdn()).ivrEvents.clear();
        cache.userState(getAccount().getMsisdn()).loginEvents.clear();
    }

    public boolean isWarrantForBlockWithCaptcha() {
        return testRPM(cache.userState(getAccount().getMsisdn()).ivrEvents, 3)
                || testRPM(cache.userState(getAccount().getMsisdn()).loginEvents, 5);

    }

    private boolean testRPM(CircularFifoQueue<EventCache.Event> ivrEvents, int boundary) {

        Iterator<EventCache.Event> iterator = ivrEvents.iterator();
        LocalDateTime prevTime = LocalDateTime.now();

        long sumPeriodInSec = 0;

        while (iterator.hasNext() && boundary > 0) {
            boundary--;
            EventCache.Event event = iterator.next();
            sumPeriodInSec += event.time.until(prevTime, ChronoUnit.SECONDS);
            prevTime = event.time;
        }

        log.trace("Boundary: "+boundary+" sumPeriodInSec: "+sumPeriodInSec);
        return boundary <= 0 && sumPeriodInSec <= 60;
    }
}
