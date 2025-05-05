<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vendor Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>Register New Vendor</h1>
    <form action="${pageContext.request.contextPath}/vendors/register" method="post" class="needs-validation" novalidate>
        <div class="mb-3">
            <label for="vendorName" class="form-label">Vendor Name</label>
            <input type="text" class="form-control" id="vendorName" name="name" required>
            <div class="invalid-feedback">
                Please provide a vendor name.
            </div>
        </div>

        <div class="mb-3">
            <label for="vendorType" class="form-label">Vendor Type</label>
            <select name="type" id="vendorType" class="form-select" required>
                <option value="">Select Vendor Type</option>
                <option value="Photographer">Photographer</option>
                <option value="Caterer">Caterer</option>
                <option value="Florist">Florist</option>
                <option value="Other">Other</option>
            </select>
            <div class="invalid-feedback">
                Please select a vendor type.
            </div>
        </div>

        <div class="mb-3">
            <label for="vendorLocation" class="form-label">Location</label>
            <input type="text" class="form-control" id="vendorLocation" name="location" required>
            <div class="invalid-feedback">
                Please provide a location.
            </div>
        </div>

        <div class="mb-3">
            <label for="vendorDescription" class="form-label">Description</label>
            <textarea class="form-control" id="vendorDescription" name="description" rows="3" required></textarea>
            <div class="invalid-feedback">
                Please provide a description.
            </div>
        </div>

        <div class="mb-3">
            <label for="vendorPrice" class="form-label">Price Range</label>
            <input type="number" class="form-control" id="vendorPrice" name="priceRange" required>
            <div class="invalid-feedback">
                Please provide a price range.
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Register Vendor</button>
    </form>
</div>

<script>
    // Custom JS to enable Bootstrap validation feedback
    (function () {
        'use strict'
        var forms = document.querySelectorAll('.needs-validation')
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }
                    form.classList.add('was-validated')
                }, false)
            })
    })();
</script>
</body>
</html>
