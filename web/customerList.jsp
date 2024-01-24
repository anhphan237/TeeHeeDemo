<%-- 
    Document   : customerList
    Created on : Jan 21, 2024, 9:28:56 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1>List of Categories</h1>
        <h3><a href="add.jsp">Add new</a></h3>

        <!--        <h3>
                    <a href="add.jsp">Add new</a>
                    <label for="sort">Sort by:</label>
                    <select id="sort" name="sort">
                        <option value="id">ID</option>
                        <option value="name">Name</option>
                        <option value="describe">Describe</option>
                    </select>
                    <input type="text" id="searchInput" onkeyup="search()" placeholder="Search by Name">
                </h3>-->

        <table border="1px" width="40%">
            <tr>
                <th>Customer ID</th>
                <th>Email</th>
                <th>Password</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Phone</th>
                <th>Point</th>
                <th>Member</th>
                <th>Image</th>
                <th>Status</th>
                <th>ACTION</th>
            </tr>
            <c:forEach items="${requestScope.data}" var="c">
                <c:set var="id" value="${c.email}"/>
                <tr>
                    <td>${c.customerID}</td>
                    <td>${email}</td>
                    <td>${c.password}</td>
                    <td>${c.firstName}</td>
                    <td>${c.lastName}</td>
                    <td>${c.phone}</td>
                    <td>${c.point}</td>
                    <td>${c.member}</td>
                    <td>${c.img}</td>
                    <td>${c.status}</td>
                    <td style="text-align: center;">
                        <a href="update?id=${id}">update</a>
                        <a href="#" onclick="doDisable('${id}')">disable</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </center>
</body>
</html>
