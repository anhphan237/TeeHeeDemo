<%-- 
    Document   : addTransaction
    Created on : Feb 1, 2024, 4:49:09 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Transaction</title>
    </head>
    <body>
        <h1>Add a new Transaction</h1>
        <h3 style="color: red">${requestScope.error}</h3>
        <form action="AddTransactionServlet">
            Enter Created Time:<input type="text" name="createdTime"/><br/>
            Enter Value:<input type="text" name="value"/><br/>
            Enter Order ID:<input type="text" name="orderId"/><br/>
            <input type="submit" name="SAVE"/>
        </form>
    </body>
</html>
