<%-- 
    Document   : addBoarding
    Created on : Jan 25, 2024, 9:10:09 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Boarding</title>
    </head>
    <body>
        <h1>Add a new Boarding</h1>
        <h3 style="color: red">${requestScope.error}</h3>
        <form action="AddBoardingServlet">
            Enter Name:<input type="text" name="name"/><br/>
            Enter Description<textarea name="description" id="description" onkeydown="addHashOnEnter()"></textarea><br/>   
            <script>
                function addHashOnEnter() {
                    if (event.key === 'Enter') {
                        event.preventDefault();
                        document.getElementById("description").value += "#";
                    }
                }
            </script>
            Enter Image:<input type="text" name="img"/><br/>
            Enter Length:<input type="text" name="length"/><br/>
            Enter Height:<input type="text" name="height"/><br/>
            Enter Width:<input type="text" name="width"/><br/>
            Enter Max Weight:<input type="text" name="maxWeight"/><br/>
            Enter price:<input type="text" name="price"/><br/>
            <input type="submit" name="SAVE"/>
        </form>
    </body>
</html>
