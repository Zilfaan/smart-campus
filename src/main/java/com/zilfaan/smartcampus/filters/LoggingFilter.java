/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zilfaan.smartcampus.filters;

import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * JAX-RS filter for logging all API requests and responses
 *
 * @author Zilfaan Zaki Sulfikhan
 */
@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger LOGGER = Logger.getLogger(LoggingFilter.class.getName());

    /**
     * Logs incoming HTTP request method and URI
     * @param requestContext JAX-RS request context
     */
    @Override
    public void filter(ContainerRequestContext requestContext) {
        LOGGER.log(java.util.logging.Level.INFO, "REQUEST: {0} {1}", new Object[]{requestContext.getMethod(), requestContext.getUriInfo().getRequestUri()});
    }

    /**
     * Logs outgoing HTTP response status code
     * @param requestContext JAX-RS request context
     * @param responseContext JAX-RS response context
     */
    @Override
    public void filter(ContainerRequestContext requestContext,
            ContainerResponseContext responseContext) {
        LOGGER.log(java.util.logging.Level.INFO, "RESPONSE: {0}", responseContext.getStatus());
    }
}
