<%-- 
    Document   : searchCustomerByName
    Created on : Feb 6, 2024, 10:17:03 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Customer By Name</title>
        <script type="text/javascript">
            function doDisable(id) {
                if (confirm("Are you sure to disable category with id = " + id)) {
                    window.location = "DeleteCustomerServlet?CustomerId=" + id;
                }
            }
        </script>
    </head>
    <body>
    <center>
        <h1>List of Customers</h1>
        <h3><a href="addCustomer.jsp">Add new</a></h3>

        <form action="SearchCustomerById">
            Search Service By Id<input type="text" name="txtSearchValue"></input>
            <input type="submit" value="Search"/>
        </form></br>
        
        <form action="SearchCustomerByName">
            Search Service By Name<input type="text" name="txtSearchValue"></input>
            <input type="submit" value="Search"/>
        </form>


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
                <th>Actions</th>
            </tr>
            <c:forEach items="${requestScope.searchResult}" var="c">
                <c:set var="id" value="${c.customerId}"/>
                <tr>
                    <td>${id}</td>
                    <td>${c.email}</td>
                    <td>${c.password}</td>
                    <td>${c.firstName}</td>
                    <td>${c.lastName}</td>
                    <td>${c.phone}</td>
                    <td>${c.point}</td>
                    <td>${c.member}</td>
                    <td>${c.img}</td>
                    <td>${c.status}</td>
                    <td style="text-align: center;">
                        <a href="UpdateCustomerServlet?CustomerId=${id}">update</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#" onclick="doDisable('${id}')">disable</a>
                        <a href="CustomerProfileServlet?CustomerId=${id}">customer's profile</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </center>
</body>
</html>
