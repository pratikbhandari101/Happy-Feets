<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>About Us - Happy Travel</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/about.css">
    <jsp:include page="../WEB-INF/includes/header.jsp">
        <jsp:param name="title" value="About Us" />
    </jsp:include>
</head>
<body>
    
    <div class="container">
        <h2>About Happy Travel</h2>
        
        <p>Welcome to Happy Travel, your trusted partner in exploring the world! We are passionate about creating unforgettable travel experiences for our customers.</p>
        
        <p>Our mission is to provide high-quality, affordable tour packages to exciting destinations around the globe. We believe that travel should be accessible to everyone, and we work hard to offer a wide variety of options to suit different budgets and interests.</p>
        
        <p>At Happy Travel, we are committed to customer satisfaction. Our team of experienced travel professionals is dedicated to providing excellent service and support throughout your journey, from planning to your return home.</p>
        
        <h3>Our Story</h3>
        <p>Happy Travel was founded in 2024 with the vision of making travel dreams come true. Since then, we have grown into a reputable travel company, helping thousands of travelers discover new cultures, landscapes, and adventures.</p>
        
        <h3>Why Choose Us?</h3>
        <ul>
            <li>Wide selection of tour packages</li>
            <li>Competitive prices</li>
            <li>Experienced and friendly team</li>
            <li>Commitment to customer satisfaction</li>
            <li>Hassle-free booking process</li>
        </ul>
        
        <p>Thank you for choosing Happy Travel. We look forward to helping you plan your next adventure!</p>
        
    </div>
    
    <jsp:include page="../WEB-INF/includes/footer.jsp"/>
    
</body>
</html> 