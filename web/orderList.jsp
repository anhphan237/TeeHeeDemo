<%-- 
    Document   : OrderList
    Created on : Jan 28, 2024, 11:38:16 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order List</title>
    </head>
    <body>
    <center>
        <h1>List of Orders</h1>
        <h3><a href="addOrder.jsp">Add new</a></h3>

        <table border="1px" width="40%">
            <tr>
                <th>Order ID</th>
                <th>Created Time</th>
                <th>Status</th>
                <th>Total</th>
                <th>Voucher ID</th>
                <th>Customer ID</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${requestScope.data}" var="c">
                <c:set var="id" value="${c.orderId}"/>
                <tr>
                    <td>${id}</td>
                    <td>${c.createdDate}</td>
                    <td>${c.status}</td>
                    <td>${c.total}</td>
                    <td>${c.voucher.voucherId}</td>
                    <td>${c.customer.customerId}</td>
                    <td style="text-align: center;">
                        <a href="UpdateOrderServlet?OrderId=${id}">update</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#" onclick="doDisable('${id}')">disable</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </center>
</body>
</html>
