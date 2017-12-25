package kz.kcell.apps.fish.resources.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 14 09 2015
 */
@Path("/resources")
@Consumes({ "application/json" })
//@Produces({ "application/json" })
@Produces({ "text/html" })
public interface StringResourcesRestService {

    @GET
    @Path("/")
    @Produces({ "text/html" })
    String getlist(String queryString);

}
