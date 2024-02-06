<%-- 
    Document   : searchOrderByStatus
    Created on : Feb 6, 2024, 9:17:10 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1>List of Orders</h1>
        <h3><a href="addOrder.jsp">Add new</a></h3>

        <form action="SearchOrderByStatus">
            Search Order By Status<input type="text" name="txtSearchValue"></input>
            <input type="submit" value="Search"/>
        </form>

        <table border="1px" width="40%">
            <tr>
                <th>Order ID</th>
                <th>Created Time</th>
                <th>Status</th>
                <th>Total</th>
                <th>Voucher ID</th>
                <th>Customer ID</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${requestScope.searchResult}" var="c">
                <c:set var="id" value="${c.orderId}"/>
                <tr>
                    <td>${id}</td>
                    <td>${c.createdDate}</td>
                    <td>
                        <c:choose>
                            <c:when test="${c.status eq 1}">
                                pending
                            </c:when>
                            <c:when test="${c.status eq 2}">
                                confirm
                            </c:when>
                            <c:when test="${c.status eq 3}">
                                complete
                            </c:when>
                            <c:when test="${c.status eq 4}">
                                cancel
                            </c:when>
                        </c:choose>
                    </td> 
                    <td>${c.total}</td>
                    <td>${c.voucher.voucherId}</td>
                    <td>${c.customer.customerId}</td>
                    <td style="text-align: center;">
                        <a href="UpdateOrderServlet?OrderId=${id}">update</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#" onclick="doDisable('${id}')">disable</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </center>
</body>
</html>
