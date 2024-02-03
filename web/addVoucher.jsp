<%-- 
    Document   : addVoucher
    Created on : Feb 1, 2024, 12:00:03 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Voucher</title>
    </head>
    <body>
        <h1>Add a new Voucher</h1>
        <h3 style="color: red">${requestScope.error}</h3>
        <form action="AddVoucherServlet">
            Enter Start Date:<input type="text" name="fromDate"/><br/>
            Enter End Date:<input type="text" name="toDate"/><br/>
            Enter Value:<input type="text" name="value"/><br/>
            <input type="submit" name="SAVE"/>
        </form>
    </body>
</html>
