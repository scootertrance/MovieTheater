package com.softserve.academy.dataSource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.softserve.academy.model.cinema.util.RoomException;
import com.softserve.academy.model.order.Order;
import com.softserve.academy.model.order.util.SeatAvailabilityException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class OrderDataSource {
    private static int newId = 0;
    private ArrayList<Order> orderList = new ArrayList<>();
    private ClientDataSource clientDataSource = new ClientDataSource();
    private MovieSessionDataSource movieSessionDataSource = new MovieSessionDataSource();

    public OrderDataSource() throws RoomException, SeatAvailabilityException, IOException {
        initialOrderDataSourceData();
    }

    public void initialOrderDataSourceData() throws SeatAvailabilityException, RoomException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        orderList = mapper.readValue(new File(
                        "src/main/java/com/softserve/academy/resources/orderList.json"),
                new TypeReference<>() {});

        ObjectMapper mapper2 = new ObjectMapper();
        mapper2.registerModule(new JSR310Module());
        movieSessionDataSource = mapper2.readValue(new File(
                        "src/main/java/com/softserve/academy/resources/movieSessionList.json"),
                new TypeReference<>() {});

    }

    public void addNewOrder (Order order) throws IOException {
        orderList.add(order);
        updateOrdersJSON();
        updateMovieSessionJSON();
    }
    public void updateOrdersJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        mapper.writeValue(new File(
                        "src/main/java/com/softserve/academy/resources/orderList.json"),
                orderList);
    }
    public void updateMovieSessionJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        mapper.writeValue(new File(
                        "src/main/java/com/softserve/academy/resources/movieSessionList.json"),
                movieSessionDataSource);
    }


    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public ClientDataSource getClientDataSource() {
        return clientDataSource;
    }

    public MovieSessionDataSource getMovieSessionDataSource() {
        return movieSessionDataSource;
    }

    public int getNewOrderId() {
        for (Order order : this.getOrderList()) {
            if (newId < order.getId()) {
                newId = order.getId();
            }
        }
        return newId + 1;
    }
}
