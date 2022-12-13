package com.softserve.academy.dataSource;



import com.softserve.academy.model.Position;
import com.softserve.academy.model.cinema.util.RoomException;
import com.softserve.academy.model.order.Order;
import com.softserve.academy.model.order.util.SeatAvailabilityException;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDataSource {
    private ArrayList<Order> orderList = new ArrayList<>();
    private ClientDataSource clientDataSource = new ClientDataSource();
    private MovieSessionDataSource movieSessionDataSource = new MovieSessionDataSource();

    public OrderDataSource() throws RoomException, SeatAvailabilityException {
        initialOrderDataSourceData();
    }

    public void initialOrderDataSourceData() throws SeatAvailabilityException, RoomException {
    orderList.add(new Order(1, clientDataSource.getClientById(1), movieSessionDataSource.getMovieSessionById(1),
            new Position(0,0),new Position(0,1)));
        orderList.add(new Order(2, clientDataSource.getClientById(2), movieSessionDataSource.getMovieSessionById(2),
                new Position(1,0),new Position(1,1),new Position(1,2)));
        orderList.add(new Order(1, clientDataSource.getClientById(1), movieSessionDataSource.getMovieSessionById(1),
                new Position(2,0),new Position(2,1), new Position(2,2),
                new Position(2,3)));
    }
}
