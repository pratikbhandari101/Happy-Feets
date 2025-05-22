<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact Us - Happy Travel</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/contact.css">
    <jsp:include page="../includes/header.jsp">
        <jsp:param name="title" value="Contact Us" />
    </jsp:include>
</head>
<body>
    
    <div class="container">
        <h2>Contact Us</h2>
        
        <p>If you have any questions, inquiries, or feedback, please feel free to contact us using the form below or through the provided contact information.</p>
        
        <div class="contact-info">
            <p><strong>Email:</strong> info@happytravel.com</p>
            <p><strong>Phone:</strong> +1 123 456 7890</p>
            <p><strong>Address:</strong> 123 Travel Avenue, Suite 456, Global City, World 78901</p>
        </div>
        
        <div class="contact-form">
            <h3>Send us a Message</h3>
            <form action="#" method="post"> <%-- Replace # with a servlet URL for handling contact form submissions --%>
                <div class="form-group">
                    <label for="name">Your Name:</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="email">Your Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                 <div class="form-group">
                    <label for="subject">Subject:</label>
                    <input type="text" id="subject" name="subject">
                </div>
                <div class="form-group">
                    <label for="message">Your Message:</label>
                    <textarea id="message" name="message" rows="5" required></textarea>
                </div>
                <button type="submit">Send Message</button>
            </form>
        </div>
        
    </div>
    
    <jsp:include page="../includes/footer.jsp"/>
    
</body>
</html> 