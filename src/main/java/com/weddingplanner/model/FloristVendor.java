// FloristVendor.java
package com.weddingplanner.model;

public class FloristVendor extends Vendor {
    private String specialization;
    private boolean providesSetupService;
    private boolean providesConsultation;

    // Default constructor
    public FloristVendor() {
        super();
        setVendorType("Florist");
    }

    // Parameterized constructor
    public FloristVendor(String name, String email, String phone, String location,
                         String description, double priceRange,
                         String specialization, boolean providesSetupService, boolean providesConsultation) {
        super(name, email, phone, location, description, priceRange, "Florist");
        this.specialization = specialization;
        this.providesSetupService = providesSetupService;
        this.providesConsultation = providesConsultation;
    }

    // Getters and setters for florist-specific fields
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public boolean isProvidesSetupService() {
        return providesSetupService;
    }

    public void setProvidesSetupService(boolean providesSetupService) {
        this.providesSetupService = providesSetupService;
    }

    public boolean isProvidesConsultation() {
        return providesConsultation;
    }

    public void setProvidesConsultation(boolean providesConsultation) {
        this.providesConsultation = providesConsultation;
    }

    // Override display info method to include florist-specific details
    @Override
    public String getDisplayInfo() {
        return super.getDisplayInfo() + " | Specialization: " + specialization
                + (providesSetupService ? " | Setup Service Included" : "")
                + (providesConsultation ? " | Consultation Available" : "");
    }

    // Override toString to include florist-specific fields
    @Override
    public String toString() {
        return super.toString() + "," + specialization + "," + providesSetupService + "," + providesConsultation;
    }
}