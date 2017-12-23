package kz.kcell.apps.common.restclient;

import kz.kcell.apps.common.exceptions.BaseException;

import javax.ws.rs.client.ResponseProcessingException;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 13 11 2014
 */
public class RestClientErrorHandler {
    public static <T> T handle(ResponseProcessingException e) throws BaseException {
        Throwable t = e.getCause();
        if(t != null && t instanceof BaseException)
            throw (BaseException)t;
        else throw e;
    }
}
