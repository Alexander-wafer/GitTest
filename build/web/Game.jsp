<%-- 
    Document   : Game
    Created on : 07.07.2020, 19:20:55
    Author     : Zver
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        .block{
            width: 500px;
            height: 500px;
            margin: 0 auto;
            text-align: center;
        }
    </style>
    <body>
        <div class="block">
            <h1>игра началась</h1>
            <%
                ArrayList<String> track = (ArrayList<String>)request.getAttribute("track");
                    for (String item:track) {
                       out.println("<p>"+item+"</p>");
                    }
            %>
            <form action="GameServer" method="post">
                <input type="text" maxlength="4" name="reply">
                <br>
                <button>click</button>
                <br>
                <a href="GameServer">новая игра</a>
            </form>

            
        </div>
    </body>
</html>
