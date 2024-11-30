package com.example.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private int id = idGen++;
    private int orderID;
    private int productID;
    private int amount;
    private static int idGen = 1;

    public OrderItem(int orderID, int productID, int amount) {
        this.orderID = orderID;
        this.productID = productID;
        this.amount = amount;
    }
}
