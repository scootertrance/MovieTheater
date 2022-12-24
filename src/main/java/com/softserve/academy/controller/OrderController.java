package com.softserve.academy.controller;

import com.softserve.academy.dataSource.*;
import com.softserve.academy.model.Movie;
import com.softserve.academy.model.Position;
import com.softserve.academy.model.cinema.util.RoomException;
import com.softserve.academy.model.client.Client;
import com.softserve.academy.model.movieSession.MovieSession;
import com.softserve.academy.model.movieSession.MovieSessionSeat;
import com.softserve.academy.model.order.Order;
import com.softserve.academy.model.order.util.SeatAvailabilityException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderController {
    private OrderDataSource orderDataSource;
    private static int newId = 0;

    public OrderController(OrderDataSource orderDataSource) {
        this.orderDataSource = orderDataSource;


    }

    public OrderDataSource getOrderDataSource() {
        return orderDataSource;
    }

    private int getNewOrderId() {
        for (Order order : this.orderDataSource.getOrderList()) {
            if (newId < order.getId()) {
                newId = order.getId();
            }
        }
        return newId + 1;
    }


    public ArrayList<Order> getOrdersByClientId(int clientId) {
        ArrayList<Order> ordersByClient = new ArrayList<>();
        for (Order order : orderDataSource.getOrderList()) {
            if (order.getClient().getId() == clientId) {
                ordersByClient.add(order);
            }
        }
        return ordersByClient;
    }

    public ArrayList<Order> getOrdersByClientEmail(String email) {
        ArrayList<Order> ordersByClient = new ArrayList<>();
        for (Order order : orderDataSource.getOrderList()) {
            if (order.getClient().getEmail().equals(email)) {
                ordersByClient.add(order);
            }
        }
        return ordersByClient;
    }

    public double getAmountOfClientOrders(ArrayList<Order> ordersByClient) {
        double amountOfClientsOrders = 0;
        for (Order order : ordersByClient) {
            amountOfClientsOrders += order.getTotalPrice();
        }
        return amountOfClientsOrders;
    }

    public Order getMostExpensiveOrder() {
        double orderPrice = 0;
        Order mostExpensiveOrder = null;
        for (Order order : orderDataSource.getOrderList()) {
            if (orderPrice < order.getTotalPrice()) {
                orderPrice = order.getTotalPrice();
                mostExpensiveOrder = order;
            }
        }
        return mostExpensiveOrder;
    }

    private void printMovieList() {
        for (Movie movie : orderDataSource.getMovieSessionDataSource().getMovieDataSource().getMovies()) {
            System.out.println(movie);
        }
    }

    private void printMovieSessionByMovieId(int movieId) {
        for (MovieSession movieSession :
                orderDataSource.getMovieSessionDataSource().getMovieSessionByMovieId(movieId)) {
            System.out.println(movieSession);
        }
    }

    private void printAvailableSeats(int movieSessionId) {
        MovieSession movieSession = orderDataSource.getMovieSessionDataSource().getMovieSessionById(movieSessionId);
        ArrayList<ArrayList<MovieSessionSeat>> seats = movieSession.getSeats();
        System.out.println("Available seats for this movie session ([row-place]): ");
        System.out.print("--------------------------------------------------------------------------------------------" +
                "------------------------------------------------------------------------");

        for (int i = 0; i < seats.size(); i++) {
            System.out.println("\n");
            for (int j = 0; j < seats.get(i).size(); j++) {
                if(seats.get(i).get(j).isAvailable()){

                    System.out.printf("[%d-%d],",i,j);

                }
            }
        }
        System.out.println("\b\n");
        System.out.println("--------------------------------------------------------------------------------------------" +
                "-------------------------------------------------------------------------------------------------");
    }

    private boolean isTherePosition(ArrayList<Position> positions,int row, int place){
        Position nextPosition = new Position(row,place);
        for (Position position : positions){
            if(position.equals(nextPosition)) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Position> choosePlace(Scanner scan, MovieSession movieSession) throws RoomException {
        ArrayList<Position> positionArrayList = new ArrayList<>();
        boolean chooseAnotherPlace = true;



        while (chooseAnotherPlace) {
            System.out.println("Choose a row:");
            int row = scan.nextInt();
            System.out.println("Choose a place in row:");
            int place = scan.nextInt();

            if (movieSession.checkIfSeatIsAvailable(row, place) & !isTherePosition(positionArrayList,row,place)) {
                System.out.println("This place is available for order. Do you want to book this place? Type 'y' or 'n'," +
                        "or 'e' for exit:");
                String firstResponse = scan.next();

                switch (firstResponse) {
                    case "y":
                        positionArrayList.add(new Position(row, place));
                        System.out.println("Do you want to book another place? Type 'y' or 'n':");
                        String secondResponse = scan.next();
                        switch (secondResponse) {
                            case "y":
                                break;
                            case "n":
                                chooseAnotherPlace = false;
                                break;
                            default:
                                System.out.println("You entered wrong data");
                                System.exit(0);
                        }
                        break;
                    case "n":
                        break;
                    case "e":
                        System.out.println("End of the program.");
                        System.exit(0);
                    default:
                        System.out.println("You entered wrong data");
                        System.exit(0);
                }
            } else {
                System.out.println("This place is not available for order. Do you want to book another place? " +
                        "Type 'y' or 'n',or 'e' for exit:");
                String thirdResponse = scan.next();
                switch(thirdResponse) {
                    case "y":
                        break;
                    case "n":
                        System.out.println("End of the program.");
                        System.exit(0);
                    default:
                        System.out.println("You entered wrong data");
                        System.exit(0);

                }
            }
        }
        return positionArrayList;
    }


    public void createOrderByClient(Scanner scan, Client client) throws IOException {
        try {
            System.out.println("\nChoose a movie from the list:");
            printMovieList();

            System.out.println("\nEnter id:");
            int movieId = scan.nextInt();
            printMovieSessionByMovieId(movieId);

            System.out.println("Enter movie session number: ");
            int movieSessionId = scan.nextInt();

            MovieSession currentMovieSession =
                    orderDataSource.getMovieSessionDataSource().getMovieSessionById(movieSessionId);
            printAvailableSeats(movieSessionId);

            Order newOrder = new Order(orderDataSource.getNewOrderId(),client,currentMovieSession,
                    this.choosePlace(scan, currentMovieSession));

            orderDataSource.addNewOrder(newOrder);
            System.out.println("Your order is: "+ newOrder);
            System.out.println("available seats");
            System.out.println(currentMovieSession.getNumberAvailableSeats());

        } catch (RoomException e) {
            throw new RuntimeException(e);
        } catch (SeatAvailabilityException e) {
            throw new RuntimeException(e);
        }
    }
}
