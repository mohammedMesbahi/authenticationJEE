<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2/21/2024
  Time: 9:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Sign Up</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      background-color: #f4f4f4;
    }

    .signup-container {
      width: 300px;
      padding: 20px;
      background-color: #fff;
      border: 1px solid #ddd;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    label {
      display: block;
      margin-bottom: 8px;
    }

    input {
      width: 100%;
      padding: 8px;
      margin-bottom: 16px;
      box-sizing: border-box;
    }

    .error {
      color: #ff0000;
      margin-bottom: 16px;
    }

    .submit-btn {
      background-color: #333;
      color: #fff;
      padding: 10px;
      border: none;
      cursor: pointer;
    }
  </style>
</head>
<body>

<div class="signup-container">
  <h2>Sign Up</h2>

  <%-- Display errors if any --%>
  <div class="error">
    <jsp:include page="errorTags.jsp" />
  </div>

  <form action="signupServlet" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>

    <input type="submit" value="Sign Up" class="submit-btn">
  </form>
  <p>you have an account? <a href="signin.jsp">Sign In</a></p>
</div>

</body>
</html>

