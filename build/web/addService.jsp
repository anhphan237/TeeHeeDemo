<%-- 
    Document   : addService
    Created on : Jan 22, 2024, 10:39:00 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Service</title>
    </head>
    <body>
        <h1>Add a new Service</h1>
        <h3 style="color: red">${requestScope.error}</h3>
        <form action="AddServiceServlet">
            Enter Name:<input type="text" name="name"/><br/>
            Enter Rate<input type="text" name="rate"/><br/>
            Enter Description<input type="text" name="description"/><br/>
            Enter Image:<input type="text" name="img"/><br/>
            <input type="submit" name="SAVE"/>
        </form>
    </body>
</html>
