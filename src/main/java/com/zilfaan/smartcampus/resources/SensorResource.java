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
 *
 * @author zilfa
 */
@Path("/sensors")
public class SensorResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Sensor sensor, @Context UriInfo uriInfo) {
        if (!DataStore.rooms.containsKey(sensor.getRoomId())) {
            throw new LinkedResourceNotFoundException();
        }

        // Generate a unique ID for the sensor
        String generatedId = java.util.UUID.randomUUID().toString();
        sensor.setId(generatedId);
        DataStore.sensors.put(generatedId, sensor);

        DataStore.rooms.get(sensor.getRoomId())
            .getSensorIds()
            .add(generatedId);

        URI uri = uriInfo.getAbsolutePathBuilder()
            .path(generatedId)
            .build();

        return Response.created(uri).entity(sensor).build();
    }

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

    @Path("/{id}/readings")
    @Produces(MediaType.APPLICATION_JSON)
    public SensorReadingResource readings(@PathParam("id") String id) {
        return new SensorReadingResource(id);
    }
}
