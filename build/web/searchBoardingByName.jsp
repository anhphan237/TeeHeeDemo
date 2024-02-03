<%-- 
    Document   : searchBoardingByName
    Created on : Jan 25, 2024, 10:44:20 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Boarding</title>
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("Are you sure to delete category with id = " + id)) {
                    window.location = "DeleteBoardingServlet?BoardingId=" + id;
                }
            }
        </script>
    </head>
    <body>
    <center>
        <h1>List of Boarding</h1>
        <h3><a href="addBoarding.jsp">Add new</a></h3>

        <form action="SearchBoardingByName">
            Search Boarding By Name<input type="text" name="txtSearchValue"></input>
            <input type="submit" value="Search"/>
        </form>

        <table border="1">
            <thead>
                <tr>
                    <th>Boarding ID</th>
                    <th>Name</th>
                    <th>Rate</th>
                    <th>Description</th>
                    <th>Image</th>
                    <th>Length</th>
                    <th>Height</th>
                    <th>Width</th>
                    <th>Max Weight</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>

            <c:forEach items="${requestScope.searchResult}" var="c">
                <tr>
                    <td>${c.boardingId}</td>
                    <td>${c.name}</td>
                    <td>${c.rate}</td>
                    <td>${c.description}</td>
                    <td>${c.img}</td>
                    <td>${c.length}</td>
                    <td>${c.height}</td>
                    <td>${c.width}</td>
                    <td>${c.maxWeight}</td>
                    <td>${c.status}</td>
                    <td style="text-align: center;">
                        <a href="UpdateBoardingServlet?BoardingId=${c.boardingId}">update</a>
                        <a href="#" onclick="doDelete('${c.boardingId}')">delete</a>
                    </td>
                </tr>
            </c:forEach>

        </table>

    </center>

</body>
</html>
