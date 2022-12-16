package com.softserve.academy;

import com.softserve.academy.controller.OrderController;

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
        System.out.printf("Orders for client with email %s:\n%s\n\n",email,orderController.getOrdersByClientEmail(email));
    }
}
