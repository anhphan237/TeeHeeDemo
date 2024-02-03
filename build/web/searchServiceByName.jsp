<%-- 
    Document   : searchServiceByName
    Created on : Jan 24, 2024, 10:16:17 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <script type="text/javascript">
            function doDelete(id){
                if (confirm("Are you sure to delete category with id = "+id)) {
                    window.location="DeleteServiceServlet?ServiceId="+id;
                }
            }
        </script>
    </head>
    <body>
    <center>
        <h1>List of Service</h1>
        <h3><a href="addService.jsp">Add new</a></h3>

        <form action="SearchServiceByName">
            Search Service By Name<input type="text" name="txtSearchValue"></input>
            <input type="submit" value="Search"/>
        </form>

        <table border="1">
            <thead>
                <tr>
                    <th>Service ID</th>
                    <th>Name</th>
                    <th>Rate</th>
                    <th>Description</th>
                    <th>Image</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>

            <c:forEach items="${requestScope.searchResult}" var="c">
                <tr>
                    <td>${c.serviceId}</td>
                    <td>${c.name}</td>
                    <td>${c.rate}</td>
                    <td>${c.description}</td>
                    <td>${c.img}</td>
                    <td>${c.status}</td>
                    <td style="text-align: center;">
                        <a href="UpdateServiceServlet?ServiceId=${c.serviceId}">update</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#" onclick="doDelete('${c.serviceId}')">delete</a>
                    </td>
                </tr>
            </c:forEach>

        </table>

    </center>
</body>
</html>
