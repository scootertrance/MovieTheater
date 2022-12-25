package com.softserve.academy.program;

import com.softserve.academy.controller.ClientController;
import com.softserve.academy.controller.OrderController;
import com.softserve.academy.dataSource.ClientDataSource;
import com.softserve.academy.dataSource.OrderDataSource;
import com.softserve.academy.model.cinema.util.RoomException;
import com.softserve.academy.model.client.Client;
import com.softserve.academy.model.order.util.SeatAvailabilityException;

import java.io.IOException;

import java.util.Scanner;


public class User {
    public static void runUser() throws SeatAvailabilityException, RoomException, IOException {
        Scanner scan = new Scanner(System.in);
        OrderDataSource orderDataSource = new OrderDataSource();
        ClientDataSource clientDataSource = orderDataSource.getClientDataSource();
        OrderController orderController = new OrderController(orderDataSource);
        ClientController clientController = new ClientController(clientDataSource);
        Client currentClient = clientController.enterClientData(scan);
        orderController.createOrderByClient(scan, currentClient);
    }
}
