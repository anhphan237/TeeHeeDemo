<%-- 
    Document   : updatePet
    Created on : Jan 27, 2024, 4:21:02 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Pet</title>
    </head>
    <body>
        <h1>Update a Pet</h1>
        <c:set var="c" value="${requestScope.pet}"/>
        <form action="UpdatePetServlet" method="post">
            Enter Pet ID:<input type="text" readonly name="petId" value="${c.petId}"/><br/>
            Enter Name:<input type="text" name="name" value="${c.name}"/><br/>
            Enter Date of Birth(yyyy-mm-dd):<input type="text" name="dob" value="${c.dob}"/><br/>
            Enter Customer ID:<input type="text" name="customerId" value="${c.customerId}"/><br/>
            Enter Type:<input type="text" name="type" value="${c.type}"/><br/>
            Enter Weight:<input type="text" name="weight" value="${c.weight}"/><br/>
            Enter Gender:<input type="text" name="gender" value="${c.gender}"/><br/>
            Enter Vaccinated:<input type="text" name="vaccinated" value="${c.vaccinated}"/><br/>
            Enter Status:<input type="text" name="status" value="${c.status}"/><br/>
            <input type="submit" name="UPDATE"/>
            <h1 style="color: red">${c.name}</h1>
        </form>
    </body>
</html>
