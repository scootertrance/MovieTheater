package com.softserve.academy.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private MovieSession movieSession;
    private ArrayList<MovieSessionSeat> seats;

    public Order(int id, Client client, MovieSession movieSession, ArrayList<Position> positions) throws
            SeatAvailabilityException, RoomException {
        this.id = id;
        this.orderDate = java.time.LocalDate.now();
        this.client = client;
        this.movieSession = movieSession;
        this.seats = reserveSeat(positions);
    }

    public Order() {
    }

    public final ArrayList<MovieSessionSeat> reserveSeat(ArrayList<Position> positions) throws RoomException, SeatAvailabilityException {
        ArrayList<MovieSessionSeat> seatsList = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++){
            if(movieSession.takeSeat(positions.get(i).getRow(), positions.get(i).getPlaceInRow())) {
                seatsList.add(movieSession.getSeats().get(positions.get(i).getRow()).get(positions.get(i).getPlaceInRow()));
            }
            else throw new SeatAvailabilityException("Selected seats is already taken");
        }
        return seatsList;
    }
    @JsonIgnore
    public int getNumberOfSeats() {
        return seats.size();
    }


    @JsonIgnore
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

    public String getPositionsInfo(ArrayList<Position> positions){
        StringBuilder sb = new StringBuilder();
        int count = 0;
        sb.append("\nYour seats:\n");
        for(Position position : positions){
            count++;
            sb.append("\n---------\nSeat № ");
            sb.append(count);
            sb.append(":\nRow: ");
            sb.append(position.getRow());
            sb.append("\nPlace: ");
            sb.append(position.getPlaceInRow());
        }
        sb.append("\n---------\n");
        return sb.toString();
    }


    @Override
    public String toString() {
        return String.format("\nOrder id: %d\nMovie: %s\nRoom: %d\nTotal price: %.2f UAH\nOrder date: %s\nClient: %s %s, %s %s",
                this.getId(),this.getMovieSession().getMovie().getTitle(), this.getMovieSession().getRoom().getRoomNumber(),
                this.getTotalPrice(),this.getOrderDate(),this.client.getName(), this.client.getSurname(),
                this.getClient().getEmail(), this.getPositionsInfo(movieSession.getSeatCoordinates(seats)));
    }

}
