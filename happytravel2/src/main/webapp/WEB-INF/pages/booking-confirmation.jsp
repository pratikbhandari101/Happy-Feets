<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.model.Booking" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Confirmation</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/booking-confirmation.css">
    <%-- Link to booking-specific CSS if needed --%>
    <jsp:include page="../includes/header.jsp">
        <jsp:param name="title" value="Booking Confirmation" />
    </jsp:include>
</head>
<body>
    
    <div class="container">
        <h2>Booking Confirmation</h2>
        
        <% Booking booking = (Booking) request.getAttribute("booking"); %>
        
        <% if (booking != null) { %>
            <div class="booking-details">
                <p><strong>Booking ID:</strong> <%= booking.getBookingId() %></p>
                <p><strong>Package ID:</strong> <%= booking.getPackageId() %></p>
                <%-- We might want to display package name here later, requires fetching the package --%>
                <p><strong>Booking Date:</strong> <%= booking.getBookingDate() %></p>
                <p><strong>Number of Travelers:</strong> <%= booking.getNumberOfTravelers() %></p>
                <p><strong>Status:</strong> <%= booking.getStatus() %></p>
                <%-- Add more booking details as needed --%>
            </div>
            
            <p>Your booking has been successfully placed. You will receive a confirmation email shortly (feature to be added).</p>
            
            <p><a href="<%= request.getContextPath() %>/tours">Browse More Tours</a></p>
            <%-- Link to user dashboard to view all bookings (feature to be added) --%>
            
        <% } else { %>
            <p>Booking details not found.</p>
            <p>There was an issue retrieving your booking information. Please check your profile for booking history or contact support.</p>
             <p><a href="<%= request.getContextPath() %>/tours">Browse Tours</a></p>
        <% } %>
    </div>
    
    <jsp:include page="../includes/footer.jsp"/>
    
</body>
</html> 