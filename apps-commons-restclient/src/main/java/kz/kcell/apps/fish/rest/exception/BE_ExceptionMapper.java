package kz.kcell.apps.fish.rest.exception;

import kz.kcell.apps.common.exceptions.BaseException;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.PrintWriter;
import java.io.StringWriter;

@Provider
@Component
public class BE_ExceptionMapper implements ExceptionMapper<BaseException> {
    public static enum Fields {
        exception,
        code_class,
        code_value,
        remote_message,
        stack_trace
    }

    @Override
    public Response toResponse(BaseException baseException) {
//        Response.ResponseBuilder r = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
        Response.ResponseBuilder r = Response.ok(baseException);
        r.header(Fields.exception.name(),  baseException.getClass().getName());
        r.header(Fields.code_class.name(), baseException.getCode().getClass().getName());
        r.header(Fields.code_value.name(), baseException.getCode().name());
        r.header(Fields.remote_message.name(),    baseException.getMessage());
        r.header(Fields.stack_trace.name(), getStackTraceAsString(baseException));
//        r.status(402);
//        r.entity(exception.getCode());
        return r.build();
    }

    public static String getStackTraceAsString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
