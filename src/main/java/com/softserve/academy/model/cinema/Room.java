package com.softserve.academy.model.cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softserve.academy.model.cinema.util.TypeOfSeat;

import java.util.ArrayList;


public class Room {
    private int roomNumber;
    private int placesInRow;
    private int rows;
    private ArrayList<ArrayList<PhysicalSeat>> seats;

    public Room(int roomNumber, int rows, int placesInRow)  {


        this.roomNumber = roomNumber;
        this.placesInRow = placesInRow;
        this.rows = rows;
        this.seats = initializeSeats(rows, placesInRow);
    }

    public Room() {
    }

    private ArrayList<ArrayList<PhysicalSeat>> initializeSeats(int rows, int placesInRow) {
        ArrayList<ArrayList<PhysicalSeat>> seatsList = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<PhysicalSeat> row = new ArrayList<>();
            for (int j = 0; j < placesInRow; j++) {
                row.add(new PhysicalSeat(TypeOfSeat.Standard));
            }
            seatsList.add(row);
        }
        return seatsList;
    }
    @JsonIgnore
    public int getNumberOfSeats() {
        return this.placesInRow * this.rows;
    }

    public PhysicalSeat getSeat(int row, int place) {
        return seats.get(row).get(place);
    }

    public int getPlacesInRow() {
        return placesInRow;
    }

    public int getRows() {
        return rows;
    }

    public int getRoomNumber() {
        return roomNumber;
    }


}
