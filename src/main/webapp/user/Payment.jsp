<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Marry Mate - Payment</title>
    <!-- Bootstrap CSS for responsive design and styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- Font Awesome for icons (e.g., credit card icon) -->
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
        /* Style the payment section with a wedding-themed background */
        .payment-section {
            background-image: url('https://images.unsplash.com/photo-1519227357091-8ec0e4059c90?ixlib=rb-4.0.3&auto=format&fit=crop&w=1920&q=80');
            background-size: cover;
            background-position: center;
            background-color: var(--white);
            padding: 60px 0;
        }
        /* Style for error messages (e.g., payment failure) */
        .error-message {
            color: red;
            font-weight: bold;
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

    <!-- Payment Section: Displays the payment form and any error messages -->
    <section class="payment-section">
        <div class="container">
            <h2 class="text-center mb-4">Payment</h2>
            <div class="card">
                <div class="card-body">
                    <!-- Check for error messages set by PaymentServlet (e.g., failed payment) -->
                    <% if (request.getAttribute("errorMessage") != null) { %>
                        <p class="error-message"><%= request.getAttribute("errorMessage") %></p>
                    <% } %>
                    <!-- Payment form submits to PaymentServlet for processing -->
                    <form id="paymentForm" action="PaymentServlet" method="post">
                        <input type="hidden" name="action" value="process">
                        <div class="mb-3">
                            <label for="bookingId" class="form-label">Booking ID</label>
                            <input type="text" class="form-control" id="bookingId" name="bookingId" required>
                        </div>
                        <div class="mb-3">
                            <label for="amount" class="form-label">Amount</label>
                            <input type="number" class="form-control" id="amount" name="amount" step="0.01" required>
                        </div>
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
                            <!-- Credit Card Fields: Shown only if creditCard is selected -->
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
                            <!-- PayPal Fields: Shown only if paypal is selected -->
                            <div id="paypalFields" style="display: none;">
                                <div class="mb-3">
                                    <label for="paypalEmail" class="form-label">PayPal Email</label>
                                    <input type="email" class="form-control" id="paypalEmail" name="paypalEmail" placeholder="example@paypal.com">
                                </div>
                            </div>
                            <!-- Bank Transfer Fields: Shown only if bankTransfer is selected -->
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
                        <button type="submit" class="btn btn-primary"><i class="fas fa-credit-card"></i> Process Payment</button>
                    </form>
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
    <script>
        // Toggle payment method fields based on user selection
        document.getElementById('paymentMethod').addEventListener('change', function() {
            const method = this.value;
            document.getElementById('creditCardFields').style.display = method === 'creditCard' ? 'block' : 'none';
            document.getElementById('paypalFields').style.display = method === 'paypal' ? 'block' : 'none';
            document.getElementById('bankTransferFields').style.display = method === 'bankTransfer' ? 'block' : 'none';
        });
    </script>
</body>
</html>