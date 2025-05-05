<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vendor Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>Vendor Profile: ${vendor.name}</h1>

    <div class="row mb-4">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header bg-light">
                    <h5 class="card-title mb-0">${vendor.name}</h5>
                    <p class="badge bg-primary">${vendor.vendorType}</p>
                </div>
                <div class="card-body">
                    <p><strong>Location:</strong> ${vendor.location}</p>
                    <p><strong>Price Range:</strong> $${vendor.priceRange}</p>
                    <p><strong>Description:</strong></p>
                    <p>${vendor.description}</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-header bg-light">
                    Contact Information
                </div>
                <div class="card-body">
                    <p><strong>Email:</strong> ${vendor.email}</p>
                    <p><strong>Phone:</strong> ${vendor.phone}</p>
                    <p><strong>Website:</strong> <a href="${vendor.website}" target="_blank">${vendor.website}</a></p>
                </div>
            </div>
        </div>
    </div>

    <div class="text-center mt-4">
        <a href="${pageContext.request.contextPath}/vendors" class="btn btn-secondary">Back to Vendor Listings</a>
    </div>
</div>
</body>
</html>
