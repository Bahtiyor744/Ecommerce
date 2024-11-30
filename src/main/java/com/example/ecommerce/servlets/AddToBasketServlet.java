package com.example.ecommerce.servlets;

import com.example.ecommerce.db.DB;
import com.example.ecommerce.entity.Basket;
import com.example.ecommerce.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/AddToBasketServlet")
public class AddToBasketServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productId = req.getParameter("productId");
        String categoryId = req.getParameter("categoryId");
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        if (productId != null) {
            int id = Integer.parseInt(productId);
            Product productAdd = DB.products.stream().filter(product -> product.getId() == id).findFirst().orElseThrow();
            Basket basket = (Basket) Objects.requireNonNullElse(session.getAttribute("basket"), new Basket());
            if (action.equals("add")) {
                basket.getMap().put(productAdd, 1);
                session.setAttribute("basket", basket);
            } else if (action.equals("remove")) {
                basket.getMap().remove(productAdd, 1);
                session.setAttribute("basket",basket);
            }
        }
        resp.sendRedirect("landing.jsp?categoryId="+categoryId);

    }
}
