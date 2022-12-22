package com.softserve.academy.dataSource;

import com.softserve.academy.model.Movie;
import com.softserve.academy.model.cinema.Room;
import com.softserve.academy.model.cinema.util.RoomException;
import com.softserve.academy.model.client.Client;
import com.softserve.academy.model.order.Order;

import java.util.ArrayList;

public class ClientDataSource {
    private ArrayList<Client> clientList = new ArrayList<>();
    private static int newId = 0;

    public ClientDataSource()  {
        initialClientData();
    }

    public void initialClientData() {
        clientList.add(new Client(1,"John", "Doe", "john.doe@gmail.com"));
        clientList.add(new Client(2,"Barack", "Obama", "barack.obama@gmail.com"));
        clientList.add(new Client(3,"Joe", "Biden", "joe.biden@gmail.com"));
    }
    public ArrayList<Client> getClients() {return clientList;}

    public Client getClientById(int id) {
        for(Client client : clientList){
            if(client.getId() == id) {
                return client;
            }
        }
        return null;
    }
    public void addNewClient(Client client) {
        clientList.add(client);
    }
    public int getNewClientId() {
        for(Client client : this.getClients()) {
            if(newId < client.getId()) {
                newId = client.getId();
            }
        }
        return newId + 1;
    }
}
