/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zilfaan.smartcampus.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.zilfaan.smartcampus.exceptions.SensorUnavailableException;
import com.zilfaan.smartcampus.models.DataStore;
import com.zilfaan.smartcampus.models.Sensor;
import com.zilfaan.smartcampus.models.SensorReading;

/**
 * Resource class for managing historical sensor readings for a specific sensor.
 * Supports retrieval and creation of readings, and updates parent sensor value.
 *
 * @author Zilfaan Zaki Sulfikhan
 */
public class SensorReadingResource {

    private String sensorId;

    /**
     * Constructs a SensorReadingResource for a specific sensor.
     * @param sensorId Sensor ID
     */
    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    /**
     * Retrieves all readings for the associated sensor.
     * @return List of SensorReading objects
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorReading> get() {
        return DataStore.readings.getOrDefault(sensorId, new ArrayList<>());
    }

    /**
     * Adds a new reading for the sensor and updates the sensor's current value.
     * @param r SensorReading object from request body
     * @return 201 Created with SensorReading entity, 403 if sensor unavailable
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(SensorReading r) {
        Sensor s = DataStore.sensors.get(sensorId);
        if (s.getStatus().equals("MAINTENANCE")) {
            throw new SensorUnavailableException();
        }
        DataStore.readings.computeIfAbsent(sensorId, k -> new ArrayList<>()).add(r);
        s.setCurrentValue(r.getValue());
        return Response.status(201).entity(r).build();
    }
}
 