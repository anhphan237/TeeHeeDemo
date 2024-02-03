<%-- 
    Document   : updateOrder
    Created on : Feb 1, 2024, 3:37:14 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Order</title>
    </head>
    <body>
        <h1>Update an Order</h1>
        <c:set var="c" value="${requestScope.order}"/>
        <form action="UpdateOrderServlet" method="post">
            Enter Order ID:<input type="text" readonly name="orderId" value="${c.orderId}"/><br/>
            Enter Created Date:<input type="datetime" name="createdDate" value="${c.createdDate}"/><br/>
            Enter Status:<input type="number" name="status" value="${c.status}"/><br/>
            Enter total:<input type="number" step="0.1" name="total" value="${c.total}"/><br/>
            Enter Voucher ID:<input type="text" step="0.1" name="voucherId" value="${c.voucher.voucherId}"/><br/>
            Enter Customer ID:<input type="text" step="0.1" name="customerId" value="${c.customer.customerId}"/><br/>
            <input type="submit" name="UPDATE"/>
            <h1 style="color: red">${c.orderId}</h1>
        </form>
    </body>
</html>
