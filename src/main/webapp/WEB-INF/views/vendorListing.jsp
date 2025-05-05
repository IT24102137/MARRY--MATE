<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vendor Listings</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row mb-4">
        <div class="col-md-8">
            <h1>Wedding Vendor Directory</h1>
            <c:if test="${not empty searchCriteria}">
                <p class="text-muted">Search Results for: ${searchCriteria}</p>
            </c:if>
        </div>
        <div class="col-md-4 text-end">
            <a href="${pageContext.request.contextPath}/vendors/register" class="btn btn-primary">Register New Vendor</a>
        </div>
    </div>

    <!-- Search Forms -->
    <div class="row mb-4">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    Search by Vendor Type
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/vendors/search/type" method="get" class="row g-3">
                        <div class="col-md-8">
                            <select name="type" class="form-select" required>
                                <option value="">Select Vendor Type</option>
                                <option value="Photographer">Photographer</option>
                                <option value="Caterer">Caterer</option>
                                <option value="Florist">Florist</option>
                                <option value="Other">Other</option>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <button type="submit" class="btn btn-secondary w-100">Search</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    Search by Location
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/vendors/search/location" method="get" class="row g-3">
                        <div class="col-md-8">
                            <input type="text" name="location" class="form-control" placeholder="Enter location" required>
                        </div>
                        <div class="col-md-4">
                            <button type="submit" class="btn btn-secondary w-100">Search</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Vendor Listings -->
    <div class="row">
        <c:choose>
            <c:when test="${empty vendors}">
                <div class="col-12">
                    <div class="alert alert-info">
                        No vendors found. <a href="${pageContext.request.contextPath}/vendors/register">Click here</a> to register a new vendor.
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${vendors}" var="vendor">
                    <div class="col-md-6 col-lg-4 mb-4">
                        <div class="card h-100">
                            <div class="card-header bg-light">
                                <h5 class="card-title mb-0">${vendor.name}</h5>
                                <p class="badge bg-primary">${vendor.vendorType}</p>
                            </div>
                            <div class="card-body">
                                <p><strong>Location:</strong> ${vendor.location}</p>
                                <p><strong>Price Range:</strong> $${vendor.priceRange}</p>
                                <p class="card-text">${vendor.description}</p>
                            </div>
                            <div class="card-footer bg-white">
                                <a href="${pageContext.request.contextPath}/vendors/profile/${vendor.id}" class="btn btn-outline-primary">View Details</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="mt-4 text-center">
        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">Back to Home</a>
    </div>
</div>
</body>
</html>
