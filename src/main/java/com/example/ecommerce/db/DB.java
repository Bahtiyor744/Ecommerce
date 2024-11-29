package com.example.ecommerce.db;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;

import java.util.ArrayList;
import java.util.List;

public interface DB {
    List<Product> products = new ArrayList<>(List.of(
        new Product("Apple", 1,1000,"D:/G46/Ecommerce/files/apple.jpg" )
    ));
    List<Category> categorys = new ArrayList<>(List.of(
       new Category("Fruits"),
       new Category("Drinks")
    ));
}
