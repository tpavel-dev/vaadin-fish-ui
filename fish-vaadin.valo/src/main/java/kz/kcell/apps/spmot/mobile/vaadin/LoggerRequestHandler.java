package kz.kcell.apps.spmot.mobile.vaadin;

import com.vaadin.server.SynchronizedRequestHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import com.vaadin.server.VaadinSession;
import kz.kcell.apps.spmot.logging.MDCKeys;
import kz.kcell.apps.spmot.mobile.vaadin.data.Account;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.IOException;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 24 12 2014
 */
@Slf4j
public class LoggerRequestHandler extends SynchronizedRequestHandler {
    @Override
    public boolean synchronizedHandleRequest(VaadinSession vaadinSession, VaadinRequest vaadinRequest, VaadinResponse vaadinResponse) throws IOException {
//        try {
//            MDC.put(MDCKeys.ip.name(), vaadinRequest.getRemoteHost());
//        } catch (Exception noting) {
//        }
        try {
            String msisdn = vaadinSession.getAttribute(Account.class).getMsisdn().get();
            MDC.put(MDCKeys.msisdn.name(), msisdn);
        } catch (Exception noting) {
        }

        try {
            String lang = vaadinSession.getAttribute(Account.class).getLang().name();
            MDC.put(MDCKeys.lang.name(), lang);
        } catch (Exception noting) {
        }

        return false;
    }

}
