package com.softserve.academy.model.cinema;

import com.softserve.academy.model.cinema.util.RoomException;
import com.softserve.academy.model.cinema.util.TypeOfSeat;

import java.util.ArrayList;


public class Room {
    private int roomNumber;
    private int placesInRow;
    private int rows;
    private ArrayList<ArrayList<PhysicalSeat>> seats;

    public Room(int roomNumber, int rows, int placesInRow) throws RoomException {

        if(rows <= 0 || placesInRow <= 0) {
            throw new RoomException("The room must contain at least one seat");
        }

        this.seats = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            ArrayList<PhysicalSeat> row = new ArrayList<>();
            for (int j = 0; j < placesInRow; j++) {
                row.add(new PhysicalSeat(TypeOfSeat.Standard));
            }
            seats.add(row);
        }

        this.roomNumber = roomNumber;
        this.placesInRow = placesInRow;
        this.rows = rows;
    }

    public int getNumberOfSeats() {
        return this.placesInRow * this.rows;
    }

    public PhysicalSeat getSeat(int row, int place) {
        return seats.get(row).get(place);
    }

    public int getNumberOfPlacesInRow() {
        return placesInRow;
    }

    public int getNumberOfRows() {
        return rows;
    }

    public int getRoomNumber() {
        return roomNumber;
    }


}
