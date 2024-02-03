<%-- 
    Document   : voucher
    Created on : Jan 31, 2024, 11:54:17 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Voucher List</title>
        <script type="text/javascript">
            function doDisable(id) {
                if (confirm("Are you sure to disable category with id = " + id)) {
                    window.location = "DeleteVoucherServlet?VoucherId=" + id;
                }
            }
        </script>
    </head>
    <body>
        <center>
        <h1>List of Vouchers</h1>
        <h3><a href="addVoucher.jsp">Add new</a></h3>

        <table border="1px" width="40%">
            <tr>
                <th>Voucher ID</th>
                <th>From Date</th>
                <th>To Date</th>
                <th>Value</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${requestScope.data}" var="c">
                <c:set var="id" value="${c.voucherId}"/>
                <tr>
                    <td>${id}</td>
                    <td>${c.fromDate}</td>
                    <td>${c.toDate}</td>
                    <td>${c.value}</td>
                    <td style="text-align: center;">
                        <a href="UpdateVoucherServlet?VoucherId=${id}">update</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#" onclick="doDisable('${id}')">disable</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </center>
    </body>
</html>
