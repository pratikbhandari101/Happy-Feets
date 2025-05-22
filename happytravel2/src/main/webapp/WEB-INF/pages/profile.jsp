<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/profile.css">
    <jsp:include page="../includes/header.jsp">
        <jsp:param name="title" value="User Profile" />
    </jsp:include>
</head>
<body>
    
    <h2>User Profile</h2>
    
    <% 
        User user = (User) request.getAttribute("user");
        if (user != null) { 
    %>
        <div>
            <p><strong>Username:</strong> <%= user.getUsername() %></p>
            <p><strong>Email:</strong> <%= user.getEmail() %></p>
            <p><strong>Full Name:</strong> <%= user.getFullName() %></p>
            <p><strong>Phone:</strong> <%= user.getPhone() %></p>
            <p><strong>Address:</strong> <%= user.getAddress() %></p>
        </div>
        
        <p><a href="<%= request.getContextPath() %>/profile/edit">Edit Profile</a></p>
    <% } else { %>
        <p>User data not available.</p>
    <% } %>
    
    <jsp:include page="../includes/footer.jsp"/>
    
</body>
</html> 