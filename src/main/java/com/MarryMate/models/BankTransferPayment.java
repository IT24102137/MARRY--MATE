package com.marrymate.model;

public class BankTransferPayment extends Payment {
    
    private String accountNumber; 
    private String routingNumber; // Bank routing number

  
    public BankTransferPayment(String bookingId, double amount, String accountNumber, String routingNumber) {
        super(bookingId, amount); // Call parent constructor to set bookingId and amount
        this.accountNumber = accountNumber; 
        this.routingNumber = routingNumber; 
    }

    // Override processPayment to implement bank transfer-specific logic (Polymorphism)
    @Override
    public boolean processPayment() {
        // Simulate bank transfer validation (check lengths of account and routing numbers)
        if (accountNumber != null && accountNumber.length() > 5 && routingNumber != null && routingNumber.length() == 9) {
            updateStatus("Completed"); // Update status to Completed if valid
            return true; // Return true to indicate successful payment
        }
        updateStatus("Failed"); // Update status to Failed if invalid
        return false; // Return false to indicate failed payment
    }
}
