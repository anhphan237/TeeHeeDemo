<%-- 
    Document   : updateVoucher
    Created on : Feb 1, 2024, 12:43:30 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Voucher</title>
    </head>
    <body>
        <h1>Update a Service</h1>
        <c:set var="c" value="${requestScope.voucher}"/>
        <form action="UpdateVoucherServlet" method="post">
            Enter Voucher ID:<input type="text" readonly name="voucherId" value="${c.voucherId}"/><br/>
            Enter Start Date:<input type="datetime" name="fromDate" value="${c.fromDate}"/><br/>
            Enter End Date:<input type="datetime" name="toDate" value="${c.toDate}"/><br/>
            Enter Value:<input type="number" step="0.1" name="value" value="${c.value}"/><br/>
            <input type="submit" name="UPDATE"/>
            <h1 style="color: red">${c.voucherId}</h1>
        </form>
    </body>
</html>
