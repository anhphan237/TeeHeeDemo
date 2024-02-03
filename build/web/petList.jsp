<%-- 
    Document   : petList
    Created on : Jan 27, 2024, 3:20:16 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pets</title>
        <script type="text/javascript">
            function doDisable(id) {
                if (confirm("Are you sure to disable category with id = " + id)) {
                    window.location = "DeletePetServlet?CustomerId=" + id;
                }
            }
        </script>
    </head>
    <body>
    <center>
        <h1>List of Pets</h1>
        <h3><a href="addPet.jsp">Add new</a></h3>

        <table border="1px" width="40%">
            <tr>
                <th>Pet ID</th>
                <th>Name</th>
                <th>Date of Birth</th>
                <th>Customer ID</th>
                <th>Type</th>
                <th>Weight</th>
                <th>Gender</th>
                <th>Vaccinated</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${requestScope.data}" var="c">
                <c:set var="id" value="${c.petId}"/>
                <tr>
                    <td>${id}</td>
                    <td>${c.name}</td>
                    <td>${c.dob}</td>
                    <td>${c.customerId}</td>
                    <td>${c.type}</td>
                    <td>${c.weight}</td>
                    <td>${c.gender}</td>
                    <td>${c.vaccinated}</td>
                    <td>${c.status}</td>
                    <td style="text-align: center;">
                        <a href="UpdatePetServlet?CustomerId=${id}">update</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#" onclick="doDisable('${id}')">disable</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </center>
</body>
</html>
