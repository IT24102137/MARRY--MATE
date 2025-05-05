// CatererVendor.java
package com.weddingplanner.model;

public class CatererVendor extends Vendor {
    private String cuisineType;
    private boolean providesWaiterService;
    private int maxCapacity;

    // Default constructor
    public CatererVendor() {
        super();
        setVendorType("Caterer");
    }

    // Parameterized constructor
    public CatererVendor(String name, String email, String phone, String location,
                         String description, double priceRange,
                         String cuisineType, boolean providesWaiterService, int maxCapacity) {
        super(name, email, phone, location, description, priceRange, "Caterer");
        this.cuisineType = cuisineType;
        this.providesWaiterService = providesWaiterService;
        this.maxCapacity = maxCapacity;
    }

    // Getters and setters for caterer-specific fields
    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public boolean isProvidesWaiterService() {
        return providesWaiterService;
    }

    public void setProvidesWaiterService(boolean providesWaiterService) {
        this.providesWaiterService = providesWaiterService;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    // Override display info method to include caterer-specific details
    @Override
    public String getDisplayInfo() {
        return super.getDisplayInfo() + " | Cuisine: " + cuisineType
                + " | Max Capacity: " + maxCapacity
                + (providesWaiterService ? " | Waiter Service Included" : "");
    }

    // Override toString to include caterer-specific fields
    @Override
    public String toString() {
        return super.toString() + "," + cuisineType + "," + providesWaiterService + "," + maxCapacity;
    }
}
