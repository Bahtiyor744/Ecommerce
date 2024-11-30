<%@ page import="com.example.ecommerce.entity.Basket" %>
<%@ page import="java.util.Objects" %>
<%@ page import="com.example.ecommerce.entity.Product" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.11.2024
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Basket</title>
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
<div class="container mt-5">
    <h2 class="mb-4">Product Table</h2>
    <table class="table table-bordered table-header">
        <thead class="table-dark">
        <tr>
            <th>Photo</th>
            <th>Name</th>
            <th>Price</th>
            <th>Count</th>
            <th>Actions</th>
            <th>Total</th>
        </tr>
        </thead>
        <tbody>
        <%
            Basket basket = (Basket) Objects.requireNonNullElse(session.getAttribute("basket"), new Basket());
            double totalSum = 0;
            for (Map.Entry<Product, Integer> map : basket.getMap().entrySet()) {
                double productTotal = map.getKey().getPrice() * map.getValue();
                totalSum += productTotal;
        %>
        <tr>
            <td class="first_td">
                <img src="${pageContext.request.contextPath}/file/<%=map.getKey().getId()%>" alt="Product Photo"
                     style="width: 100%; height: 100%;">
            </td>
            <td><%= map.getKey().getName() + " "%>
            </td>
            <td><%= map.getKey().getPrice() + " "%>
            </td>
            <td><%= map.getValue() + " "%>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/PlusMinusServlet" method="get"
                      style="display: inline; ">
                    <input type="hidden" name="productId" value="<%= map.getKey().getId() %>">
                    <button class="btn" style="outline: none;
            color: #fff; background:red" name="action" value="minus">-
                    </button>
                    <input type="hidden" name="productId" value="<%= map.getKey().getId() %>">
                    <button class="btn" style="outline: none;
            color: #fff; background : #5252b6" name="action" value="plus">+
                    </button>
                </form>
            </td>
            <td><%= map.getKey().getPrice() * map.getValue()%>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
        <tfoot>
        <tr class="table success">
            <td colspan="5" class="text-end"><strong>Total:</strong></td>
            <td><strong><%= totalSum %>
            </strong></td>
        </tr>
        </tfoot>
    </table>
    <div class="mt-3 text-center">
        <form action="/AddOrderServlet" method="get">
            <button class="btn btn-lg btn-primary" style="outline: none; background: steelblue; color: #fff;">
                Go
            </button>
        </form>
        <form action="landing.jsp" method="get">
            <button class="btn btn-lg btn-primary" style="outline: none; background: steelblue; color: #fff;">
                Back
            </button>
        </form>

    </div>
</div>


</body>
</html>
