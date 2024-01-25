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
        <title>Customer List</title>
        <script type="text/javascript">
            function doDisable(id){
                if (confirm("Are you sure to disable category with id = "+id)) {
                    window.location="DeleteCustomerServlet?CustomerId="+id;
                }
            }
        </script>
    </head>
    <body>
    <center>
        <h1>List of Customers</h1>
        <h3><a href="addCustomer.jsp">Add new</a></h3>

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
                <th>Action</th>
            </tr>
            <c:forEach items="${requestScope.data}" var="c">
                <c:set var="email" value="${c.email}"/>
                <c:set var="id" value="${c.customerID}"/>
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
                        <a href="UpdateCustomerServlet?CustomerId=${id}">update</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#" onclick="doDisable('${id}')">disable</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </center>
</body>
</html>
