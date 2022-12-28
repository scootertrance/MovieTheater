package com.softserve.academy.dataSource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.softserve.academy.model.cinema.Room;
import com.softserve.academy.model.cinema.util.RoomException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RoomDataSource {
    private ArrayList<Room> roomList = new ArrayList<>();

    public RoomDataSource() throws RoomException, IOException {
        initialRoomData();

    }

    public void initialRoomData() throws RoomException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        roomList = mapper.readValue(new File(
                        "src/main/java/com/softserve/academy/resources/roomList.json"),
                new TypeReference<>() {});
        roomList.add(new Room(1,10,20));
        roomList.add(new Room(2,25,30));
        roomList.add(new Room(3,20,25));
    }

    public void updateRoomsJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        mapper.writeValue(new File(
                        "src/main/java/com/softserve/academy/resources/roomList.json"),
                roomList);
    }
    public void addRooms(){
        roomList.add(new Room(1,10,20));
        roomList.add(new Room(2,25,30));
        roomList.add(new Room(3,20,25));
    }

    public Room getRoomByRoomNumber(int roomNumber) {
        for(Room room : roomList) {
            if(room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
    public ArrayList<Room> getRooms() {return roomList;}
}
