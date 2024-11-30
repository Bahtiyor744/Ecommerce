package com.example.ecommerce.entity;

import com.example.ecommerce.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int id = idGen++;
    private Status status = Status.NEW;
    private LocalDateTime date = LocalDateTime.now();
    private int userId;
    private static int idGen = 1;

    public Order(int userId) {
        this.userId = userId;
    }
}
