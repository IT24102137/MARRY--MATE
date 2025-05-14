package com.marrymate.model;

public class PhotographerRefund extends RefundPolicy {
  
    public PhotographerRefund() {
        super("Photographer"); // Call parent constructor with vendor type
    }

    // Override calculateRefund to implement photographer-specific refund logic (Polymorphism)
    @Override
    public double calculateRefund(double amount, int daysBeforeEvent) {
        if (daysBeforeEvent >= 7) { // Check if cancellation is at least 7 days before event
            return amount * 0.5; // Return 50% of the amount as refund
        }
        return 0; // Return 0 if cancellation is too late
    }
}