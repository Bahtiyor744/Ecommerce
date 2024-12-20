package com.example.ecommerce.entity;

import com.example.ecommerce.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id = idGen++;
    private String firstName;
    private String lastName;
    private String password;
    private UserRole userRole = UserRole.USER;
    private String email;
    private static int idGen = 1;

    public User(String firstName, String lastName, String password, UserRole userRole, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userRole = userRole;
        this.email = email;
    }
}
