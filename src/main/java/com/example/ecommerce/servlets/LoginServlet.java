package com.example.ecommerce.servlets;

import com.example.ecommerce.db.DB;
import com.example.ecommerce.entity.Basket;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.enums.UserRole;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstName = req.getParameter("name");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        Basket basket = (Basket) session.getAttribute("basket");
        Optional<User> user1 = DB.users.stream()
                .filter(user -> user.getFirstName().equals(firstName) && user.getPassword().equals(password))
                .findFirst();
        if (user1.isPresent()) {
            session.setAttribute("user", user1.get());
            if (user1.get().getUserRole().equals(UserRole.USER)) {
                if (basket != null) {
                    resp.sendRedirect("/basket.jsp");
                } else {
                    resp.sendRedirect("/landing.jsp");
                }
            } else if (user1.get().getUserRole().equals(UserRole.ADMIN)) {
                resp.sendRedirect("/admin.jsp");
            }
        } else {
            resp.sendRedirect("/login.jsp");
        }
    }
}
