package com.softserve.academy;

import com.softserve.academy.controller.ClientController;
import com.softserve.academy.controller.OrderController;
import com.softserve.academy.dataSource.ClientDataSource;
import com.softserve.academy.dataSource.OrderDataSource;
import com.softserve.academy.model.cinema.util.RoomException;
import com.softserve.academy.model.client.Client;
import com.softserve.academy.model.movieSession.MovieSession;
import com.softserve.academy.model.order.Order;
import com.softserve.academy.model.order.util.SeatAvailabilityException;

import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class ClientApp {
    public static void main(String[] args) throws SeatAvailabilityException, RoomException {
        Scanner scan = new Scanner(System.in);
        OrderDataSource orderDataSource = new OrderDataSource();
        ClientDataSource clientDataSource = orderDataSource.getClientDataSource();
        OrderController orderController = new OrderController(orderDataSource);
        ClientController clientController = new ClientController(clientDataSource);
        Output output = new Output(orderController);

        Client currentClient = clientController.enterClientData(scan);
        orderController.createOrderByClient(scan, currentClient);







    }
}
