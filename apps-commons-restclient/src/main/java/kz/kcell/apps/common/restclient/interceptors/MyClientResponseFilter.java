package kz.kcell.apps.common.restclient.interceptors;


import kz.kcell.apps.common.exceptions.BaseException;
import kz.kcell.apps.common.exceptions.code.ExceptionCodeEnum;
import kz.kcell.apps.spmot.rest.exception.BE_ExceptionMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 30 10 2014
 */
@Provider
@Slf4j
public class MyClientResponseFilter implements ClientResponseFilter {
    @Override @SuppressWarnings("unchecked")
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        //log.debug("{} {}", responseContext, requestContext);

        String excClassName      = "";
        String codeEnumClassName = "";
        String codeValueName     = "";
        String message           = "";
        String stackTrace        = "";

        try {
            excClassName      = getHeaderValue(BE_ExceptionMapper.Fields.exception.name() , responseContext);
            if(StringUtils.isBlank(excClassName)) return;
            log.info("excClassName: {}", excClassName);

            codeEnumClassName = findHeaderValue(BE_ExceptionMapper.Fields.code_class.name() , responseContext);
            codeValueName     = findHeaderValue(BE_ExceptionMapper.Fields.code_value.name() , responseContext);
            try {
                message           = findHeaderValue(BE_ExceptionMapper.Fields.remote_message.name()   , responseContext);
            } catch (Exception exc) {
                    log.warn("http header "+BE_ExceptionMapper.Fields.remote_message.name() + ": "+exc.getLocalizedMessage());
//                log.error(exc.getLocalizedMessage(), exc);
            }
            try {
                stackTrace        = findHeaderValue(BE_ExceptionMapper.Fields.stack_trace.name(), responseContext);
            } catch (Exception exc) {
//                log.warn("http header "+BE_ExceptionMapper.Fields.stack_trace.name() + ": "+exc.getLocalizedMessage());
//                log.error(exc.getLocalizedMessage(), exc);
            }



            Class codeEnumClass = Class.forName(codeEnumClassName);
            if (codeEnumClass.isEnum() == false) {
                throw new HeaderValueNotFoundException("Header " + codeEnumClassName + " is not enum");
            }
            Enum value = Enum.valueOf(codeEnumClass, codeValueName);

            Class excClass = Class.forName(excClassName);

            BaseException exc = (BaseException) excClass.newInstance();
            exc.setCode((ExceptionCodeEnum) value);
            exc.setRemoteMessage(message);
            exc.setRemoteStackTrace(stackTrace);

            throw exc;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot make Exception for " + excClassName, e);
        } catch (InstantiationException e) {
            throw new RuntimeException("Cannot make Exception for " + excClassName, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot make Exception for " + excClassName, e);
        }
//        throw new IOException(excClassName);
    }


    @SuppressWarnings("unchecked")
    private <T> T getHeaderValue(String headerName, ClientResponseContext responseContext) {
        List<String> h = responseContext.getHeaders().get(headerName);
        if (h == null || h.size() == 0) {
            return null;
        }
        return (T) h.get(0);

    }

    private <T> T findHeaderValue(String headerName, ClientResponseContext responseContext) {
        T v = getHeaderValue(headerName, responseContext);
        if (v == null) {
            throw new HeaderValueNotFoundException(headerName);
        }
        return v;
    }

;
}
