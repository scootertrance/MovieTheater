package com.softserve.academy.dataSource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.model.client.Client;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ClientDataSource {
    private ArrayList<Client> clientList = new ArrayList<>();
    private static int newId = 0;

    public ClientDataSource() throws IOException {
        initialClientData();
    }

    public void initialClientData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        clientList = mapper.readValue(new File(
                        "src/main/java/com/softserve/academy/resources/clientList.json"),
                new TypeReference<>() {});

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
    public void addNewClient(Client client) throws IOException {
        clientList.add(client);
        updateClientsJSON();
    }

    public void updateClientsJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(
                        "src/main/java/com/softserve/academy/resources/clientList.json"),
                clientList);
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
