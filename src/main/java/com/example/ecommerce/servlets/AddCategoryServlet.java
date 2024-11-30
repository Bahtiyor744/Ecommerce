package com.example.ecommerce.servlets;

import com.example.ecommerce.db.DB;
import com.example.ecommerce.entity.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddCategoryServlet")
public class AddCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("name");
        DB.categorys.add(new Category(categoryName));
        resp.sendRedirect("/admin.jsp?name=category");
    }
}
