package com.marrymate.model;

public class CatererRefund extends RefundPolicy {
  
    public CatererRefund() {
        super("Caterer"); // Call parent constructor with vendor type
    }

    // Override calculateRefund to implement caterer-specific refund logic (Polymorphism)
    @Override
    public double calculateRefund(double amount, int daysBeforeEvent) {
        if (daysBeforeEvent >= 14) { // Check if cancellation is at least 14 days before event
            return amount * 0.75; // Return 75% of the amount as refund
        }
        return 0; // Return 0 if cancellation is too late
    }
}