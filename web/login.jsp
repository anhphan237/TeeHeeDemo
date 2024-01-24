<%-- 
    Document   : login.jsp
    Created on : Jan 19, 2024, 10:10:58 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/login-form.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;1,200&family=Quicksand:wght@500&display=swap"
            rel="stylesheet"
            />
    </head>
    <body>
    <center class="login-page">
        <div class="login-form">
            <form action="DispatchServlet" method="POST">
                <h1>NEKO HEE</h1>
                <input type="text" name="email" placeholder="Email" class="input" />
                <br />
                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    class="input"
                    />
                <br />
                <input type="submit" value="Login" class="button" name="btAction" />
            </form>

            <p>
                ---------------------------------OR---------------------------------
            </p>
            <button><a href="">Continue with Google</a></button>
            <div><a href="">Forgot password?</a></div>
        </div>
        <div class="signin">
            <p>
                You don't have an account?
                <a href="registration.jsp">Registration</a>
            </p>
        </div>
    </center>
</body>
</html>
