package com.zilfaan.smartcampus.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contains reading ID, timestamp, and value.
 *
 * @author Zilfaan Zaki Sulfikhan
 */
public class SensorReading {
    private String id;
    private long timestamp;
    private double value;

    /**
     * Constructs a SensorReading object.
     * @param id Reading ID
     * @param timestamp Epoch time in ms
     * @param value Recorded value
     */
    public SensorReading(@JsonProperty("id") String id, @JsonProperty("timestamp") long timestamp, @JsonProperty("value") double value) {
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
    }
    /**
     * Gets the reading ID.
     */
    public String getId() { return id; }
    /**
     * Sets the reading ID.
     */
    public void setId(String id) { this.id = id; }
    /**
     * Gets the timestamp of the reading.
     */
    public long getTimestamp() { return timestamp; }
    /**
     * Sets the timestamp of the reading.
     */
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    /**
     * Gets the value of the reading.
     */
    public double getValue() { return value; }
    /**
     * Sets the value of the reading.
     */
    public void setValue(double value) { this.value = value; }
}

