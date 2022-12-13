package com.softserve.academy.controller;

import com.softserve.academy.dataSource.*;
import com.softserve.academy.model.order.Order;

public class OrderController {
    private MovieDataSource movieDataSource;
    private RoomDataSource roomDataSource;
    private MovieSessionDataSource movieSessionDataSource;
    private ClientDataSource clientDataSource;
    private OrderDataSource orderDataSource;

    public OrderController(MovieDataSource movieDataSource, RoomDataSource roomDataSource,
                           MovieSessionDataSource movieSessionDataSource, ClientDataSource clientDataSource,
                           OrderDataSource orderDataSource) {
        this.movieDataSource = movieDataSource;
        this.roomDataSource = roomDataSource;
        this.movieSessionDataSource = movieSessionDataSource;
        this.clientDataSource = clientDataSource;
        this.orderDataSource = orderDataSource;
    }

}
