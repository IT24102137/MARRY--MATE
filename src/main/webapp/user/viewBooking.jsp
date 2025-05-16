<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.marrymate.model.Booking" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Marry Mate - View Booking</title>
    <!-- Bootstrap CSS for responsive design and styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- Font Awesome for icons (e.g., back arrow) -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous">
    <!-- Google Fonts for consistent typography -->
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
    <style>
        /* Define custom color variables for branding */
        :root {
            --navy-blue: #1B263B;
            --gold: #D4A017;
            --white: #FFFFFF;
        }
        body {
            font-family: 'Roboto', sans-serif;
            background-color: var(--white);
            color: var(--navy-blue);
        }
        h1, h2, h3, h4, h5, h6 {
            font-family: 'Playfair Display', serif;
            color: var(--navy-blue);
        }
        .navbar {
            background-color: var(--navy-blue);
        }
        .navbar-brand, .nav-link {
            color: var(--white) !important;
        }
        .nav-link:hover {
            color: var(--gold) !important;
        }
        .btn-primary {
            background-color: var(--gold);
            border-color: var(--gold);
            color: var(--navy-blue);
        }
        .btn-primary:hover {
            background-color: var(--navy-blue);
            border-color: var(--navy-blue);
            color: var(--white);
        }
        .card {
            border: none;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: rgba(255, 255, 255, 0.95);
        }
        /* Style the view section with a wedding-themed background */
        .view-section {
            background-image: linear-gradient(rgba(255, 255, 255, 0.9), rgba(255, 255, 255, 0.9)), url('https://images.unsplash.com/photo-1511285560929-80b456feb0be?ixlib=rb-4.0.3&auto=format&fit=crop&w=1920&q=80');
            background-size: cover;
            background-position: center;
            background-color: var(--white);
            padding: 60px 0;
        }
    </style>
</head>
<body>
    <!-- Navbar for navigation, consistent across all pages -->
    <nav class="navbar navbar-expand-lg">
        <div class="container">
            <a class="navbar-brand" href="#">Marry Mate</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="vendor_booking.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="vendor_booking.jsp">Vendor Booking</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Profile</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- View Booking Section: Displays details of a specific booking -->
    <section class="view-section">
        <div class="container">
            <h2 class="text-center mb-4">Booking Details</h2>
            <div class="card">
                <div class="card-body">
                    <!-- Retrieve booking object set by BookingServlet -->
                    <% Booking booking = (Booking) request.getAttribute("booking"); %>
                    <% if (booking != null) { %>
                        <p><strong>Booking ID:</strong> <%= booking.getBookingId() %></p>
                        <p><strong>Vendor Type:</strong> <%= booking.getVendorType() %></p>
                        <p><strong>Event Date:</strong> <%= booking.getEventDate() %></p>
                        <p><strong>Details:</strong> <%= booking.getDetails() %></p>
                        <p><strong>Amount:</strong> $<%= String.format("%.2f", booking.getAmount()) %></p>
                        <p><strong>Status:</strong> <%= booking.getStatus() %></p>
                    <% } else { %>
                        <p>No booking found.</p>
                    <% } %>
                    <!-- Link to return to the main booking page -->
                    <a href="vendor_booking.jsp" class="btn btn-primary"><i class="fas fa-arrow-left"></i> Back to Bookings</a>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer with copyright information -->
    <footer class="bg-dark text-white py-4">
        <div class="container text-center">
            <p>© 2025 Marry Mate. All Rights Reserved.</p>
        </div>
    </footer>

    <!-- Bootstrap JS for dynamic elements -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>