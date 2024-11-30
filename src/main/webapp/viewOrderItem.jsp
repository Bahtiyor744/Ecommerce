<%@ page import="com.example.ecommerce.db.DB" %>
<%@ page import="com.example.ecommerce.entity.OrderItem" %>
<%@ page import="com.example.ecommerce.entity.Product" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 30.11.2024
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products Table</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        table th:not(:first-child), table td:not(:first-child) {
            vertical-align: middle;
            text-align: center;
        }

        table .first_td {
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body>

<%
    int orderId = Integer.parseInt(request.getParameter("orderId"));
%>
<div class="container mt-5">
    <h2 class="mb-4">
        My order Products
    </h2>
    <table class="table table-bordered tablet-hover">
        <thead class="table-dark">
        <tr>
            <th>Photo</th>
            <th>Name</th>
            <th>Price</th>
            <th>Count</th>
            <th>Total</th>
        </tr>
        </thead>
        <tbody>
        <%
            double totalSum = 0;
            for (OrderItem orderItem : DB.orderItem) {
                if (orderItem.getOrderID() == orderId) {
                    for (Product product : DB.products) {
                        if (product.getId() == orderItem.getProductID()) {
                            double productTotal = product.getPrice() * orderItem.getAmount();
                            totalSum += productTotal;
        %>
        <tr>
            <td class="first_td">
                <img src="/file/<%=product.getId()%>" alt="Product Photo" style="width: 100px; height: 100px;">
            </td>
            <td>
                <%= product.getName() %>
            </td>
            <td>
                <%= product.getPrice() %>
            </td>
            <td>
                <%= orderItem.getAmount() %>
            </td>
            <td>
                <%= product.getPrice() * orderItem.getAmount() %>
            </td>
        </tr>
        <%
                        }
                    }
                }
            }
        %>
        </tbody>
        <tfoot>
        <tr class="table-success">
            <td colspan="4" class="text-end">
                <strong>
                    Total:
                </strong>
            </td>
            <td>
                <strong>
                    <%= totalSum %>
                </strong>
            </td>
        </tr>
        </tfoot>
    </table>
    <div class="mt-3 text-center">
        <form action="${pageContext.request.contextPath}/myOrders.jsp" method="get">
            <button class="btn btn-lg btn-primary" style="outline: none; background: steelblue; color: #ffffff;">
                Back
            </button>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
