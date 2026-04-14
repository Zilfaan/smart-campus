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
        LOGGER.log(java.util.logging.Level.INFO, "-- INCOMING REQUEST --");
        LOGGER.log(java.util.logging.Level.INFO, "Method: {0}", requestContext.getMethod());
        LOGGER.log(java.util.logging.Level.INFO, "URI: {0}", requestContext.getUriInfo().getAbsolutePath());
    }

    /**
     * Logs outgoing HTTP response status code
     * @param requestContext JAX-RS request context
     * @param responseContext JAX-RS response context
     */
    @Override
    public void filter(ContainerRequestContext requestContext,
            ContainerResponseContext responseContext) {
        LOGGER.log(java.util.logging.Level.INFO, "-- OUTGOING RESPONSE --");
        LOGGER.log(java.util.logging.Level.INFO, "Status: {0}", responseContext.getStatus());
    }
}
