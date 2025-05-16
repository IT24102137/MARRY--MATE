package com.marrymate.servlet;

import com.marrymate.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

/**
 * Servlet implementation for handling all payment processing
 * URL mapping: /PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Processes payment form submissions
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Get payment parameters
        String paymentMethod = request.getParameter("paymentMethod");
        String bookingId = request.getParameter("bookingId");
        double amount = Double.parseDouble(request.getParameter("amount"));
        
        // 2. Retrieve booking from session
        HttpSession session = request.getSession();
        Booking booking = (Booking) session.getAttribute("currentBooking");
        
        // 3. Process payment based on method
        Payment payment = null;
        boolean paymentSuccess = false;
        
        try {
            switch(paymentMethod) {
                case "creditCard":
                    payment = processCreditCard(request, bookingId, amount);
                    break;
                    
                case "paypal":
                    payment = processPayPal(request, bookingId, amount);
                    break;
                    
                case "bankTransfer":
                    payment = processBankTransfer(request, bookingId, amount);
                    break;
            }
            
            if (payment != null) {
                paymentSuccess = payment.processPayment();
            }
            
        } catch (Exception e) {
            paymentSuccess = false;
            request.setAttribute("error", "Payment processing error: " + e.getMessage());
        }
        
        // 4. Handle payment result
        if (paymentSuccess) {
            handleSuccessfulPayment(session, booking, payment, request, response);
        } else {
            handleFailedPayment(session, request, response);
        }
    }
    
    /**
     * Processes credit card payment
     */
    private CreditCardPayment processCreditCard(HttpServletRequest request, String bookingId, double amount) {
        String cardNumber = request.getParameter("cardNumber");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");
        return new CreditCardPayment(bookingId, amount, cardNumber, expiryDate, cvv);
    }
    
    /**
     * Processes PayPal payment
     */
    private PayPalPayment processPayPal(HttpServletRequest request, String bookingId, double amount) {
        String paypalEmail = request.getParameter("paypalEmail");
        return new PayPalPayment(bookingId, amount, paypalEmail);
    }
    
    /**
     * Processes bank transfer payment
     */
    private BankTransferPayment processBankTransfer(HttpServletRequest request, String bookingId, double amount) {
        String accountNumber = request.getParameter("accountNumber");
        String routingNumber = request.getParameter("routingNumber");
        return new BankTransferPayment(bookingId, amount, accountNumber, routingNumber);
    }
    
    /**
     * Handles successful payment scenario
     */
    private void handleSuccessfulPayment(HttpSession session, Booking booking, 
            Payment payment, HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Update booking status
        booking.setStatus("Paid");
        
        // Store in session
        session.setAttribute("paymentStatus", "success");
        session.setAttribute("payment", payment);
        
        // Set confirmation attributes
        request.setAttribute("transactionId", payment.getBookingId());
        request.setAttribute("amount", payment.getAmount());
        
        // Forward to confirmation page
        request.getRequestDispatcher("paymentConfirmation.jsp").forward(request, response);
    }
    
    /**
     * Handles failed payment scenario
     */
    private void handleFailedPayment(HttpSession session, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        session.setAttribute("paymentStatus", "failed");
        request.setAttribute("errorMessage", "Payment processing failed. Please try again.");
        request.getRequestDispatcher("payment.jsp").forward(request, response);
    }

    /**
     * Handles payment status check requests
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("status".equals(action)) {
            String bookingId = request.getParameter("bookingId");
            
            // In real app: Query database for status
            request.setAttribute("paymentStatus", getPaymentStatus(bookingId));
            
            request.getRequestDispatcher("paymentStatus.jsp").forward(request, response);
        }
    }
    
    /**
     * Retrieves payment status (mock implementation)
     */
    private String getPaymentStatus(String bookingId) {
        // In real app: Query database or payment service
        return "Completed"; // Mock response
    }
}