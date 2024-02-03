<%-- 
    Document   : addPet
    Created on : Jan 27, 2024, 3:32:34 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Pet</title>
    </head>
    <body>
        <h1>Add a new Pet</h1>
        <h3 style="color: red">${requestScope.error}</h3>
        <form action="AddPetServlet">
            Enter Name:<input type="text" name="name"/><br/>
            Enter Date of birth(yyyy-mm-dd):<input type="text" name="dob"/><br/>
            Enter Customer ID:<input type="text" name="customerId"/><br/>
            Enter Type:<input type="text" name="type"/><br/>
            Enter Weight:<input type="text" name="weight"/><br/>
            Enter Gender:<input type="text" name="gender"/><br/>
            Enter Vaccinated:<input type="text" name="vaccinated"/><br/>
            <input type="submit" name="SAVE"/>
        </form>
    </body>
</html>
