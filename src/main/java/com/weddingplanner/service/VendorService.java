package com.weddingplanner.service;

import com.weddingplanner.model.*;
import com.weddingplanner.util.FileHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorService {

    // Create a new vendor
    public boolean createVendor(Vendor vendor) {
        try {
            FileHandler.saveVendor(vendor);
            return true;
        } catch (IOException e) {
            System.err.println("Error creating vendor: " + e.getMessage());
            return false;
        }
    }

    // Get all vendors
    public List<Vendor> getAllVendors() {
        try {
            return FileHandler.loadVendors();
        } catch (IOException e) {
            System.err.println("Error loading vendors: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Get vendor by ID
    public Vendor getVendorById(String id) {
        try {
            return FileHandler.loadVendors().stream()
                    .filter(v -> v.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            System.err.println("Error finding vendor: " + e.getMessage());
            return null;
        }
    }

    // Update existing vendor
    public boolean updateVendor(Vendor vendor) {
        try {
            Vendor existingVendor = getVendorById(vendor.getId());
            if (existingVendor == null) {
                return false;
            }

            FileHandler.saveVendor(vendor);
            return true;
        } catch (IOException e) {
            System.err.println("Error updating vendor: " + e.getMessage());
            return false;
        }
    }

    // Delete vendor by ID
    public boolean deleteVendor(String id) {
        try {
            return FileHandler.deleteVendor(id);
        } catch (IOException e) {
            System.err.println("Error deleting vendor: " + e.getMessage());
            return false;
        }
    }

    // Search vendors by type
    public List<Vendor> searchVendorsByType(String vendorType) {
        try {
            return FileHandler.loadVendors().stream()
                    .filter(v -> v.getVendorType().equalsIgnoreCase(vendorType))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error searching vendors: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Search vendors by location
    public List<Vendor> searchVendorsByLocation(String location) {
        try {
            return FileHandler.loadVendors().stream()
                    .filter(v -> v.getLocation().toLowerCase().contains(location.toLowerCase()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error searching vendors: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Create specific vendor types with proper type casting
    public Vendor createVendorByType(String vendorType, Vendor baseVendor) {
        Vendor newVendor;

        switch (vendorType) {
            case "Photographer":
                newVendor = new PhotographerVendor();
                break;
            case "Caterer":
                newVendor = new CatererVendor();
                break;
            case "Florist":
                newVendor = new FloristVendor();
                break;
            default:
                newVendor = new Vendor();
                break;
        }

        // Copy common properties
        newVendor.setName(baseVendor.getName());
        newVendor.setEmail(baseVendor.getEmail());
        newVendor.setPhone(baseVendor.getPhone());
        newVendor.setLocation(baseVendor.getLocation());
        newVendor.setDescription(baseVendor.getDescription());
        newVendor.setPriceRange(baseVendor.getPriceRange());
        newVendor.setVendorType(vendorType);

        return newVendor;
    }
}