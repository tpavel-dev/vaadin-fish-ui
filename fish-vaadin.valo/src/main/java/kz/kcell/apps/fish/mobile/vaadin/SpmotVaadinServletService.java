//package kz.kcell.apps.spmot.mobile.vaadin;
//
//import com.vaadin.server.*;
//
//import java.util.List;
//
//public class SpmotVaadinServletService extends VaadinServletService {
//
//    public SpmotVaadinServletService(
//            VaadinServlet servlet,
//            DeploymentConfiguration deploymentConfiguration
//    ) throws ServiceException {
//        super(servlet, deploymentConfiguration);
//    }
//
//    @Override
//    protected List<RequestHandler> createRequestHandlers() throws ServiceException {
//        List<RequestHandler> requestHandlers = super.createRequestHandlers();
//        removeUnsuportedBrowserHandler(requestHandlers);
//        requestHandlers.add(new SpmotUnsupportedBrowserHandler());
//        requestHandlers.add(new LoggerRequestHandler());
//        return requestHandlers;
//    }
//
//    private void removeUnsuportedBrowserHandler(List<RequestHandler> requestHandlers) {
//        for (RequestHandler handler : requestHandlers) {
//            if (handler instanceof UnsupportedBrowserHandler) {
//                requestHandlers.remove(handler);
//                break;
//            }
//        }
//    }
//}