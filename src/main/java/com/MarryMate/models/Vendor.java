package com.MarryMate.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Vendor class for wedding service providers
 * Extends the base User class with vendor-specific attributes
 */
public class Vendor extends User {
    
    private String businessName;
    private String contactPerson;
    private List<String> serviceIds;
    private String description;
    private PriceRange priceRange;
    private double averagePrice;
    private String location;
    private List<String> availability;
    private double rating;
    private List<Review> reviews;
    private List<String> portfolioImages;
    private boolean featured;
    
    // Default constructor
    public Vendor() {
        super();
        setRole("vendor");
        this.serviceIds = new ArrayList<>();
        this.availability = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.portfolioImages = new ArrayList<>();
        this.featured = false;
    }
    
    // Parameterized constructor
    public Vendor(String vendorId, String username, String password, String email, 
                String businessName, String contactPerson, String phoneNumber) {
        super(vendorId, username, password, email, contactPerson, phoneNumber, "vendor");
        this.businessName = businessName;
        this.contactPerson = contactPerson;
        this.serviceIds = new ArrayList<>();
        this.availability = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.portfolioImages = new ArrayList<>();
        this.featured = false;
        this.rating = 0.0;
    }
    
    // Getters and setters for Vendor specific fields
    public String getBusinessName() {
        return businessName;
    }
    
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
    
    public String getContactPerson() {
        return contactPerson;
    }
    
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    
    public List<String> getServiceIds() {
        return serviceIds;
    }
    
    public void setServiceIds(List<String> serviceIds) {
        this.serviceIds = serviceIds;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public PriceRange getPriceRange() {
        return priceRange;
    }
    
    public void setPriceRange(PriceRange priceRange) {
        this.priceRange = priceRange;
    }
    
    public double getAveragePrice() {
        return averagePrice;
    }
    
    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public List<String> getAvailability() {
        return availability;
    }
    
    public void setAvailability(List<String> availability) {
        this.availability = availability;
    }
    
    public double getRating() {
        return rating;
    }
    
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    public List<Review> getReviews() {
        return reviews;
    }
    
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    
    public List<String> getPortfolioImages() {
        return portfolioImages;
    }
    
    public void setPortfolioImages(List<String> portfolioImages) {
        this.portfolioImages = portfolioImages;
    }
    
    public boolean isFeatured() {
        return featured;
    }
    
    public void setFeatured(boolean featured) {
        this.featured = featured;
    }
    
    // Vendor-specific methods
    public void addService(String serviceId) {
        if (this.serviceIds == null) {
            this.serviceIds = new ArrayList<>();
        }
        this.serviceIds.add(serviceId);
    }
    
    public void addAvailableDate(String date) {
        if (this.availability == null) {
            this.availability = new ArrayList<>();
        }
        this.availability.add(date);
    }
    
    public void addReview(Review review) {
        if (this.reviews == null) {
            this.reviews = new ArrayList<>();
        }
        this.reviews.add(review);
        recalculateRating();
    }
    
    public void addPortfolioImage(String imagePath) {
        if (this.portfolioImages == null) {
            this.portfolioImages = new ArrayList<>();
        }
        this.portfolioImages.add(imagePath);
    }
    
    // Calculate average rating from reviews
    private void recalculateRating() {
        if (reviews == null || reviews.isEmpty()) {
            this.rating = 0.0;
            return;
        }
        
        double sum = 0.0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        this.rating = sum / reviews.size();
    }
    
    @Override
    public String toString() {
        return "Vendor{" +
                "vendorId='" + getUserId() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", businessName='" + businessName + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", rating=" + rating +
                ", active=" + isActive() +
                '}';
    }
    
    // Inner class for price range
    public static class PriceRange {
        private int min;
        private int max;
        
        public PriceRange() {
        }
        
        public PriceRange(int min, int max) {
            this.min = min;
            this.max = max;
        }
        
        public int getMin() {
            return min;
        }
        
        public void setMin(int min) {
            this.min = min;
        }
        
        public int getMax() {
            return max;
        }
        
        public void setMax(int max) {
            this.max = max;
        }
        
        @Override
        public String toString() {
            return "$" + min + " - $" + max;
        }
    }
    
    // Inner class for vendor reviews
    public static class Review {
        private String userId;
        private String comment;
        private double rating;
        
        public Review() {
        }
        
        public Review(String userId, String comment, double rating) {
            this.userId = userId;
            this.comment = comment;
            this.rating = rating;
        }
        
        public String getUserId() {
            return userId;
        }
        
        public void setUserId(String userId) {
            this.userId = userId;
        }
        
        public String getComment() {
            return comment;
        }
        
        public void setComment(String comment) {
            this.comment = comment;
        }
        
        public double getRating() {
            return rating;
        }
        
        public void setRating(double rating) {
            this.rating = rating;
        }
    }
}