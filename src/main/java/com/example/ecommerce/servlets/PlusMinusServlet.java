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

@WebServlet("/PlusMinusServlet")
public class PlusMinusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Basket basket = (Basket) Objects.requireNonNullElse(session.getAttribute("basket"), new Basket());
        if (productId != null && action != null) {
            try {
                int id = Integer.parseInt(productId);
                Product product = DB.products.stream()
                        .filter(item -> item.getId() == id)
                        .findFirst()
                        .orElse(null);
                if (product != null && basket.getMap().containsKey(product)) {
                    int currentQuantity = basket.getMap().get(product);
                    if (action.equals("plus")) {
                        basket.getMap().put(product, currentQuantity + 1);
                    } else if (action.equals("minus") && currentQuantity > 0) {
                        basket.getMap().put(product, currentQuantity - 1);
                        if (basket.getMap().get(product) == 0) {
                            basket.getMap().remove(product);
                        }
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        session.setAttribute("basket",basket);
        resp.sendRedirect("basket.jsp");
    }
}
