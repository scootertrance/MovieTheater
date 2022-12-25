package com.softserve.academy.program;

import com.softserve.academy.controller.OrderController;
import com.softserve.academy.dataSource.OrderDataSource;
import com.softserve.academy.model.cinema.util.RoomException;
import com.softserve.academy.model.order.util.SeatAvailabilityException;
import com.softserve.academy.view.Output;
import java.io.IOException;

public class Admin
{
    public static void runAdmin() throws SeatAvailabilityException, RoomException, IOException {
        Output output = new Output(new OrderController(new OrderDataSource()));
        output.printMostExpensiveOrder();
        output.printOrdersByClientId(1);
        output.printOrdersByClientEmail("joe.biden@gmail.com");
        output.printAllOrders();
    }
}
