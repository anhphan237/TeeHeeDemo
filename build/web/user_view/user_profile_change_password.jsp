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
        <form action="ChangePassword">
            Old Password</br>
            <input type="password" name="oldPassword"/></br>
            New Password</br>
            <input type="password" name="newPassword"/></br>
            Confirm New Password</br>
            <input type="password" name="confirmPassword"/></br></br>
            <input type="submit" value="Update Password" style="background-color: lightseagreen"> 
            <a href="#">I forget my password</a>
        </form>

    </body>
</html>
