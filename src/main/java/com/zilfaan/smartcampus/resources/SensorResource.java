/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zilfaan.smartcampus.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.zilfaan.smartcampus.exceptions.LinkedResourceNotFoundException;
import com.zilfaan.smartcampus.models.DataStore;
import com.zilfaan.smartcampus.models.Sensor;

/**
 * Resource class for managing Sensor entities in the Smart Campus API.
 * Supports creation, retrieval, and sub-resource locator for readings.
 *
 * @author Zilfaan Zaki Sulfikhan
 */
@Path("/sensors")
public class SensorResource {

    /**
     * Registers a new sensor and links it to a room.
     * @param sensor Sensor object from request body
     * @param uriInfo URI context for building resource URI
     * @return 201 with Sensor entity, 422 if room does not exist
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Sensor sensor, @Context UriInfo uriInfo) {
        if (!DataStore.rooms.containsKey(sensor.getRoomId())) {
            throw new LinkedResourceNotFoundException();
        }
        String generatedId = java.util.UUID.randomUUID().toString();
        sensor.setId(generatedId);
        DataStore.sensors.put(generatedId, sensor);
        DataStore.rooms.get(sensor.getRoomId()).getSensorIds().add(generatedId);
        URI uri = uriInfo.getAbsolutePathBuilder().path(generatedId).build();
        return Response.created(uri).entity(sensor).build();
    }

    /**
     * Retrieves all sensors, optionally filtered by type.
     * @param type Optional sensor type filter
     * @return Collection of Sensor objects
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Sensor> get(@QueryParam("type") String type) {
        if (type == null) {
            return DataStore.sensors.values();
        }
        return DataStore.sensors.values().stream()
                .filter(s -> s.getType().equalsIgnoreCase(type))
                .toList();
    }

    /**
     * Sub-resource locator for sensor readings.
     * @param id Sensor ID
     * @return SensorReadingResource instance
     */
    @Path("/{id}/readings")
    @Produces(MediaType.APPLICATION_JSON)
    public SensorReadingResource readings(@PathParam("id") String id) {
        return new SensorReadingResource(id);
    }
}
