package com.softserve.academy.controller;

import com.softserve.academy.dataSource.*;
import com.softserve.academy.model.order.Order;

import java.util.ArrayList;

public class OrderController {
    private OrderDataSource orderDataSource;

    public OrderController(OrderDataSource orderDataSource) {
        this.orderDataSource = orderDataSource;
    }
    public ArrayList<Order> getOrdersByClientId(int clientId) {
        ArrayList<Order> ordersByClient = new ArrayList<>();
        for (Order order : orderDataSource.getOrderList()) {
            if(order.getClient().getId() == clientId) {
                ordersByClient.add(order);
            }
        }
        return ordersByClient;
    }

    public ArrayList<Order> getOrdersByClientEmail(String email) {
        ArrayList<Order> ordersByClient = new ArrayList<>();
        for(Order order: orderDataSource.getOrderList()){
            if (order.getClient().getEmail().equals(email)) {
                ordersByClient.add(order);
            }
        }
        return ordersByClient;
    }

    public double getAmountOfClientOrders(ArrayList<Order> ordersByClient) {
        double amountOfClientsOrders = 0;
        for(Order order : ordersByClient) {
            amountOfClientsOrders += order.getTotalPrice();
        }
        return amountOfClientsOrders;
    }

    public Order getMostExpensiveOrder() {
        double orderPrice = 0;
        Order mostExpensiveOrder = null;
        for(Order order : orderDataSource.getOrderList()) {
            if(orderPrice < order.getTotalPrice()){
                orderPrice =  order.getTotalPrice();
                mostExpensiveOrder = order;
            }
        }
        return mostExpensiveOrder;
    }

}
