/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zilfaan.smartcampus.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.zilfaan.smartcampus.models.ErrorMessage;

/**
 * Global exception mapper for uncaught exceptions.
 * @author Zilfaan Zaki Sulfikhan
 */
@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

        /**
         * Converts any uncaught exception to a 500 Internal Server Error response.
         * @param ex the thrown exception
         * @return HTTP 500 with generic error details in JSON
         */
        @Override
        public Response toResponse(Throwable ex) {
        ex.printStackTrace();
        
        ErrorMessage error = new ErrorMessage(
            Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
            "InternalServerError",
            "Something went wrong"
        );
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(error)
            .build();
        }
}
