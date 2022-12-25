package com.softserve.academy;

import com.softserve.academy.model.cinema.util.RoomException;
import com.softserve.academy.model.order.util.SeatAvailabilityException;
import com.softserve.academy.program.Program;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            Program.run();
        } catch (SeatAvailabilityException e) {
            throw new RuntimeException(e);
        } catch (RoomException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
