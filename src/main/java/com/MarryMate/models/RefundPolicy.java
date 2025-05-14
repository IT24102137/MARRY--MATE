package com.marrymate.model;

public abstract class RefundPolicy {
   
    protected String vendorType; // Type of vendor (e.g., Photographer, Caterer)

   
    public RefundPolicy(String vendorType) {
        this.vendorType = vendorType; 
    }

    // Abstract method to enforce refund calculation in subclasses (Abstraction)
    public abstract double calculateRefund(double amount, int daysBeforeEvent);
}