<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin - ${tour == null ? 'Add' : 'Edit'} Tour Package</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css">
</head>
<body>
    <jsp:include page="/WEB-INF/includes/header.jsp" />

    <div class="container">
        <div class="admin-header">
            <h1>${tour == null ? 'Add New' : 'Edit'} Tour Package</h1>
            <a href="${pageContext.request.contextPath}/admin/tours" class="back-button">Back to List</a>
        </div>

        <!-- Error Message -->
        <c:if test="${not empty error}">
            <div class="error-message">
                ${error}
            </div>
        </c:if>

        <!-- Tour Package Form -->
        <form action="${pageContext.request.contextPath}/admin/tours" 
              method="post" 
              enctype="multipart/form-data"
              class="admin-form">
            
            <c:if test="${tour != null}">
                <input type="hidden" name="packageId" value="${tour.packageId}">
            </c:if>

            <div class="form-group">
                <label for="packageName">Package Name:</label>
                <input type="text" 
                       id="packageName" 
                       name="packageName" 
                       value="${tour.packageName}" 
                       required>
            </div>

            <div class="form-group">
                <label for="destination">Destination:</label>
                <input type="text" 
                       id="destination" 
                       name="destination" 
                       value="${tour.location}" 
                       required>
            </div>

            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" 
                          name="description" 
                          rows="4" 
                          required>${tour.description}</textarea>
            </div>

            <div class="form-group">
                <label for="price">Price ($):</label>
                <input type="number" 
                       id="price" 
                       name="price" 
                       value="${tour.price}" 
                       step="0.01" 
                       min="0" 
                       required>
            </div>

            <div class="form-group">
                <label for="durationDays">Duration (Days):</label>
                <input type="number" 
                       id="durationDays" 
                       name="duration" 
                       value="${tour.duration}" 
                       min="1" 
                       required>
            </div>

            <div class="form-group">
                <label for="image">Package Image:</label>
                <input type="file" 
                       id="image" 
                       name="image" 
                       accept="image/*"
                       ${tour == null ? 'required' : ''}>
                <c:if test="${tour != null && not empty tour.imageUrl}">
                    <div class="current-image">
                        <p>Current Image:</p>
                        <img src="${pageContext.request.contextPath}${tour.imageUrl}" 
                             alt="${tour.name}" 
                             style="max-width: 200px;">
                    </div>
                </c:if>
            </div>

            <div class="form-actions">
                <button type="submit" class="submit-button">
                    ${tour == null ? 'Add' : 'Update'} Package
                </button>
                <a href="${pageContext.request.contextPath}/admin/tours" class="cancel-button">Cancel</a>
            </div>
        </form>
    </div>

    <jsp:include page="/WEB-INF/includes/footer.jsp" />
</body>
</html> 