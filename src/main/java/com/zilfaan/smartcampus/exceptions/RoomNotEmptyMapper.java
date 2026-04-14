package com.zilfaan.smartcampus.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.zilfaan.smartcampus.models.ErrorMessage;

/**
 * Exception mapper for RoomNotEmptyException.
 * Returns 409 response when attempting to delete a room that still contains sensors
 *
 * @author Zilfaan Zaki Sulfikhan
 */
@Provider
public class RoomNotEmptyMapper implements ExceptionMapper<RoomNotEmptyException> {

        /**
         * Converts RoomNotEmptyException to a 409 response.
         * @param ex the thrown exception
         * @return HTTP 409 with error details in JSON
         */
        @Override
        public Response toResponse(RoomNotEmptyException ex) {
        ErrorMessage error = new ErrorMessage(
            Response.Status.CONFLICT.getStatusCode(),
            "RoomNotEmpty",
            "Room contains active sensors"
        );
        return Response.status(Response.Status.CONFLICT)
            .entity(error)
            .build();
        }
}