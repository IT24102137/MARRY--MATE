package com.marrymate.model; 

public abstract class Payment {
    
    protected String bookingId; 
    protected double amount; 
    protected String status; // Payment status (e.g., Pending, Completed)

    
    public Payment(String bookingId, double amount) {
        this.bookingId = bookingId; 
        this.amount = amount;
        this.status = "Pending"; // Initialize status as Pending
    }

    // Abstract method to enforce payment processing logic in subclasses (Abstraction)
    public abstract boolean processPayment();

    // Protected method to update status, accessible only to subclasses (Information Hiding)
    protected void updateStatus(String status) {
        this.status = status; // Update the payment status
    }

    // Getter for bookingId
    public String getBookingId() { 
        return bookingId; 
    }

    // Getter for amount
    public double getAmount() { 
        return amount;
    }

    // Getter for status
    public String getStatus() { 
        return status; 
    }
}
