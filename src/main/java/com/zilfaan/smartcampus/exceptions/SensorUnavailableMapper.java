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
 * Exception mapper for SensorUnavailableException.
 * Returns HTTP 403 when a sensor is under maintenance and cannot accept readings
 *
 * @author Zilfaan Zaki Sulfikhan
 */
@Provider
public class SensorUnavailableMapper implements ExceptionMapper<SensorUnavailableException> {

        /**
         * Converts SensorUnavailableException to a 403 response.
         * @param ex the thrown exception
         * @return HTTP 403 with error details in JSON
         */
        @Override
        public Response toResponse(SensorUnavailableException ex) {
        ErrorMessage error = new ErrorMessage(
            Response.Status.FORBIDDEN.getStatusCode(),
            "SensorUnavailable",
            "Sensor is under maintenance"
        );
        return Response.status(Response.Status.FORBIDDEN)
            .entity(error)
            .build();
        }
}
