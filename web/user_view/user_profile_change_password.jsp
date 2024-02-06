<%-- 
    Document   : user_profile_change_password
    Created on : Feb 3, 2024, 11:13:21 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change password</title>
    </head>
    <body>
        <h1>Change Password</h1>
        <hr>
        <form action="UpdateCustomerPassword" method="post">
            <c:set var="c" value="${requestScope.customer}"/>
            <input type="hidden" name="customerId" value="${c.customerId}">
            <!-- Hiển thị thông tin khách hàng -->
            <p>Customer ID: ${c.customerId}</p>
            <p>First Name: ${c.firstName}</p>
            <p>Last Name: ${c.lastName}</p>
            <!-- Biểu mẫu nhập mật khẩu mới -->
            Old Password</br>
            <input type="password" name="oldPassword"/></br>
            <p style="color: red">${requestScope.error1}</p>
            New Password</br>
            <input type="password" name="newPassword"/></br>
            <p style="color: red">${requestScope.error2}</p>
            Confirm New Password</br>
            <input type="password" name="confirmPassword"/></br></br>
            <p style="color: red">${requestScope.error3}</p>
            <p style="color: green">${requestScope.success}</p>
            <input type="submit" value="Update Password" style="background-color: lightseagreen"> 
            <a href="#">I forget my password</a>
        </form>

    </body>
</html>
