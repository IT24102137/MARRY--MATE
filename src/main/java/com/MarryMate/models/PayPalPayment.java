package com.marrymate.model;

public class PayPalPayment extends Payment {
   
    private String paypalEmail; // PayPal account email

   
    public PayPalPayment(String bookingId, double amount, String paypalEmail) {
        super(bookingId, amount); // Call parent constructor to set bookingId and amount
        this.paypalEmail = paypalEmail; 
    }

    // Override processPayment to implement PayPal-specific logic (Polymorphism)
    @Override
    public boolean processPayment() {
        // Simulate PayPal validation (check if email contains '@')
        if (paypalEmail != null && paypalEmail.contains("@")) {
            updateStatus("Completed"); // Update status to Completed if valid
            return true; // Return true to indicate successful payment
        }
        updateStatus("Failed"); // Update status to Failed if invalid
        return false; // Return false to indicate failed payment
    }
}