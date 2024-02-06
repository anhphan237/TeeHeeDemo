<%-- 
    Document   : servicePriceList
    Created on : Feb 7, 2024, 5:04:36 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Service Price List</title>
    </head>
    <body>
    <center>
        <h1>List of Service Price</h1>
        <h3><a href="addServicePrice.jsp">Add new</a></h3>

        <form action="SearchServicePriceById">
            Search Service By Name<input type="text" name="txtSearchValue"></input>
            <input type="submit" value="Search"/>
        </form>

        <table border="1">
            <thead>
                <tr>
                    <th>Service ID</th>
                    <th>Date</th>
                    <th>Type</th>
                    <th>Weight</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
            </thead>

            <c:forEach items="${requestScope.data}" var="c">
                <c:set var="id" value="${c.serviceId}"/>
                <tr>
                    <td>${id}</td>
                    <td>${c.date}</td>
                    <td>${c.type}</td>
                    <td>${c.weight}</td>
                    <td>${c.price}</td>
                    <td style="text-align: center;">
                        <a href="UpdateServiceServlet?ServiceId=${c.serviceId}">update</a>
                        <a href="#" onclick="doDelete('${c.serviceId}')">delete</a>
                    </td>
                </tr>
            </c:forEach>

        </table>

    </center>

</body>
</html>
