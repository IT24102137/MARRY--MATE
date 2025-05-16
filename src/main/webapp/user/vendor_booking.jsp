<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.marrymate.model.Booking, com.marrymate.model.Invoice" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Marry Mate - Vendor Booking & Payment</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
    <style>
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

        .card-header {
            background-color: var(--navy-blue);
            color: var(--white);
        }

        .form-control:focus {
            border-color: var(--gold);
            box-shadow: 0 0 5px rgba(212, 160, 23, 0.5);
        }

        .history-table th {
            background-color: var(--navy-blue);
            color: var(--white);
        }

        .history-table td {
            background-color: var(--white);
        }

        .refund-panel {
            background-color: var(--navy-blue);
            color: var(--white);
            padding: 20px;
            border-radius: 8px;
        }

        .hero-section {
            background-image: linear-gradient(rgba(27, 38, 59, 0.7), rgba(27, 38, 59, 0.7)), url('https://images.unsplash.com/photo-1515934751635-c81c6bc9a2d8?ixlib=rb-4.0.3&auto=format&fit=crop&w=1920&q=80');
            background-size: cover;
            background-position: center;
            background-color: var(--navy-blue);
            color: var(--white);
            padding: 80px 0;
            text-align: center;
        }

        .vendor-section {
            background-image: linear-gradient(rgba(27, 38, 59, 0.6), rgba(27, 38, 59, 0.6)), url('https://images.unsplash.com/photo-1469371670807-013ccf25f16a?ixlib=rb-4.0.3&auto=format&fit=crop&w=1920&q=80');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            background-color: var(--white);
            min-height: 100vh;
            display: flex;
            align-items: center;
            padding: 60px 0;
        }

        .payment-section {
            background-image: url('https://images.unsplash.com/photo-1519227357091-8ec0e4059c90?ixlib=rb-4.0.3&auto=format&fit=crop&w=1920&q=80');
            background-size: cover;
            background-position: center;
            background-color: var(--white);
            padding: 60px 0;
        }

        .history-section {
            background-image: linear-gradient(rgba(255, 255, 255, 0.9), rgba(255, 255, 255, 0.9)), url('https://images.unsplash.com/photo-1511285560929-80b456feb0d7?ixlib=rb-4.0.3&auto=format&fit=crop&w=1920&q=80');
            background-size: cover;
            background-position: center;
            background-color: var(--white);
            padding: 60px 0;
        }

        .refund-section {
            background-image: url('https://images.unsplash.com/photo-1511285560929-80b456feb0be?ixlib=rb-4.0.3&auto=format&fit=crop&w=1920&q=80');
            background-size: cover;
            background-position: center;
            background-color: var(--white);
            padding: 60px 0;
        }

        .decorative-divider {
            width: 100px;
            height: 2px;
            background-color: var(--gold);
            margin: 20px auto;
        }
    </style>
</head>
<body>
    <!-- Hero Section -->
    <section class="hero-section">
        <div class="container">
            <h1>Marry Mate</h1>
            <p>Plan your dream wedding with ease</p>
            <div class="decorative-divider"></div>
        </div>
    </section>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg">
        <div class="container">
            <a class="navbar-brand" href="#">Marry Mate</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Vendor Booking</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Profile</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Vendor Booking Section -->
    <section class="vendor-section">
        <div class="container">
            <h2 class="text-center mb-4">Vendor Booking</h2>
            <div class="card">
                <div class="card-body">
                    <form id="bookingForm" action="BookingServlet" method="post">
                        <input type="hidden" name="action" value="create">
                        <div class="mb-3">
                            <label for="vendorType" class="form-label">Vendor Type</label>
                            <select class="form-control" id="vendorType" name="vendorType" required>
                                <option value="">Select Vendor</option>
                                <option value="photographer">Photographer</option>
                                <option value="caterer">Caterer</option>
                                <option value="florist">Florist</option>
                                <option value="venue">Venue</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="eventDate" class="form-label">Event Date</label>
                            <input type="date" class="form-control" id="eventDate" name="eventDate" required>
                        </div>
                        <div class="mb-3">
                            <label for="vendorDetails" class="form-label">Additional Details</label>
                            <textarea class="form-control" id="vendorDetails" name="vendorDetails" rows="4"></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="amount" class="form-label">Amount</label>
                            <input type="number" class="form-control" id="amount" name="amount" step="0.01" required>
                        </div>
                        <button type="submit" class="btn btn-primary"><i class="fas fa-calendar-check"></i> Book Vendor</button>
                    </form>
                </div>
            </div>
        </div>
    </section>

    <!-- Payment Processing Section -->
    <section class="payment-section">
        <div class="container">
            <h2 class="text-center mb-4">Payment Processing</h2>
            <div class="card">
                <div class="card-body">
                    <form id="paymentForm" action="PaymentServlet" method="post">
                        <input type="hidden" name="action" value="process">
                        <div class="mb-3">
                            <label for="paymentMethod" class="form-label">Payment Method</label>
                            <select class="form-select" id="paymentMethod" name="paymentMethod" required>
                                <option value="">Select Payment Method</option>
                                <option value="creditCard">Credit Card</option>
                                <option value="paypal">PayPal</option>
                                <option value="bankTransfer">Bank Transfer</option>
                            </select>
                        </div>
                        <div id="paymentDetails">
                            <!-- Credit Card Fields -->
                            <div id="creditCardFields" style="display: none;">
                                <div class="mb-3">
                                    <label for="cardNumber" class="form-label">Card Number</label>
                                    <input type="text" class="form-control" id="cardNumber" name="cardNumber" placeholder="1234 5678 9012 3456">
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="expiryDate" class="form-label">Expiry Date</label>
                                        <input type="text" class="form-control" id="expiryDate" name="expiryDate" placeholder="MM/YY">
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="cvv" class="form-label">CVV</label>
                                        <input type="text" class="form-control" id="cvv" name="cvv" placeholder="123">
                                    </div>
                                </div>
                            </div>
                            <!-- PayPal Fields -->
                            <div id="paypalFields" style="display: none;">
                                <div class="mb-3">
                                    <label for="paypalEmail" class="form-label">PayPal Email</label>
                                    <input type="email" class="form-control" id="paypalEmail" name="paypalEmail" placeholder="example@paypal.com">
                                </div>
                            </div>
                            <!-- Bank Transfer Fields -->
                            <div id="bankTransferFields" style="display: none;">
                                <div class="mb-3">
                                    <label for="accountNumber" class="form-label">Account Number</label>
                                    <input type="text" class="form-control" id="accountNumber" name="accountNumber" placeholder="Account Number">
                                </div>
                                <div class="mb-3">
                                    <label for="routingNumber" class="form-label">Routing Number</label>
                                    <input type="text" class="form-control" id="routingNumber" name="routingNumber" placeholder="Routing Number">
                                </div>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="bookingId" class="form-label">Booking ID</label>
                            <input type="text" class="form-control" id="bookingId" name="bookingId" required>
                        </div>
                        <div class="mb-3">
                            <label for="amount" class="form-label">Amount</label>
                            <input type="number" class="form-control" id="amount" name="amount" step="0.01" required>
                        </div>
                        <button type="submit" class="btn btn-primary"><i class="fas fa-credit-card"></i> Process Payment</button>
                    </form>
                </div>
            </div>
        </div>
    </section>

    <!-- Booking History Section -->
    <section class="history-section">
        <div class="container">
            <h2 class="text-center mb-4">Booking History & Invoices</h2>
            <div class="card">
                <div class="card-body">
                    <table class="table history-table">
                        <thead>
                            <tr>
                                <th>Booking ID</th>
                                <th>Vendor Type</th>
                                <th>Event Date</th>
                                <th>Details</th>
                                <th>Amount</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                HttpSession session = request.getSession();
                                List<Booking> bookings = (List<Booking>) session.getAttribute("bookings");
                                if (bookings == null) {
                                    bookings = new java.util.ArrayList<>();
                                    session.setAttribute("bookings", bookings);
                                }
                                Booking currentBooking = (Booking) session.getAttribute("currentBooking");
                                if (currentBooking != null && !bookings.contains(currentBooking)) {
                                    bookings.add(currentBooking);
                                }
                                for (Booking b : bookings) {
                            %>
                            <tr>
                                <td><%= b.getBookingId() %></td>
                                <td><%= b.getVendorType() %></td>
                                <td><%= b.getEventDate() %></td>
                                <td><%= b.getDetails() %></td>
                                <td>$<%= String.format("%.2f", b.getAmount()) %></td>
                                <td><%= b.getStatus() %></td>
                                <td>
                                    <a href="BookingServlet?action=view&bookingId=<%= b.getBookingId() %>" class="btn btn-sm btn-primary"><i class="fas fa-file-invoice"></i> View</a>
                                    <a href="BookingServlet?action=cancel&bookingId=<%= b.getBookingId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to cancel booking <%= b.getBookingId() %>?')"><i class="fas fa-trash"></i> Cancel</a>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>

    <!-- Refund/Cancellation Panel -->
    <section class="refund-section">
        <div class="container">
            <h2 class="text-center mb-4">Refund/Cancellation</h2>
            <div class="refund-panel">
                <h4>Request a Refund</h4>
                <form id="refundForm" action="BookingServlet" method="post">
                    <input type="hidden" name="action" value="cancel">
                    <div class="mb-3">
                        <label for="bookingId" class="form-label">Booking ID</label>
                        <input type="text" class="form-control" id="bookingId" name="bookingId" placeholder="Enter Booking ID" required>
                    </div>
                    <div class="mb-3">
                        <label for="reason" class="form-label">Reason for Cancellation</label>
                        <textarea class="form-control" id="reason" name="reason" rows="4" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary"><i class="fas fa-undo"></i> Request Refund</button>
                </form>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer class="bg-dark text-white py-4">
        <div class="container text-center">
            <p>© 2025 Marry Mate. All Rights Reserved.</p>
        </div>
    </footer>

    <!-- Bootstrap JS and Custom JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script>
        // Payment Method Toggle
        document.getElementById('paymentMethod').addEventListener('change', function() {
            const method = this.value;
            document.getElementById('creditCardFields').style.display = method === 'creditCard' ? 'block' : 'none';
            document.getElementById('paypalFields').style.display = method === 'paypal' ? 'block' : 'none';
            document.getElementById('bankTransferFields').style.display = method === 'bankTransfer' ? 'block' : 'none';
        });

        // Form Submission Handlers (removed alerts, integrated with backend)
        document.getElementById('bookingForm').addEventListener('submit', function(e) {
            // e.preventDefault(); // Removed to allow form submission to servlet
            // Backend handles this via BookingServlet
        });

        document.getElementById('paymentForm').addEventListener('submit', function(e) {
            // e.preventDefault(); // Removed to allow form submission to servlet
            // Backend handles this via PaymentServlet
        });

        document.getElementById('refundForm').addEventListener('submit', function(e) {
            // e.preventDefault(); // Removed to allow form submission to servlet
            // Backend handles this via BookingServlet
        });

        // Action Button Handlers (integrated with backend)
        function viewInvoice(bookingId) {
            window.location.href = "BookingServlet?action=view&bookingId=" + bookingId;
        }

        function editBooking(bookingId) {
            alert('Edit functionality not implemented yet.'); // Placeholder, to be implemented
        }

        function cancelBooking(bookingId) {
            if (confirm('Are you sure you want to cancel booking ' + bookingId + '?')) {
                window.location.href = "BookingServlet?action=cancel&bookingId=" + bookingId;
            }
        }

        // Resource Loading Error Handler
        window.addEventListener('error', function(event) {
            if (event.target.tagName === 'LINK' || event.target.tagName === 'SCRIPT' || event.target.tagName === 'IMG') {
                console.error('Failed to load resource:', event.target.src || event.target.href);
            }
        }, true);
    </script>
</body>
</html>