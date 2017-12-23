package kz.kcell.apps.spmot.audit;

import kz.kcell.apps.common.msisdn.Msisdn;
import kz.kcell.apps.spmot.mobile.vaadin.SpmotWebConfig;
import kz.kcell.vaadin.ui.EventType;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class EventCache {

    @Autowired
    private SpmotWebConfig config;

    private Map<Msisdn, UserState> userStateMapMap = new HashMap<>();

    public UserState userState(Msisdn msisdn) {
        UserState state = userStateMapMap.get(msisdn);
        if (state == null) {
            state = new UserState(config.getAudit().loginEventsCapacity, config.getAudit().ivrEventsCapacity);
            userStateMapMap.put(msisdn, state);
        }
        return state;
    }

    public static class Event {
        public LocalDateTime time;
        public EventType eventType;
//        public Object eventObject;

        private Event(){}

        public static Event newEvent(EventType eventType) {
            Event event = new Event();
            event.eventType = eventType;
//            event.eventObject = eventObject;
            event.time =  LocalDateTime.now();
            return event;
        }
    }

    public class UserState {
        public CircularFifoQueue<Event> loginEvents;
        public CircularFifoQueue<Event> ivrEvents;
        public AtomicInteger countAuthAttempts = new AtomicInteger(0);
        public AtomicBoolean isBlockedCaptcha = new AtomicBoolean(false);

        public UserState(int loginEventsCapacity, int ivrEventsCapacity) {
            loginEvents = new CircularFifoQueue(loginEventsCapacity);
            ivrEvents = new CircularFifoQueue(ivrEventsCapacity);
        }
    }
}
