<%-- 
    Document   : user_profile
    Created on : Feb 3, 2024, 11:34:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
    </head>
    <body>
        <h1>User Profile</h1>
        <hr>
        <img id="selectedImage" style="display: none;" alt="Selected Image" style='height: 30px; width: 30px'>
        <input type="file" name="chooseFile" id="fileInput"onchange="displayImage()" name="img"/>
        <script>
            function displayImage() {
                var input = document.getElementById('fileInput');
                var image = document.getElementById('selectedImage');

                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        image.src = e.target.result;
                        image.style.display = 'block';
                    };

                    reader.readAsDataURL(input.files[0]);
                }
            }
        </script>
        <form action="CustomerProfileServlet" method="post">
            <c:set var="c" value="${requestScope.customer}"/>
            <h3>${c.member} - ${c.point}</h3>
            First Name </br>
            <input type="text" name="firstName" value="${c.firstName}"/></br>
            Last Name </br>
            <input type="text" name="lastName" value="${c.lastName}"/></br>
            Email </br>
            <input type="text" name="email" value="${c.email}"/></br>
            Phone </br>
            <input type="number" name="phone" value="${c.phone}"/></br>
            </br><!-- comment -->
            <input type="submit" value="Update"/>
        </form>
    </body>
</html>
