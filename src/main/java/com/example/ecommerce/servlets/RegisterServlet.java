package com.example.ecommerce.servlets;

import com.example.ecommerce.db.DB;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.enums.UserRole;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        if (confirmPassword.equals(password)) {
            DB.users.add(new User(firstName, lastName, password, UserRole.USER, email));
            resp.sendRedirect("/registerSuccess.jsp");
        } else {
            resp.sendRedirect("/login.jsp");
        }
    }
}
