<%-- 
    Document   : boarding
    Created on : Jan 19, 2024, 10:11:36 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Service</title>

        <link rel="stylesheet" href="css/boarding-rates.css" />
        <link rel="stylesheet" href="css/header.css" />
        <link rel="stylesheet" href="css/footer.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;1,200&family=Quicksand:wght@500&display=swap"
            rel="stylesheet"
            />
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

        <form action="SearchServiceById">
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

            <c:forEach items="${requestScope.data}" var="c">
                <tr>
                    <td>${c.serviceId}</td>
                    <td>${c.name}</td>
                    <td>${c.rate}</td>
                    <td>${c.description}</td>
                    <td>${c.img}</td>
                    <td>${c.status}</td>
                    <td style="text-align: center;">
                        <a href="UpdateServiceServlet?ServiceId=${c.serviceId}">update</a>
                        <a href="#" onclick="doDelete('${c.serviceId}')">delete</a>
                    </td>
                </tr>
            </c:forEach>

        </table>

    </center>

</body>
</html>
