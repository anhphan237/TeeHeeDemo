<%-- 
    Document   : addOrder
    Created on : Feb 1, 2024, 2:29:47 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Order</title>
    </head>
    <body>
        <h1>Add a new Order</h1>
        <h3 style="color: red">${requestScope.error}</h3>
        <form action="AddOrderServlet">
            Enter Created Date:<input type="text" name="createdDate"/><br/>
            Enter Status:<input type="number" name="status"/><br/>
            Enter Total:<input type="text" name="total"/><br/>
            Enter Voucher ID:<input type="text" name="voucherId"/><br/>
            Enter Customer ID:<input type="text" name="customerId"/><br/>
            <input type="submit" name="SAVE"/>
        </form>
    </body>
</html>
