package com.zilfaan.smartcampus.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.zilfaan.smartcampus.models.ErrorMessage;

/**
 * Exception mapper for LinkedResourceNotFoundException.
 * Returns HTTP 422 when a referenced resource does not exist
 *
 * @author Zilfaan Zaki Sulfikhan
 */
@Provider
public class LinkedResourceNotFoundMapper implements ExceptionMapper<LinkedResourceNotFoundException> {

        /**
         * Converts LinkedResourceNotFoundException to a 422 response.
         * @param ex the thrown exception
         * @return HTTP 422 with error details in JSON
         */
        @Override
        public Response toResponse(LinkedResourceNotFoundException ex) {
        ErrorMessage error = new ErrorMessage(
            422,
            "LinkedResourceNotFound",
            "Room does not exist"
        );
        return Response.status(422)
            .entity(error)
            .build();
        }
}
