<%-- 
    Document   : transaction
    Created on : Feb 1, 2024, 4:36:51 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction Page</title>
    </head>
    <body>
    <center>
        <h1>List of Transaction</h1>
        <h3><a href="addTransaction.jsp">Add new</a></h3>

        <form action="SearchTransactionById">
            Search Service By Name<input type="text" name="txtSearchValue"></input>
            <input type="submit" value="Search"/>
        </form>

        <table border="1">
            <thead>
                <tr>
                    <th>Transaction ID</th>
                    <th>Created Time</th>
                    <th>Value</th>
                    <th>Order ID</th>
                    <th>Action</th>
                </tr>
            </thead>

            <c:forEach items="${requestScope.data}" var="c">
                <tr>
                    <td>${c.transactionId}</td>
                    <td>${c.createdTime}</td>
                    <td>${c.value}</td>
                    <td>${c.order.orderId}</td>
                    <td style="text-align: center;">
                        <a href="UpdateTransactionServlet?TransactionId=${c.transactionId}">update</a>
                        <a href="#" onclick="doDelete('${c.transactionId}')">delete</a>
                    </td>
                </tr>
            </c:forEach>

        </table>

    </center>
</body>
</html>
