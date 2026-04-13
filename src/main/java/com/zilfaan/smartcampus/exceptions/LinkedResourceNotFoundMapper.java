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
        return Response.status(422)
            .entity(Map.of(
                "error", "Invalid reference",
                "message", "Room does not exist"
            ))
            .build();
        }
}
