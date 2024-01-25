<%-- 
    Document   : updateCustomer
    Created on : Jan 24, 2024, 3:31:38 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Customer</title>
    </head>
    <body>
        <h1>Update a Customer</h1>
        <c:set var="c" value="${requestScope.customer}"/>
        <form action="UpdateCustomerServlet" method="post">
            Enter ID:<input type="text" readonly name="customerId" value="${c.customerID}"/><br/>
            Enter Email:<input type="text" name="email" value="${c.email}"/><br/>
            Enter Password:<input type="text" name="password" value="${c.password}"/><br/>
            Enter First Name:<input type="text" name="firstName" value="${c.firstName}"/><br/>
            Enter Last Name:<input type="text" name="lastName" value="${c.lastName}"/><br/>
            Enter Phone:<input type="text" name="phone" value="${c.phone}"/><br/>
            Enter Point:<input type="text" name="point" value="${c.point}"/><br/>
            Enter Member<input type="text" name="member" value="${c.member}"/><br/>
            Enter Image:<input type="text" name="img" value="${c.img}"/><br/>
            Enter Status:<input type="text" name="status" value="${c.status}"/><br/>
            <input type="submit" name="UPDATE"/>
            <h1 style="color: red">${c.email}</h1>
        </form>
    </body>
</html>
