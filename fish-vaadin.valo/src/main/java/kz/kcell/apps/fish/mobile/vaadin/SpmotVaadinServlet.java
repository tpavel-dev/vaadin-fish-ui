package kz.kcell.apps.fish.mobile.vaadin;

import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.spring.server.SpringVaadinServlet;
import kz.kcell.apps.fish.mobile.vaadin.controller.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;

import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.*;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;

//@WebServlet(value = "/*", asyncSupported = true)
@Slf4j
@Component("vaadinServlet")
public class SpmotVaadinServlet extends SpringVaadinServlet {

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();

        getService().setSystemMessagesProvider( p -> {
            CustomizedSystemMessages messages = new CustomizedSystemMessages();
//            messages.setCommunicationErrorCaption("Comm Err");
//            messages.setCommunicationErrorMessage("This is bad.");
//            messages.setCommunicationErrorNotificationEnabled(true);
//            messages.setCommunicationErrorURL("http://vaadin.com/");

            messages.setCookiesDisabledMessage($(cookiesDisabledMessage));
            messages.setCookiesDisabledCaption($(cookiesDisabledCaption));

            messages.setAuthenticationErrorCaption($(authenticationErrorCaption));
            messages.setAuthenticationErrorMessage($(authenticationErrorMessage));

            messages.setCommunicationErrorCaption($(communicationErrorCaption));
            messages.setCommunicationErrorMessage($(communicationErrorMessage));

            messages.setInternalErrorCaption($(internalErrorCaption));
            messages.setInternalErrorMessage($(internalErrorMessage));

//        messages.setOutOfSyncCaption(SpmotResourceManager.$(SpmotResourceBundle.outOfSyncCaption));
//        messages.setOutOfSyncMessage(SpmotResourceManager.$(SpmotResourceBundle.outOfSyncMessage));

            messages.setSessionExpiredCaption($(sessionExpiredCaption));
            messages.setSessionExpiredMessage($(sessionExpiredMessage));


            return messages;
        });

        getService().addSessionInitListener(event -> {
//            event.getService().setSystemMessagesProvider(new SpmotSystemMessagesProvider());
            event.getSession().addBootstrapListener(new SpmotBootstrapListener());

            event.getSession().addRequestHandler(SessionManager.setupMDC_RequestHandler());
            event.getSession().addRequestHandler(new LoggerRequestHandler());
            event.getSession().addRequestHandler(new SpmotUnsupportedBrowserHandler());

        });

        // todo - не выполняет то что должен выполнять
        getService().addServiceDestroyListener(l -> MDC.clear());

    }

/*    @Override
    protected VaadinServletService createServletService(
            DeploymentConfiguration deploymentConfiguration)
            throws ServiceException
    {
        SpmotVaadinServletService service = new SpmotVaadinServletService(this, deploymentConfiguration);
        service.init();
        return service;
    }*/
}