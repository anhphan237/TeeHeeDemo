<%-- 
    Document   : updateBoarding
    Created on : Jan 25, 2024, 10:04:32 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Boarding</title>
    </head>
    <body>
        <h1>Update a Boarding</h1>
        <c:set var="c" value="${requestScope.boarding}"/>
        <form action="UpdateBoardingServlet" method="post">
            Enter ID:<input type="text" readonly name="id" value="${c.boardingId}"/><br/>
            Enter Name:<input type="text" name="name" value="${c.name}"/><br/>
            Enter Rate<input type="text" name="rate" value="${c.rate}"/><br/>
            Enter Description:<input type="text" onkeydown="addHashOnEnter()" name="description" id="description" value="${fn:join(c.description, ', ')}"/><br/>
            <script>
                function addHashOnEnter() {
                    if (event.key === 'Enter') {
                        event.preventDefault();
                        document.getElementById("description").value += "#";
                    }
                }
            </script>
            Enter Image:<input type="text" name="img" value="${c.img}"/><br/>
            Enter Length:<input type="text" name="length" value="${c.length}"/><br/>
            Enter Height:<input type="text" name="height" value="${c.height}"/><br/>
            Enter Width:<input type="text" name="width" value="${c.width}"/><br/>
            Enter Max Weight:<input type="text" name="maxWeight" value="${c.maxWeight}"/><br/>
            Enter Price:<input type="text" name="price" value="${c.price}"/><br/>
            <input type="submit" name="UPDATE"/>
            <h1 style="color: red">${c.name}</h1>
        </form>
    </body>
</html>
