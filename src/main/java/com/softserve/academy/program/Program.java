package com.softserve.academy.program;

import com.softserve.academy.model.cinema.util.RoomException;
import com.softserve.academy.model.order.util.SeatAvailabilityException;
import com.softserve.academy.program.Admin;
import com.softserve.academy.program.User;

import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static void run() throws SeatAvailabilityException, RoomException, IOException {
        System.out.println("Welcome to the movie ticket system. To login as admin enter 'a', or as user 'u': ");
        Scanner sc = new Scanner(System.in);
        String response = sc.next();
        switch (response){
            case "a":
                Admin.runAdmin();
                break;
            case "u" :
                User.runUser();
                break;
            default:
                System.out.println("Incorrect input");
        }
    }
}
