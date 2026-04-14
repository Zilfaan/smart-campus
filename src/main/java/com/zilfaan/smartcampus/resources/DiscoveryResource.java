package com.zilfaan.smartcampus.resources;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root discovery endpoint for the Smart Campus API.
 * Provides API metadata, contact info, and resource links
 *
 * @author Zilfaan Zaki Sulfikhan
 */
@Path("/")
public class DiscoveryResource {
    /**
     * Returns API metadata and resource links for discovery.
     * @return JSON object with version, contact, and resource links
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfo() {

        Map<String, Object> data = new HashMap<>();

        data.put("version", "1.0");
        data.put("contact", "zilfaan.20232038@iit.ac.lk");

        Map<String, String> links = new HashMap<>();
        links.put("rooms", "/api/v1/rooms");
        links.put("sensors", "/api/v1/sensors");

        data.put("resources", links);
        return Response.ok(data).build();
    }
}
