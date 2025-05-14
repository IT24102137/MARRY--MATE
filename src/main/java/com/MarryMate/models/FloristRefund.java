package com.marrymate.model;

public class FloristRefund extends RefundPolicy {
 
    public FloristRefund() {
        super("Florist"); // Call parent constructor with vendor type
    }

    // Override calculateRefund to implement florist-specific refund logic (Polymorphism)
    @Override
    public double calculateRefund(double amount, int daysBeforeEvent) {
        if (daysBeforeEvent >= 7) { // Check if cancellation is at least 7 days before event
            return amount; // Return full amount as refund
        }
        return 0; // Return 0 if cancellation is too late
    }
}