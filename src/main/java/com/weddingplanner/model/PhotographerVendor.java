// PhotographerVendor.java
package com.weddingplanner.model;

public class PhotographerVendor extends Vendor {
    private String photographyStyle;
    private int yearsOfExperience;
    private boolean providesVideography;

    // Default constructor
    public PhotographerVendor() {
        super();
        setVendorType("Photographer");
    }

    // Parameterized constructor
    public PhotographerVendor(String name, String email, String phone, String location,
                              String description, double priceRange,
                              String photographyStyle, int yearsOfExperience,
                              boolean providesVideography) {
        super(name, email, phone, location, description, priceRange, "Photographer");
        this.photographyStyle = photographyStyle;
        this.yearsOfExperience = yearsOfExperience;
        this.providesVideography = providesVideography;
    }

    // Getters and setters for photographer-specific fields
    public String getPhotographyStyle() {
        return photographyStyle;
    }

    public void setPhotographyStyle(String photographyStyle) {
        this.photographyStyle = photographyStyle;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public boolean isProvidesVideography() {
        return providesVideography;
    }

    public void setProvidesVideography(boolean providesVideography) {
        this.providesVideography = providesVideography;
    }

    // Override display info method to include photographer-specific details
    @Override
    public String getDisplayInfo() {
        return super.getDisplayInfo() + " | Style: " + photographyStyle
                + " | Experience: " + yearsOfExperience + " years"
                + (providesVideography ? " | Includes Videography" : "");
    }

    // Override toString to include photographer-specific fields
    @Override
    public String toString() {
        return super.toString() + "," + photographyStyle + "," + yearsOfExperience + "," + providesVideography;
    }
}