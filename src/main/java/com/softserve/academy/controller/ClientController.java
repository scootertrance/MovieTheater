package com.softserve.academy.controller;

import com.softserve.academy.dataSource.ClientDataSource;
import com.softserve.academy.model.client.Client;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientController {
    private ClientDataSource clientDataSource = new ClientDataSource();

    public ClientController(ClientDataSource clientDataSource) throws IOException {
        this.clientDataSource = clientDataSource;
    }

    public Client createNewClient(String name, String surname, String email) throws IOException {
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
                    String email = "";
                    System.out.println("Enter an email or enter 'exit' for exit");

                    while (true) {

                        if (scan.hasNext("exit")) {
                            System.out.println("End of the program.");
                            System.exit(0);
                        }

                        email = scan.next();

                        if (email.matches("\\w+(\\.\\w+)*@(\\w+\\.)+\\w+")) {
                            break;
                        }

                        else {
                            System.out.println("You entered wrong data. Please enter correct data or enter 'exit' for exit:");
                        }
                    }

                    Client currentClient = createNewClient(name, surname, email);
                    return currentClient;
                default:
                    System.out.println("You entered wrong data.");
                    System.exit(0);
            }
        } catch (InputMismatchException | IOException exc) {
            System.out.println("You entered wrong data.");
        }
        return null;
    }
}
