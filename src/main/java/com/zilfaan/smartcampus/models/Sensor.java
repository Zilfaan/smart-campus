package com.zilfaan.smartcampus.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contains sensor metadata, status, and parent room ID.
 *
 * @author Zilfaan Zaki Sulfikhan
 */
public class Sensor {
    private String id;
    private String type;
    private String status;
    private double currentValue;
    private String roomId;

    /**
     * Constructs a Sensor object.
     * @param id Sensor ID
     * @param type Sensor type
     * @param status Sensor status
     * @param roomId Parent room ID
     */
    public Sensor(@JsonProperty("id") String id, @JsonProperty("type") String type, @JsonProperty("status") String status, @JsonProperty("roomId") String roomId) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.roomId = roomId;
    }
    /**
     * Gets the sensor ID.
     */
    public String getId() { return id; }
    /**
     * Sets the sensor ID.
     */
    public void setId(String id) { this.id = id; }
    /**
     * Gets the sensor type.
     */
    public String getType() { return type; }
    /**
     * Sets the sensor type.
     */
    public void setType(String type) { this.type = type; }
    /**
     * Gets the sensor status.
     */
    public String getStatus() { return status; }
    /**
     * Sets the sensor status.
     */
    public void setStatus(String status) { this.status = status; }
    /**
     * Gets the current value of the sensor.
     */
    public double getCurrentValue() { return currentValue; }
    /**
     * Sets the current value of the sensor.
     */
    public void setCurrentValue(double currentValue) { this.currentValue = currentValue; }
    /**
     * Gets the parent room ID for this sensor.
     */
    public String getRoomId() { return roomId; }
    /**
     * Sets the parent room ID for this sensor.
     */
    public void setRoomId(String roomId) { this.roomId = roomId; }
}
