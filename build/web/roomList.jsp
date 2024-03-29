<%-- 
    Document   : roomList
    Created on : Feb 3, 2024, 8:25:31 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room Page</title>
        <script type="text/javascript">
            function doDisable(id) {
                if (confirm("Are you sure to disable Room with id = " + id)) {
                    window.location = "DeleteRoomServlet?RoomId=" + id;
                }
            }
        </script>
    </head>
    <body>
    <center>
        <h1>List of Rooms</h1>
        <h3><a href="addRoom.jsp">Add new</a></h3>

        <table border="1px" width="40%">
            <tr>
                <th>Room ID</th>
                <th>Boarding ID</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${requestScope.data}" var="c">
                <c:set var="id" value="${c.roomId}"/>
                <tr>
                    <td>${id}</td>
                    <td>${c.boarding.boardingId}</td>
                    <td>${c.status}</td>
                    <td style="text-align: center;">
                        <a href="UpdateRoomServlet?RoomId=${id}">update</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#" onclick="doDisable('${id}')">disable</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </center>
</body>
</html>
