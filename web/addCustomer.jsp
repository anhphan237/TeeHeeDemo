<%-- 
    Document   : addCustomer
    Created on : Jan 24, 2024, 11:21:26 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Customer</title>
    </head>
    <body>
        <h1>Add a new Customer</h1>
        <h3 style="color: red">${requestScope.error}</h3>
        <form action="AddCustomerServlet">
            Enter Email<input type="text" name="email"/><br/>
            Enter Password<input type="password" name="password"/><br/>
            Enter First Name<input type="text" name="firstName"/><br/>
            Enter Last Name<input type="text" name="lastName"/><br/>
            Enter Phone<input type="text" name="phone"/><br/>
            <input type="submit" name="SAVE"/>
        </form>
    </body>
</html>
