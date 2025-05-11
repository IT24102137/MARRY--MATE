package com.marrymate.model; 

public class Booking {
    // Private fields to encapsulate booking data (Encapsulation)
    private String bookingId; 
    private String vendorType; 
    private String eventDate; 
    private String details; // Additional details provided by the user
    private double amount;
    private String status; // Status of the booking (ex; Pending, Confirmed)

    //Parametrised Constructor
    public Booking(String bookingId, String vendorType, String eventDate, String details, double amount) {
        this.bookingId = bookingId;
        this.vendorType = vendorType; 
        this.eventDate = eventDate; 
        this.details = details; 
        this.amount = amount; 
        this.status = "Pending"; // Initialize status as Pending
    }

    // Getter for bookingId 
    public String getBookingId() { 
        return bookingId; 
    }

    // Getter for vendorType
    public String getVendorType() { 
        return vendorType; 
    }

    // Getter for eventDate
    public String getEventDate() { 
        return eventDate; 
    }

    // Getter for details
    public String getDetails() { 
        return details; 
    }

    // Getter for amount
    public double getAmount() { 
        return amount; 
    }

    // Getter for status
    public String getStatus() { 
        return status; 
    }

    // Setter for eventDate
    public void setEventDate(String eventDate) { 
        this.eventDate = eventDate; 
    }

    // Setter for details
    public void setDetails(String details) { 
        this.details = details; 
    }

    // Setter for amount
    public void setAmount(double amount) { 
        this.amount = amount; 
    }

    // Setter for status
    public void setStatus(String status) { 
        this.status = status; 
    }
}