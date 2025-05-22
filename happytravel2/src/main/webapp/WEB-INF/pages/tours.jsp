<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tour Packages - Happy Travel</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tours.css">
</head>
<body>
    <jsp:include page="/WEB-INF/includes/header.jsp" />

    <div class="container">
        <h1>Tour Packages</h1>

        <!-- Search and Filter Form -->
        <div class="search-filter-container">
            <form action="${pageContext.request.contextPath}/tours" method="get" class="search-filter-form">
                <div class="search-box">
                    <input type="text" name="search" value="${searchTerm}" placeholder="Search packages...">
                </div>
                
                <div class="filter-group">
                    <input type="text" name="destination" value="${destination}" placeholder="Destination">
                    
                    <div class="price-range">
                        <input type="number" name="minPrice" value="${minPrice}" placeholder="Min Price">
                        <input type="number" name="maxPrice" value="${maxPrice}" placeholder="Max Price">
                    </div>
                    
                    <div class="duration-range">
                        <input type="number" name="minDuration" value="${minDuration}" placeholder="Min Days">
                        <input type="number" name="maxDuration" value="${maxDuration}" placeholder="Max Days">
                    </div>
                    
                    <button type="submit" class="search-button">Search</button>
                    <a href="${pageContext.request.contextPath}/tours" class="clear-button">Clear Filters</a>
                </div>
            </form>
        </div>

        <!-- Error Message -->
        <c:if test="${not empty error}">
            <div class="error-message">
                ${error}
            </div>
        </c:if>

        <!-- Tour Packages Grid -->
        <div class="tour-packages-grid">
            <c:forEach items="${tourPackages}" var="tour">
                <div class="tour-card">
                    <div class="tour-image">
                        <img src="${pageContext.request.contextPath}/resources/images/${tour.imageUrl}" 
                             alt="${tour.name}">
                    </div>
                    <div class="tour-info">
                        <h3>${tour.name}</h3>
                        <p class="destination">${tour.location}</p>
                        <p class="duration">${tour.duration} Days</p>
                        <p class="price">
                            <fmt:formatNumber value="${tour.price}" type="currency" currencySymbol="$"/>
                        </p>
                        <div class="tour-dates">
                            <%-- Removed start_date and end_date as they are not in the DB schema --%>
                             N/A
                            <%-- Removed start_date and end_date as they are not in the DB schema --%>
                             N/A
                        </div>
                        <a href="${pageContext.request.contextPath}/tours/view/${tour.packageId}" 
                           class="view-details-button">View Details</a>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- No Results Message -->
        <c:if test="${empty tourPackages}">
            <div class="no-results">
                <p>No tour packages found matching your criteria.</p>
            </div>
        </c:if>
    </div>

    <jsp:include page="/WEB-INF/includes/footer.jsp" />
</body>
</html> 