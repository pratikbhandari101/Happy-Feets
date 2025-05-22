<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.model.TourPackage" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tour Package Details</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/package-details.css">
    <jsp:include page="../includes/header.jsp">
        <jsp:param name="title" value="Tour Package Details" />
    </jsp:include>
</head>
<body>
    
    <h2>Tour Package Details</h2>
    
    <% TourPackage pkg = (TourPackage) request.getAttribute("tourPackage"); %>
    
    <% if (pkg != null) { %>
        <div>
            <img src="<%= request.getContextPath() %>/resources/images/<%= pkg.getImageUrl() %>" alt="<%= pkg.getName() %> Image" class="package-image">
            <h3><%= pkg.getName() %></h3>
            <p><strong>Description:</strong> <%= pkg.getDescription() %></p>
            <p><strong>Price:</strong> <%= pkg.getPrice() %></p>
            <p><strong>Duration:</strong> <%= pkg.getDuration() %> days</p>
            <%-- Add other details here --%>
        </div>
        
        <h3>Book this Tour Package</h3>
        <form action="<%= request.getContextPath() %>/bookings" method="post">
            <input type="hidden" name="packageId" value="<%= pkg.getPackageId() %>">
            <div>
                <label for="numberOfTravelers">Number of Travelers:</label>
                <input type="number" id="numberOfTravelers" name="numberOfTravelers" min="1" value="1" required>
            </div>
            <button type="submit">Book Now</button>
        </form>
        
        <p><a href="<%= request.getContextPath() %>/tours">Back to Tours List</a></p>
        
    <% } else { %>
        <p>Tour package not found.</p>
    <% } %>
    
    <jsp:include page="../includes/footer.jsp"/>
    
</body>
</html> 