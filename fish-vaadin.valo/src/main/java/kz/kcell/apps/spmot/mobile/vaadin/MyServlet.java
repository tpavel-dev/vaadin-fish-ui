package kz.kcell.apps.spmot.mobile.vaadin;

import com.vaadin.server.*;
import kz.kcell.apps.spmot.logging.MDCKeys;
import org.slf4j.MDC;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class MyServlet extends VaadinServlet {

    private class MyVaadinServletService extends VaadinServletService {
        public MyVaadinServletService(VaadinServlet servlet
                , DeploymentConfiguration deploymentConfiguration)
                throws ServiceException {
            super(servlet, deploymentConfiguration);
        }

        @Override
        protected List<RequestHandler> createRequestHandlers() throws ServiceException {
            List<RequestHandler> requestHandlers = super.createRequestHandlers();
            requestHandlers.add(new MyUnsupportedBrowserHandler());
            return requestHandlers;
        }

        @Override
        public void requestStart(VaadinRequest request, VaadinResponse response) {
            super.requestStart(request, response);
            MDC.put(MDCKeys.ip.name(), request.getRemoteAddr());
        }

        @Override
        public void requestEnd(VaadinRequest request, VaadinResponse response, VaadinSession session) {
            super.requestEnd(request, response, session);
            MDC.clear();
        }
    }

    private class MyUnsupportedBrowserHandler extends UnsupportedBrowserHandler {

        @Override
        protected void writeBrowserTooOldPage(com.vaadin.server.VaadinRequest request
                , com.vaadin.server.VaadinResponse response) throws IOException
        {

            Writer pageWriter = response.getWriter();

            WebBrowser browser = Page.getCurrent().getWebBrowser();

            if (browser.isIE()) {
                pageWriter .write("Internet Explorer "
                        + browser.getBrowserMajorVersion()
                        + " is too old and is not supported. Please upgrade to a newer version");
            } else if (browser.isOpera()) {
                pageWriter .write("Opera "
                        + browser.getBrowserMajorVersion()
                        + " is too old and is not supported. Please upgrade to a newer version");
            } else if (browser.isFirefox()) {
                pageWriter .write("Firefox "
                        + browser.getBrowserMajorVersion()
                        + " is too old and is not supported. Please upgrade to a newer version");
            } else if (browser.isSafari()) {
                pageWriter .write("Safari "
                        + browser.getBrowserMajorVersion()
                        + " is too old and is not supported. Please upgrade to a newer version");
            }
        };
    }

    @Override
    protected VaadinServletService createServletService(
            DeploymentConfiguration deploymentConfiguration)
            throws ServiceException
    {
        MyVaadinServletService service =
                new MyVaadinServletService(this, deploymentConfiguration);
        service.init();
        return service;
    }
}