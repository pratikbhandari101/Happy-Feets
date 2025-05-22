<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/includes/header.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>

<div class="hero-section bg-primary text-white py-5 mb-5">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md-6">
                <h1 class="display-4">Welcome to Happy Feet</h1>
                <p class="lead">Discover amazing destinations and create unforgettable memories with our curated tour packages.</p>
                <a href="${pageContext.request.contextPath}/pages/tours.jsp" class="btn btn-light btn-lg">Explore Tours</a>
            </div>
            <div class="col-md-6">
                <img src="${pageContext.request.contextPath}/resources/images/hero-image.jpg" alt="Travel" class="img-fluid rounded">
            </div>
        </div>
    </div>
</div>

<div class="container">
    <h2 class="text-center mb-4">Why Choose Us?</h2>
    <div class="row">
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body text-center">
                    <i class="fas fa-map-marked-alt fa-3x mb-3 text-primary"></i>
                    <h5 class="card-title">Expert Guides</h5>
                    <p class="card-text">Our experienced guides ensure you get the most out of your travel experience.</p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body text-center">
                    <i class="fas fa-hotel fa-3x mb-3 text-primary"></i>
                    <h5 class="card-title">Quality Accommodations</h5>
                    <p class="card-text">We carefully select comfortable and well-located accommodations for your stay.</p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body text-center">
                    <i class="fas fa-shield-alt fa-3x mb-3 text-primary"></i>
                    <h5 class="card-title">Safe Travel</h5>
                    <p class="card-text">Your safety is our priority. We ensure all tours meet the highest safety standards.</p>
                </div>
            </div>
        </div>
    </div>

    <h2 class="text-center my-5">Popular Destinations</h2>
    <div class="row">
        <div class="col-md-4 mb-4">
            <div class="card">
                <img src="${pageContext.request.contextPath}/resources/images/kathmandu.jpg" class="card-img-top" alt="Destination 1">
                <div class="card-body">
                    <h5 class="card-title">Kathmandu, Nepal</h5>
                    <p class="card-text">Experience the perfect blend of culture,and adventure.</p>
                    <a href="#" class="btn btn-primary">Learn More</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card">
                <img src="${pageContext.request.contextPath}/resources/images/bhaktpur.jpg" class="card-img-top" alt="Destination 2">
                <div class="card-body">
                    <h5 class="card-title">Bhaktapur, Nepal</h5>
                    <p class="card-text">Discover the city of love, art, and amazing cuisine.</p>
                    <a href="#" class="btn btn-primary">Learn More</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card">
                <img src="${pageContext.request.contextPath}/resources/images/lalitpur.jpg" class="card-img-top" alt="Destination 3">
                <div class="card-body">
                    <h5 class="card-title">lalitpur, Nepal</h5>
                    <p class="card-text">Immerse yourself in the unique blend of tradition and modernity.</p>
                    <a href="#" class="btn btn-primary">Learn More</a>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"/> 