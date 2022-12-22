package com.softserve.academy.controller;

import com.softserve.academy.dataSource.ClientDataSource;
import com.softserve.academy.model.client.Client;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientController {
    private ClientDataSource clientDataSource = new ClientDataSource();

    public ClientController(ClientDataSource clientDataSource) {
        this.clientDataSource = clientDataSource;
    }

    public Client createNewClient(String name, String surname, String email) {
        Client client = new Client(clientDataSource.getNewClientId(), name, surname, email);
        clientDataSource.addNewClient(client);
        return client;
    }

    public Client enterClientData(Scanner scan) {
        try {
            System.out.println("\nEnter client id (enter 'e') or create a new client ('n'): ");
            String response = scan.next();

            switch (response) {
                case "e":
                    System.out.println("Enter client id:");
                    int clientId = scan.nextInt();
                    return clientDataSource.getClientById(clientId);
                case "n":
                    System.out.println("Enter a name:");
                    String name = scan.next();
                    System.out.println("Enter a surname:");
                    String surname = scan.next();
                    System.out.println("Enter an email :");
                    String email = scan.next();
                    Client currentClient = createNewClient(name, surname, email);
                    return currentClient;
                default:
                    System.out.println("You entered wrong data.");
                    System.exit(0);
            }
        } catch (InputMismatchException exc) {
            System.out.println("You entered wrong data.");
        }
        return null;
    }
}