package com.softserve.academy.dataSource;

import com.softserve.academy.model.cinema.Room;
import com.softserve.academy.model.cinema.util.RoomException;

import java.util.ArrayList;

public class RoomDataSource {
    private ArrayList<Room> roomList = new ArrayList<>();

    public RoomDataSource() throws RoomException {
        initialRoomData();
    }

    public void initialRoomData() throws RoomException {
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
