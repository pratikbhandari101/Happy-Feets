<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/login.css">
    <jsp:include page="../includes/header.jsp">
        <jsp:param name="title" value="Login" />
    </jsp:include>
</head>
<body>
    
    <h2>Login</h2>
    
    <% if (request.getAttribute("error") != null) { %>
        <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>
    
    <form action="<%= request.getContextPath() %>/auth/login" method="post">
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <button type="submit">Login</button>
        </div>
    </form>
    
    <p>Don't have an account? <a href="<%= request.getContextPath() %>/auth/register">Register here</a>.</p>
    
    <jsp:include page="../includes/footer.jsp"/>
    
</body>
</html> 