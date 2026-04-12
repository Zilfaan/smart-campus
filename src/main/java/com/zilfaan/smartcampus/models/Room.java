package com.zilfaan.smartcampus.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.ArrayList;

public class Room {

    private String id;
    private String name;

    private int capacity;
    private List<String> sensorIds = new ArrayList<>();

    public Room(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("capacity") int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getSensorIds() {
        return sensorIds;
    }

    public void setSensorIds(List<String> sensorIds) {
        this.sensorIds = sensorIds;
    }

}
