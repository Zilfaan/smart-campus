package com.zilfaan.smartcampus.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory data store for all rooms, sensors, and readings.
 *
 * @author Zilfaan Zaki Sulfikhan
 */
public class DataStore {

    public static Map<String, Room> rooms = new HashMap<>();
    public static Map<String, Sensor> sensors = new HashMap<>();
    public static Map<String, List<SensorReading>> readings = new HashMap<>();
}
