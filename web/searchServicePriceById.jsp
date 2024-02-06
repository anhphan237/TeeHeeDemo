<%-- 
    Document   : searchServicePriceById
    Created on : Feb 7, 2024, 4:03:22 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Service Price</title>
    </head>
    <body>
    <center>
        <table border="1px" width="40%">
            <tr>
                <th>Service ID</th>
                <th>Date</th>
                <th>Type</th>
                <th>Weight</th>
                <th>Price</th>
            </tr>
            <c:set var="c" value="${requestScope.result}"/>
            <c:set var="id" value="${c.serviceId}"/>
            <tr>
                <td>${id}</td>
                <td>${c.date}</td>
                <td>${c.type}</td>
                <td>${c.weight}</td>
                <td>${c.price}</td>
            </tr>
        </table>
    </center>
</body>
</html>
