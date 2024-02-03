<%-- 
    Document   : addRoom
    Created on : Feb 3, 2024, 8:45:32 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Room</title>
    </head>
    <body>
        <h1>Add a new Room</h1>
        <h3 style="color: red">${requestScope.error}</h3>
        <form action="AddRoomServlet">
            Enter Boarding ID:<input type="text" name="roomId"/><br/>
            <input type="submit" name="SAVE"/>
        </form>
    </body>
</html>
