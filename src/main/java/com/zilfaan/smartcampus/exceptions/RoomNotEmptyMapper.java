/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zilfaan.smartcampus.exceptions;

import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

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
        return Response.status(Response.Status.CONFLICT)
            .entity(Map.of(
                "error", "Room is not empty",
                "message", "Room contains active sensors"
            ))
            .build();
        }
}