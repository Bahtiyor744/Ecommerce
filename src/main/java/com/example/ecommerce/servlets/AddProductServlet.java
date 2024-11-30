package com.example.ecommerce.servlets;

import com.example.ecommerce.db.DB;
import com.example.ecommerce.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet("/AddProductServlet")
@MultipartConfig
public class AddProductServlet extends HttpServlet {
    String path = "D:/G46/Ecommerce/files/";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("category_id"));
        Part productImg = req.getPart("image");
        Files.write(Path.of(path + name + ".jpg"), productImg.getInputStream().readAllBytes());
        String photoUrl = path + name + ".jpg";
        DB.products.add(new Product(name,categoryId,price,photoUrl));
        resp.sendRedirect("/admin.jsp?name=product");
    }
}
