package com.marrymate.model;

public class CreditCardPayment extends Payment {
   
    private String cardNumber; 
    private String expiryDate; 
    private String cvv;
 
    public CreditCardPayment(String bookingId, double amount, String cardNumber, String expiryDate, String cvv) {
        super(bookingId, amount); // Call parent constructor to set bookingId and amount
        this.cardNumber = cardNumber; 
        this.expiryDate = expiryDate; 
        this.cvv = cvv; 
    }

    // Override processPayment to implement credit card-specific logic (Polymorphism)
    @Override
    public boolean processPayment() {
        // Simulate credit card validation (check card number length and CVV)
        if (cardNumber != null && cardNumber.length() == 16 && cvv != null && cvv.length() == 3) {
            updateStatus("Completed"); // Update status to Completed if valid
            return true; // Return true to indicate successful payment
        }
        updateStatus("Failed"); // Update status to Failed if invalid
        return false; // Return false to indicate failed payment
    }
}