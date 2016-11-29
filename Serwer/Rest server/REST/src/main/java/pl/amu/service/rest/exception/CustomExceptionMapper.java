package pl.amu.service.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by adrian.perek on 2016-03-19.
 */
@Provider
public class CustomExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public Response toResponse(IllegalArgumentException exception) {
        return Response.ok("Illegal Argument Exception Caught").build();
    }
}
