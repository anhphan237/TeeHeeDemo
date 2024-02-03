<%-- 
    Document   : updateService
    Created on : Jan 22, 2024, 9:37:22 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Service</title>
    </head>
    <body>
        <h1>Update a Service</h1>
        <c:set var="c" value="${requestScope.service}"/>
        <form action="UpdateServiceServlet" method="post">
            Enter ID:<input type="text" readonly name="id" value="${c.serviceId}"/><br/>
            Enter Name:<input type="text" name="name" value="${c.name}"/><br/>
            Enter Rate<input type="text" name="rate" value="${c.rate}"/><br/>
            <!--Enter Description:<input type="text" name="description" value="${c.description}"/><br/>-->
            Enter description:<textarea name="description" id="description" onkeydown="addHashOnEnter()"></textarea><br/>    
            <script>
                function addHashOnEnter() {
                    if (event.key === 'Enter') {
                        event.preventDefault();
                        document.getElementById("description").value += "#";
                    }
                }
            </script>
            Enter img:<input type="text" name="img" value="${c.img}"/><br/>
            <input type="submit" name="UPDATE"/>
            <h1 style="color: red">${c.name}</h1>
        </form>
    </body>
</html>
