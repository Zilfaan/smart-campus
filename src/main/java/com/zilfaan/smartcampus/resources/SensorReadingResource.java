/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zilfaan.smartcampus.resources;

import com.zilfaan.smartcampus.models.DataStore;
import com.zilfaan.smartcampus.models.Sensor;
import com.zilfaan.smartcampus.models.SensorReading;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

/**
 *
 * @author zilfa
 */
public class SensorReadingResource {

    private String sensorId;

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    @GET
    public List<SensorReading> get() {
        return DataStore.readings.getOrDefault(sensorId, new ArrayList<>());
    }

    @POST
    public Response add(SensorReading r) {

        Sensor s = DataStore.sensors.get(sensorId);

        if (s.getStatus().equals("MAINTENANCE")) {
            throw new RuntimeException("Sensor unavailable exception");
        }
        //TODO: Replace with custom exception

        DataStore.readings
                .computeIfAbsent(sensorId, k -> new ArrayList<>())
                .add(r);

        s.setCurrentValue(r.getValue());

        return Response.status(201).build();
    }
}
