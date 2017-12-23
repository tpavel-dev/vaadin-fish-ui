package kz.kcell.apps.spmot.servlet;

import kz.kcell.apps.common.HttpUtils;
import kz.kcell.apps.spmot.mobile.vaadin.controller.ServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 27 02 2015
 */
 @WebServlet(value = "/ping")
@Slf4j
@Component
public class PingAppServlet  extends HttpServlet {

//    private PingService pingService;

    @Autowired
    private ServiceFactory serviceFactory;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        try {
            HttpUtils.disableCache(resp);
//            resp.getWriter().append("pong");
            resp.getWriter().append("This service working successfully.");
            resp.getWriter().close();
        }catch (Exception exc) {
            log.error(exc.getLocalizedMessage(), exc);
            HttpUtils.disableCache(resp);
            resp.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
//            resp.getWriter().write(exc.getLocalizedMessage());
            exc.printStackTrace(resp.getWriter());
            resp.getWriter().close();
        }
    }


    public ServiceFactory getServiceFactory() {
        if(serviceFactory == null) {
//            serviceFactory = new ServiceFactory();
            serviceFactory = ServiceFactory.instance;
        }
        return serviceFactory;
    }

}
