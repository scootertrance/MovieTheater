package com.softserve.academy;

import com.softserve.academy.controller.OrderController;
import com.softserve.academy.dataSource.OrderDataSource;
import com.softserve.academy.model.cinema.util.RoomException;
import com.softserve.academy.model.order.util.SeatAvailabilityException;
import java.io.IOException;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SeatAvailabilityException, RoomException, IOException {
        Output output = new Output(new OrderController(new OrderDataSource()));
        output.printMostExpensiveOrder();
        output.printOrdersByClientId(1);
        output.printOrdersByClientEmail("joe.biden@gmail.com");
        output.printAllOrders();

    }
}
