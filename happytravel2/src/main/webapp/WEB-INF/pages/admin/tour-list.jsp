<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin - Tour Packages</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css">
</head>
<body>
    <jsp:include page="/WEB-INF/includes/header.jsp" />

    <div class="container">
        <div class="admin-header">
            <h1>Tour Package Management</h1>
            <a href="${pageContext.request.contextPath}/admin/tours/add" class="add-button">Add New Package</a>
        </div>

        <!-- Error Message -->
        <c:if test="${not empty error}">
            <div class="error-message">
                ${error}
            </div>
        </c:if>

        <!-- Tour Packages Table -->
        <div class="table-container">
            <table class="admin-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Destination</th>
                        <th>Price</th>
                        <th>Duration</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tourPackages}" var="tour">
                        <tr>
                            <td>${tour.packageId}</td>
                            <td>${tour.name}</td>
                            <td>${tour.location}</td>
                            <td>
                                <fmt:formatNumber value="${tour.price}" type="currency" currencySymbol="$"/>
                            </td>
                            <td>${tour.duration} Days</td>
                            <td>
                                <%-- Removed start_date and end_date as they are not in the DB schema --%>
                                N/A
                            </td>
                            <td>
                                <%-- Removed start_date and end_date as they are not in the DB schema --%>
                                N/A
                            </td>
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/admin/tours/edit/${tour.packageId}" 
                                   class="edit-button">Edit</a>
                                <a href="${pageContext.request.contextPath}/admin/tours/delete/${tour.packageId}" 
                                   class="delete-button"
                                   onclick="return confirm('Are you sure you want to delete this tour package?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- No Results Message -->
        <c:if test="${empty tourPackages}">
            <div class="no-results">
                <p>No tour packages found.</p>
            </div>
        </c:if>
    </div>

    <jsp:include page="/WEB-INF/includes/footer.jsp" />
</body>
</html> 