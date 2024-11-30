<%@ page import="com.example.ecommerce.entity.User" %>
<%@ page import="com.example.ecommerce.db.DB" %>
<%@ page import="com.example.ecommerce.entity.Order" %>
<%@ page import="com.example.ecommerce.entity.OrderItem" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 30.11.2024
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Orders Table</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        table th, table td {
            vertical-align: middle;
            text-align: center;
        }
    </style>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    int userId = user.getId();
%>
<div class="container mt-5">
    <h2 class="mb-4">
        My Orders table
    </h2>
    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>STATUS</th>
            <th>DATE</th>
            <th>COUNT</th>
            <th>ACTIONS</th>
            <th>USER ID</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Order order : DB.orders) {
                int count = 0;
                if (order.getUserId() == userId) {
                    for (OrderItem orderItem : DB.orderItem) {
                        if (order.getId() == orderItem.getOrderID()){
                            count += 1;
                        }
                    }
        %>
        <tr>
            <td>
                <%= " " + order.getId() + " "%>
            </td>
            <td>
                <%= " " + order.getStatus() + " "%>
            </td>
            <td>
                <%= " " + order.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + " "%>
            </td>
            <td>
                <%= " " + count + " "%>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/viewOrderItem.jsp" method="get"
                      style="display: inline;">
                    <button class="btn" style="outline: none; color: #fff; background: red"
                            name="orderId" value="<%= order.getId() %>">
                        View
                    </button>
                </form>
            </td>
            <td>
                <%= " " + userId + " "%>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
    <div class="mt-3 text-center">
        <form action="landing.jsp" method="get">
            <button class="btn btn-lg btn primary" style="outline: none; background: steelblue; color: #fff">
                Back
            </button>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
