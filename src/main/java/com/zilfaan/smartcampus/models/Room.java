package com.zilfaan.smartcampus.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contains room metadata and a list of assigned sensor IDs.
 *
 * @author Zilfaan Zaki Sulfikhan
 */
public class Room {
    private String id;
    private String name;
    private int capacity;
    private List<String> sensorIds = new ArrayList<>();

    /**
     * Constructs a Room object.
     * @param id Room ID
     * @param name Room name
     * @param capacity Maximum occupancy
     */
    public Room(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("capacity") int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }
    /**
     * Gets the room ID.
     */
    public String getId() { return id; }
    /**
     * Sets the room ID.
     */
    public void setId(String id) { this.id = id; }
    /**
     * Gets the room name.
     */
    public String getName() { return name; }
    /**
     * Sets the room name.
     */
    public void setName(String name) { this.name = name; }
    /**
     * Gets the room capacity.
     */
    public int getCapacity() { return capacity; }
    /**
     * Sets the room capacity.
     */
    public void setCapacity(int capacity) { this.capacity = capacity; }
    /**
     * Gets the list of sensor IDs assigned to this room.
     */
    public List<String> getSensorIds() { return sensorIds; }
    /**
     * Sets the list of sensor IDs for this room.
     */
    public void setSensorIds(List<String> sensorIds) { this.sensorIds = sensorIds; }
}
