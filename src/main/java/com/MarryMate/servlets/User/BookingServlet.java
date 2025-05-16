package com.marrymate.servlet; 
import com.marrymate.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

/**
 * Servlet implementation for handling all booking-related operations
 * URL mapping: /BookingServlet
 */
@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles HTTP POST requests (booking creation)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Generate unique booking ID
        String bookingId = "MM-" + System.currentTimeMillis();
        
        // 2. Retrieve all form parameters
        String vendorType = request.getParameter("vendorType");
        String eventDate = request.getParameter("eventDate");
        String details = request.getParameter("vendorDetails");
        double amount = Double.parseDouble(request.getParameter("amount"));
        
        // 3. Create new Booking object
        Booking newBooking = new Booking(bookingId, vendorType, eventDate, details, amount);
        newBooking.setStatus("Confirmed");
        
        // 4. Generate invoice
        Invoice invoice = new Invoice("INV-" + bookingId, newBooking);
        
        // 5. Store in session
        HttpSession session = request.getSession();
        session.setAttribute("currentBooking", newBooking);
        session.setAttribute("currentInvoice", invoice);
        
        // 6. Redirect to payment page
        response.sendRedirect("payment.jsp");
    }

    /**
     * Handles HTTP GET requests (view/cancel bookings)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("view".equals(action)) {
            // Handle view booking request
            String bookingId = request.getParameter("bookingId");
            
            // In real app: Retrieve from database
            Booking booking = new Booking(bookingId, "Photographer", "2025-06-15", "Premium Package", 500.0);
            
            request.setAttribute("booking", booking);
            request.getRequestDispatcher("viewBooking.jsp").forward(request, response);
            
        } else if ("cancel".equals(action)) {
            // Handle cancellation
            processCancellation(request, response);
        }
    }
    
    /**
     * Processes booking cancellation and refund calculation
     */
    private void processCancellation(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String bookingId = request.getParameter("bookingId");
        String reason = request.getParameter("reason");
        
        // Get appropriate refund policy (would get vendor type from booking in real app)
        RefundPolicy policy = getRefundPolicy("Photographer");
        
        // Calculate refund (example values - would use actual booking data)
        double refundAmount = policy.calculateRefund(500.0, 30); 
        
        // Set results for JSP
        request.setAttribute("refundAmount", refundAmount);
        request.setAttribute("cancellationMessage", 
            "Booking #" + bookingId + " cancelled. Refund: $" + refundAmount);
        
        // Forward to confirmation page
        request.getRequestDispatcher("cancellationConfirmation.jsp").forward(request, response);
    }
    
    /**
     * Factory method to get appropriate refund policy implementation
     */
    private RefundPolicy getRefundPolicy(String vendorType) {
        switch(vendorType.toLowerCase()) {
            case "photographer":
                return new PhotographerRefund(); // 50% refund if >=7 days
            case "caterer":
                return new CatererRefund();     // 75% refund if >=14 days
            case "florist":
                return new FloristRefund();     // 100% refund if >=7 days
            default:
                return new RefundPolicy(vendorType) {
                    @Override
                    public double calculateRefund(double amount, int daysBeforeEvent) {
                        return 0; // Default no refund
                    }
                };
        }
    }
}