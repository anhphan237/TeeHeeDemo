<%-- 
    Document   : OrderList
    Created on : Jan 28, 2024, 11:38:16 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order List</title>
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
                <th>Room ID</th>
                <th>Pet ID</th>
                <th>Service ID</th>
                <th>Check In Date</th>
                <th>Check Out Date</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${requestScope.data}" var="c">
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
                    <td>${c.orderBoardingDetail.room.roomId}</td>
                    <td>
                        <c:forEach var="item" items="${c.orderServiceDetailList}" varStatus="loop">
                            <c:if test="${loop.index == loop.index}">
                                ${item.pet.petId}<br>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach var="item" items="${c.orderServiceDetailList}">
                            ${item.service.serviceId}<br>
                        </c:forEach>
                    </td>
                    <td>${c.orderBoardingDetail.checkInDate}</td>
                    <td>${c.orderBoardingDetail.checkOutDate}</td>
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
