package com.example.ecommerce.FilterServlet;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.enums.UserRole;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class FilterServlet extends HttpFilter {
    List<String> openPages = new ArrayList<>(List.of(
            "/",
            "/login.jsp",
            "/register.jsp",
            "/landing.jsp",
            "/basket.jsp",
            "/registerSuccess.jsp",
            "/PlusMinusServlet",
            "/AddToBasketServlet",
            "/LoginServlet",
            "/RegisterServlet",
            "/Add",
            "/file/1",
            "/file/2",
            "/file/3",
            "/file/4",
            "/file/5",
            "/file/6",
            "/file/7",
            "/file/8",
            "/file/9",
            "/file/10"
    ));
    List<String> adminPages = new ArrayList<>(List.of(
            "/admin.jsp",
            "/AddProductServlet",
            "/AddCategoryServlet",
            "/DeleteProduct",
            "/DeleteCategory"
    ));

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (openPages.contains(req.getRequestURI())) {
            chain.doFilter(req, res);
        } else if (user == null) {
            res.sendRedirect("/login.jsp");
        } else if (user.getUserRole().equals(UserRole.ADMIN)) {
            chain.doFilter(req, res);
        }else if (user.getUserRole().equals(UserRole.USER)){
            if(adminPages.contains(req.getRequestURI())){
                res.sendRedirect("/login.jsp");
            }
            chain.doFilter(req,res);
        } else {
            res.sendRedirect("login.jsp");
        }
    }
}
