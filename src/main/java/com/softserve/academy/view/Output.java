package com.softserve.academy.view;

import com.softserve.academy.controller.OrderController;
import com.softserve.academy.model.Movie;
import com.softserve.academy.model.Position;
import com.softserve.academy.model.movieSession.MovieSession;
import com.softserve.academy.model.movieSession.MovieSessionSeat;
import com.softserve.academy.model.order.Order;

import java.util.ArrayList;

public class Output {
    private OrderController orderController;

    public Output(OrderController orderController) {
        this.orderController = orderController;
    }

    public void printMostExpensiveOrder() {
        System.out.printf("MostExpensiveOrder:%s\n\n",orderController.getMostExpensiveOrder());
    }

    public void printOrdersByClientId(int id){
        System.out.printf("Orders for client with id %d:\n%s\n\n",id,orderController.getOrdersByClientId(id));
    }

    public void printOrdersByClientEmail(String email){
        System.out.printf("Orders for client with email %s:\n%s\n\n",email,orderController.
                getOrdersByClientEmail(email));
    }

    public void printMovieList() {
        for(Movie movie: orderController.getOrderDataSource().getMovieSessionDataSource().getMovieDataSource().
                getMovies()) {
            System.out.println(movie);
        }
    }
    public void printMovieSessionByMovieId(int movieId) {
        for(MovieSession movieSession : orderController.getOrderDataSource().getMovieSessionDataSource().
                getMovieSessionByMovieId(movieId)){
            System.out.println(movieSession);
        }
    }
    public void printAllOrders(){
        System.out.println("All orders from order datasource: ");
        for(Order order : orderController.getOrderDataSource().getOrderList()){
            System.out.println(order);
        }
    }
}
