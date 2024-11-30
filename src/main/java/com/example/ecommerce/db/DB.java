package com.example.ecommerce.db;

import com.example.ecommerce.entity.*;
import com.example.ecommerce.enums.UserRole;

import java.util.ArrayList;
import java.util.List;

public interface DB {
    List<User> users = new ArrayList<>(List.of(
            new User("admin", "admin", "123", UserRole.ADMIN, "admin"),
            new User("user", "user", "123", UserRole.USER, "user")
    ));
    List<Product> products = new ArrayList<>(List.of(
            new Product("Apple", 1, 1000, "D:/G46/Category/files/apple.jpg"),
            new Product("Capucino", 2, 800, "D:/G46/Category/files/cofe.jpg"),
            new Product("Juice", 2, 2500, "D:/G46/Category/files/juice.jpg"),
            new Product("RedBull", 2, 3000, "D:/G46/Category/files/redbull.jpg"),
            new Product("Apelsin", 1, 1500, "D:/G46/Category/files/Apelsin.jpg"),
            new Product("Banan", 1, 1700, "D:/G46/Category/files/Banan1.jpg")
    ));
    List<Category> categorys = new ArrayList<>(List.of(
            new Category("Fruits"),
            new Category("Drinks")
    ));
    List<Order> orders = new ArrayList<>();
    List<OrderItem> orderItem = new ArrayList<>();
}
