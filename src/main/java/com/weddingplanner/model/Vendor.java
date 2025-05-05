// Vendor.java
package com.weddingplanner.model;

import java.io.Serializable;
import java.util.UUID;

public class Vendor implements Serializable {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String location;
    private String description;
    private double priceRange;
    private String vendorType;

    // Default constructor
    public Vendor() {
        this.id = UUID.randomUUID().toString();
    }

    // Parameterized constructor
    public Vendor(String name, String email, String phone, String location,
                  String description, double priceRange, String vendorType) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.description = description;
        this.priceRange = priceRange;
        this.vendorType = vendorType;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(double priceRange) {
        this.priceRange = priceRange;
    }

    public String getVendorType() {
        return vendorType;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }

    // Method for vendor display info, which can be overridden by subclasses
    public String getDisplayInfo() {
        return "Vendor: " + name + " (" + vendorType + ") - " + location;
    }

    // toString method to help with file operations
    @Override
    public String toString() {
        return id + "," + name + "," + email + "," + phone + "," + location + ","
                + description + "," + priceRange + "," + vendorType;
    }
}