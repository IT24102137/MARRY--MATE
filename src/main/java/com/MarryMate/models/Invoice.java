package com.marrymate.model;

public class Invoice {
    
    private String invoiceId; 
    private Booking booking; // Associated booking object
    private double tax; // Calculated tax amount
    private double totalAmount; // Total amount including tax

    
    public Invoice(String invoiceId, Booking booking) {
        this.invoiceId = invoiceId; 
        this.booking = booking;
        this.tax = booking.getAmount() * 0.1; // Calculate 10% tax based on booking amount
        this.totalAmount = booking.getAmount() + tax; // Calculate total amount (base + tax)
    }

    // Getter for invoiceId 
    public String getInvoiceId() { 
        return invoiceId; 
    }

    // Getter for booking
    public Booking getBooking() { 
        return booking; 
    }

    // Getter for tax
    public double getTax() { 
        return tax; 
    }

    // Getter for totalAmount
    public double getTotalAmount() { 
        return totalAmount; 
    }
}