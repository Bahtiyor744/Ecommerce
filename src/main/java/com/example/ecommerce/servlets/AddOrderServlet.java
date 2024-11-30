package com.example.ecommerce.servlets;

import com.example.ecommerce.db.DB;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.enums.Status;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Order order = new Order(user.getId());
        order.setStatus(Status.COMPLATED);
        DB.orders.add(order);
        Basket basket = (Basket) Objects.requireNonNullElse(session.getAttribute("basket"), new Basket());
        for (Map.Entry<Product, Integer> basketMap : basket.getMap().entrySet()) {
            Product product = basketMap.getKey();
            Integer amount = basketMap.getValue();
            DB.orderItem.add(new OrderItem(order.getId(), product.getId(), amount));
        }
        basket.getMap().clear();
        session.setAttribute("basket", basket);
        resp.sendRedirect("/success.jsp");
    }
}
