/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zilfaan.smartcampus.resources;

import com.zilfaan.smartcampus.models.DataStore;
import com.zilfaan.smartcampus.models.Room;
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

/**
 *
 * @author zilfa
 */
@Path("/rooms")
public class SensorRoom {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Room> getRooms() {
        return DataStore.rooms.values();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRoom(Room room, @Context UriInfo uriInfo) {

        DataStore.rooms.put(room.getId(), room);
        //TODO: make the id not come from response and instead generate in here

        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(room.getId())
                .build();

        return Response.created(uri).entity(room).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Room getRoom(@PathParam("id") String id) {
        return DataStore.rooms.get(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRoom(@PathParam("id") String id) {

        Room room = DataStore.rooms.get(id);

        if (room == null) {
            return Response.status(404).build();
        }

        if (!room.getSensorIds().isEmpty()) {
            throw new RuntimeException("Room not empty");
            //TODO: Replace with custom exception
        }

        DataStore.rooms.remove(id);

        return Response.ok().build();
    }
}

