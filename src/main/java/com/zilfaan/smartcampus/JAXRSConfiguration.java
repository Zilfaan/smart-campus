package com.zilfaan.smartcampus;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures JAX-RS for the Smart Campus application.
 * Sets the base API path and registers resources and providers.
 *
 * @author Zilfaan Zaki Sulfikhan
 */
@ApplicationPath("/api/v1")
public class JAXRSConfiguration extends Application {
    
}
