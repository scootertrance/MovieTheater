package com.softserve.academy.model.order;

import com.softserve.academy.model.Position;
import com.softserve.academy.model.cinema.util.RoomException;
import com.softserve.academy.model.client.Client;
import com.softserve.academy.model.movieSession.MovieSession;
import com.softserve.academy.model.movieSession.MovieSessionSeat;
import com.softserve.academy.model.order.util.SeatAvailabilityException;


import java.time.LocalDate;
import java.util.ArrayList;

public class Order  {

    private int id;
    private LocalDate orderDate;
    private Client client;
    private ArrayList<MovieSessionSeat> seats;
    private MovieSession movieSession;

    public Order(int id, Client client, MovieSession movieSession, Position...positions) throws
            SeatAvailabilityException, RoomException {
        this.id = id;
        this.orderDate = java.time.LocalDate.now();

        for (int i = 0; i < positions.length; i++) {
            reserveSeat(positions[i].getRow(), positions[i].getPlaceInRow());
        }

    }
    public void reserveSeat(int row, int place) throws RoomException, SeatAvailabilityException {
        if(movieSession.takeSeat(row, place)) {
            seats.add(movieSession.getSeats().get(row).get(place));
        }
        else throw new SeatAvailabilityException("Selected seats is already taken");
    }

    public int getNumberOfSeats() {
        return seats.size();
    }

    public double getTotalPrice() {
        return Math.round(getNumberOfSeats() * this.movieSession.getPrice() * 100) / 100;
    }

    public int getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Client getClient() {
        return client;
    }

    public ArrayList<MovieSessionSeat> getSeats() {
        return seats;
    }

    public MovieSession getMovieSession() {
        return movieSession;
    }
}
