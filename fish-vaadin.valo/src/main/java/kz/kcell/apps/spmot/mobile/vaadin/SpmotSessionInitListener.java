//package kz.kcell.apps.spmot.mobile.vaadin;
//
//import com.vaadin.server.ServiceException;
//import com.vaadin.server.SessionInitEvent;
//import com.vaadin.server.SessionInitListener;
//import kz.kcell.apps.spmot.mobile.vaadin.controller.SessionManager;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class SpmotSessionInitListener implements SessionInitListener {
//
//    @Override
//    public void sessionInit(final SessionInitEvent event) throws ServiceException {
//
////        SpmotUIProvider uiProvider = new SpmotUIProvider();
////        event.getSession().addUIProvider(uiProvider);
//
////        uiProvider.getController().post(EventType.START_SESSION);
//
//        event.getService().setSystemMessagesProvider(new SpmotSystemMessagesProvider());
//        event.getSession().addBootstrapListener(new SpmotBootstrapListener());
//        SessionManager.initRequestHandler(event.getSession());
//    }
//
//}