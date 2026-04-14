package com.zilfaan.smartcampus.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.zilfaan.smartcampus.exceptions.RoomNotEmptyException;
import com.zilfaan.smartcampus.models.DataStore;
import com.zilfaan.smartcampus.models.Room;

/**
 * Resource class for managing Room entities in the Smart Campus API.
 * Supports CRUD operations and business logic for safe deletion.
 *
 * @author Zilfaan Zaki Sulfikhan
 */
@Path("/rooms")
public class SensorRoom {
        private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(SensorRoom.class.getName());
    /**
     * Retrieves all rooms in the system.
     * @return Collection of Room objects
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Room> getRooms() {
            LOGGER.info("[GET /rooms] Fetching all rooms");
        return DataStore.rooms.values();
    }

    /**
     * Creates a new room with a generated unique ID.
     * @param room Room object from request body
     * @param uriInfo URI context for building resource URI
     * @return 201 Created with Room entity
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRoom(Room room, @Context UriInfo uriInfo) {
            LOGGER.info("[POST /rooms] Creating room: " + room);
        String generatedId = java.util.UUID.randomUUID().toString();
        room.setId(generatedId);
        DataStore.rooms.put(generatedId, room);
        URI uri = uriInfo.getAbsolutePathBuilder().path(generatedId).build();
        return Response.created(uri).entity(room).build();
    }

    /**
     * Retrieves a specific room by ID.
     * @param id Room ID
     * @return Room object or null if not found
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Room getRoom(@PathParam("id") String id) {
            LOGGER.info("[GET /rooms/" + id + "] Fetching room by ID");
        return DataStore.rooms.get(id);
    }

    /**
     * Deletes a room if it is empty (no sensors assigned).
     * @param id Room ID
     * @return 200 OK if deleted, 404 if not found, 409 if not empty
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRoom(@PathParam("id") String id) {
        LOGGER.info("[DELETE /rooms/" + id + "] About to delete room");
        Room room = DataStore.rooms.get(id);
        LOGGER.info("[DELETE /rooms/" + id + "] deleteRoom function called");
        if (room == null) {
            LOGGER.warning("[DELETE /rooms/" + id + "] Room not found");
            return Response.status(404).entity("Room not found").build();
        }
        if (!room.getSensorIds().isEmpty()) {
            LOGGER.warning("[DELETE /rooms/" + id + "] Room not empty");
            throw new RoomNotEmptyException();
        }
        DataStore.rooms.remove(id);
        LOGGER.info("[DELETE /rooms/" + id + "] Deleted room");
        return Response.ok().entity("Room deleted").build();
    }
}

