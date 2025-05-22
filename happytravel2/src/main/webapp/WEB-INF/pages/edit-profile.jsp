<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/edit-profile.css">
    <jsp:include page="../includes/header.jsp">
        <jsp:param name="title" value="Edit Profile" />
    </jsp:include>
</head>
<body>
    
    <h2>Edit Profile</h2>
    
    <% 
        User user = (User) request.getAttribute("user");
        if (user != null) { 
    %>
        <form action="<%= request.getContextPath() %>/profile/edit" method="post">
            <div>
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value="<%= user.getUsername() %>" readonly>
            </div>
             <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required>
            </div>
            <div>
                <label for="fullName">Full Name:</label>
                <input type="text" id="fullName" name="fullName" value="<%= user.getFullName() %>" required>
            </div>
            <div>
                <label for="phone">Phone:</label>
                <input type="tel" id="phone" name="phone" value="<%= user.getPhone() %>" required>
            </div>
            <div>
                <label for="address">Address:</label>
                <textarea id="address" name="address" required><%= user.getAddress() %></textarea>
            </div>
            <div>
                <button type="submit">Save Changes</button>
            </div>
        </form>
    <% } else { %>
        <p>User data not available for editing.</p>
    <% } %>
    
    <jsp:include page="../includes/footer.jsp"/>
    
</body>
</html> 