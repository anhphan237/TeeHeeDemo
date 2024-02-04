<%-- 
    Document   : updateRoom
    Created on : Feb 3, 2024, 9:46:22 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Room</title>
    </head>
    <body>
        <h1>Update a Service</h1>
        <c:set var="c" value="${requestScope.room}"/>
        <form action="UpdateRoomServlet" method="post">
            Enter Room ID:<input type="text" readonly name="id" value="${c.roomId}"/><br/>
            Enter Boarding ID:<input type="text" name="boardingId" value="${c.boarding.boardingId}"/><br/>
            Enter Status:<input type="text" name="status" value="${c.status}"/><br/>
            <input type="submit" name="UPDATE"/>
            <h1 style="color: red">${c.roomId}</h1>
        </form>
    </body>
</html>
