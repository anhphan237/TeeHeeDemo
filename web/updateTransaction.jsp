<%-- 
    Document   : updateTransaction
    Created on : Feb 1, 2024, 5:11:13 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Transaction</title>
    </head>
    <body>
        <h1>Update a Transaction</h1>
        <c:set var="c" value="${requestScope.transaction}"/>
        <form action="UpdateTransactionServlet" method="post">
            Enter Transaction ID:<input type="text" readonly name="transactionId" value="${c.transactionId}"/><br/>
            Enter Created Time:<input type="text" name="createdTime" value="${c.createdTime}"/><br/>
            Enter Value:<input type="number" step="0.1" name="value" value="${c.value}"/><br/>
            Enter Order ID:<input type="text" name="orderId" value="${c.order.orderId}"/><br/>
            <input type="submit" name="UPDATE"/>
            <h1 style="color: red">${c.transactionId}</h1>
        </form>
    </body>
</html>
