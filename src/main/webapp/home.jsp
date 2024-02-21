<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2/21/2024
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        nav {
            background-color: #333;
            color: #fff;
            padding: 10px;
            display: flex;
            justify-content: space-between;
        }

        .user-info {
            display: flex;
            align-items: center;
        }

        .logout-btn {
            background-color: #f00;
            color: #fff;
            padding: 5px 10px;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>

<%
    // Check if the emailOfTheUser cookie exists
    String userEmail = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("emailOfTheUser")) {
                userEmail = cookie.getValue();
                break;
            }
        }
    }

    // If emailOfTheUser cookie does not exist, redirect to signin
    if (userEmail == null) {
        response.sendRedirect("signin.jsp");
    }
%>

<nav>
    <div class="user-info">
        <div>Welcome <%= userEmail %></div>
    </div>
    <div>
        <form action="logoutServlet" method="post">
            <input type="submit" value="Logout" class="logout-btn">
        </form>
    </div>
</nav>

<!-- Your home page content goes here -->

</body>
</html>


</body>
</html>
