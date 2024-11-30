package com.example.ecommerce.db;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.enums.UserRole;

import java.util.ArrayList;
import java.util.List;

public interface DB {
    List<User> users = new ArrayList<>(List.of(
            new User("admin","admin","123", UserRole.ADMIN,"admin"),
            new User("user","user","123",UserRole.USER,"user")
    ));
    List<Product> products = new ArrayList<>(List.of(
        new Product("Apple", 1,1000,"D:/G46/Ecommerce/files/apple.jpg" )
    ));
    List<Category> categorys = new ArrayList<>(List.of(
       new Category("Fruits"),
       new Category("Drinks")
    ));
}
