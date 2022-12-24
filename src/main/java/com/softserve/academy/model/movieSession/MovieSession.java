package com.softserve.academy.model.movieSession;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.softserve.academy.model.Movie;
import com.softserve.academy.model.Position;
import com.softserve.academy.model.cinema.PhysicalSeat;
import com.softserve.academy.model.cinema.Room;
import com.softserve.academy.model.cinema.util.RoomException;
import com.softserve.academy.model.movieSession.util.MovieSessionException;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class MovieSession {
    private int id;
    private Movie movie;
    private Room room;
    private LocalDateTime dateTime;
    private double price;
    private ArrayList<ArrayList<MovieSessionSeat>> seats;

    public MovieSession(int id, Movie movie, Room room, LocalDateTime dateTime, double price) {
        this.id = id;
        this.movie = movie;
        this.room = room;
        this.dateTime = dateTime;
        this.price = price;
        this.seats = initializeSeats(room);

    }

    public MovieSession() {
    }

    private ArrayList<ArrayList<MovieSessionSeat>> initializeSeats(Room room) {
        ArrayList<ArrayList<MovieSessionSeat>> seatsList = new ArrayList<>();
        for (int i = 0; i < room.getRows(); i++) {
            ArrayList<MovieSessionSeat> row = new ArrayList<>();

            for (int j = 0; j < room.getPlacesInRow(); j++) {
                row.add(new MovieSessionSeat(room.getSeat(i,j),true));
            }

            seatsList.add(row);
        }
        return seatsList;
    }



    public void setId(int id) throws MovieSessionException {
        if (id < 0)
            throw new MovieSessionException("Movie session ID cannot be negative");
        this.id = id;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

//    public void setRoom(Room room) {
//        this.room = room;
//        if (seats.size() != 0) {
//            seats.removeAll(seats);
//        }
//        for (int i = 0; i < room.getRows(); i++) {
//            ArrayList<MovieSessionSeat> row = new ArrayList<>();
//
//            for (int j = 0; j < room.placesInRow(); j++) {
//                row.add(new MovieSessionSeat(room.getSeat(i, j), true));
//            }
//            seats.add(row);
//        }
//    }


    public void setRoom(Room room) {
        this.room = room;
    }

    public void setDateTime(LocalDateTime dateTime) throws MovieSessionException {
        if (dateTime.isBefore(LocalDateTime.now()))
            throw new MovieSessionException("Movie screening date cannot be in the past.");
        this.dateTime = dateTime;
    }

    public void setPrice(double price) throws MovieSessionException {
        if (price <= 0)
            throw new MovieSessionException("Ticket price cannot be negative.");
        this.price = Math.round(price * 100.0) / 100.0;
    }

    public boolean checkIfSeatIsAvailable(int row, int place) throws RoomException {
        try {
            return seats.get(row).get(place).isAvailable();
        } catch (IndexOutOfBoundsException e) {
            throw new RoomException(
                    "Selected place does not exist.");
        }
    }
    @JsonIgnore
    public int getNumberAvailableSeats() throws RoomException {
        int availableSeats = 0;
        for (int i = 0; i < this.getRoom().getRows(); i++) {
            for (int j = 0; j < this.getRoom().getPlacesInRow(); j++) {
                if (checkIfSeatIsAvailable(i, j)) {
                    availableSeats++;
                }
            }
        }
        return availableSeats;
    }


    public boolean takeSeat(int row, int place) throws RoomException {
        if (checkIfSeatIsAvailable(row, place)) {
            seats.get(row).get(place).setAvailable(false);
            return true;
        }
        return false;
    }

    public boolean freeSeat(int row, int place) throws RoomException {
        if (!checkIfSeatIsAvailable(row, place)) {
            seats.get(row).get(place).setAvailable(true);
            return true;
        }
        return false;
    }

    public PhysicalSeat getPhysicalSeat(int row, int place) throws RoomException {
        return this.getSeats().get(row).get(place).getPhysicalSeat();
    }



    public ArrayList<Position> getSeatCoordinates(ArrayList<MovieSessionSeat> orderSeats){
        ArrayList<Position> positionArrayList = new ArrayList<>();
        for (int i = 0; i < seats.size(); i++) {
            for (int j = 0; j < seats.get(i).size(); j++) {
                if(!seats.get(i).get(j).isAvailable()){
                    for (int k = 0; k < orderSeats.size(); k++) {
                        if(seats.get(i).get(j).equals(orderSeats.get(k))){
                            positionArrayList.add(new Position(i,j));
                        }
                    }

                }
            }
        }
        return positionArrayList;
    }

    @Override
    public String toString() {
        int availableSeats = 0;

        try {
            availableSeats = this.getNumberAvailableSeats();
        }
        catch (RoomException exception) {
            exception.printStackTrace();
        }

        String dayOfWeek = getDateTime().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String month = getDateTime().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int day = getDateTime().getDayOfMonth();
        int hour = this.getDateTime().getHour();
        int minute = this.getDateTime().getMinute();
        double price = this.getPrice();


        return String.format("Movie session number: %d\nRoom number: %d\nShow date: %s, %s, %d\nShow time: %d:%d\nTicket price: %.2f UAH\n" +
                        "Available seats: %d\n",
                this.getId(), this.getRoom().getRoomNumber(), dayOfWeek, month, day, hour, minute, price, availableSeats);
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Room getRoom() {
        return room;
    }

    public int getId() {
        return id;
    }

    public ArrayList<ArrayList<MovieSessionSeat>> getSeats() {
        return seats;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setSeats(ArrayList<ArrayList<MovieSessionSeat>> seats) {
        this.seats = seats;
    }
}


