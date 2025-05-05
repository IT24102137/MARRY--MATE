package com.weddingplanner.util;

import com.weddingplanner.model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String DATA_DIRECTORY = "data";
    private static final String VENDORS_FILE = DATA_DIRECTORY + "/vendors.txt";

    // Ensure data directory exists
    static {
        File directory = new File(DATA_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    // Save a list of vendors to file
    public static void saveVendors(List<Vendor> vendors) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VENDORS_FILE))) {
            for (Vendor vendor : vendors) {
                writer.write(serializeVendor(vendor));
                writer.newLine();
            }
        }
    }

    // Load all vendors from file
    public static List<Vendor> loadVendors() throws IOException {
        List<Vendor> vendors = new ArrayList<>();
        File file = new File(VENDORS_FILE);

        if (!file.exists()) {
            return vendors; // Return empty list if file doesn't exist yet
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Vendor vendor = deserializeVendor(line);
                    if (vendor != null) {
                        vendors.add(vendor);
                    }
                }
            }
        }

        return vendors;
    }

    // Load vendors filtered by type
    public static List<Vendor> loadVendorsByType(String type) throws IOException {
        List<Vendor> vendors = loadVendors(); // Load all vendors from the file
        List<Vendor> filteredVendors = new ArrayList<>();

        // Filter the vendors by their type
        for (Vendor vendor : vendors) {
            if (vendor.getVendorType().equalsIgnoreCase(type)) {
                filteredVendors.add(vendor); // Add the vendor to the list if type matches
            }
        }

        return filteredVendors;
    }

    // Save a single vendor (appends to file)
    public static void saveVendor(Vendor vendor) throws IOException {
        List<Vendor> existingVendors = loadVendors();

        // Check if vendor already exists (update)
        boolean updated = false;
        for (int i = 0; i < existingVendors.size(); i++) {
            if (existingVendors.get(i).getId().equals(vendor.getId())) {
                existingVendors.set(i, vendor);
                updated = true;
                break;
            }
        }

        // If not updated, add as new
        if (!updated) {
            existingVendors.add(vendor);
        }

        saveVendors(existingVendors);
    }

    // Delete a vendor by ID
    public static boolean deleteVendor(String vendorId) throws IOException {
        List<Vendor> vendors = loadVendors();
        boolean removed = vendors.removeIf(v -> v.getId().equals(vendorId));

        if (removed) {
            saveVendors(vendors);
        }

        return removed;
    }

    // Convert vendor object to string for file storage
    private static String serializeVendor(Vendor vendor) {
        return vendor.toString();
    }

    // Convert string from file back to vendor object
    private static Vendor deserializeVendor(String line) {
        try {
            String[] parts = line.split(",");

            if (parts.length < 8) {
                System.err.println("Invalid vendor data format: " + line);
                return null;
            }

            String id = parts[0];
            String name = parts[1];
            String email = parts[2];
            String phone = parts[3];
            String location = parts[4];
            String description = parts[5];
            double priceRange = Double.parseDouble(parts[6]);
            String vendorType = parts[7];

            Vendor vendor;

            switch (vendorType) {
                case "Photographer":
                    if (parts.length < 11) {
                        System.err.println("Invalid photographer data format: " + line);
                        return null;
                    }
                    vendor = new PhotographerVendor();
                    ((PhotographerVendor) vendor).setPhotographyStyle(parts[8]);
                    ((PhotographerVendor) vendor).setYearsOfExperience(Integer.parseInt(parts[9]));
                    ((PhotographerVendor) vendor).setProvidesVideography(Boolean.parseBoolean(parts[10]));
                    break;

                case "Caterer":
                    if (parts.length < 11) {
                        System.err.println("Invalid caterer data format: " + line);
                        return null;
                    }
                    vendor = new CatererVendor();
                    ((CatererVendor) vendor).setCuisineType(parts[8]);
                    ((CatererVendor) vendor).setProvidesWaiterService(Boolean.parseBoolean(parts[9]));
                    ((CatererVendor) vendor).setMaxCapacity(Integer.parseInt(parts[10]));
                    break;

                case "Florist":
                    if (parts.length < 11) {
                        System.err.println("Invalid florist data format: " + line);
                        return null;
                    }
                    vendor = new FloristVendor();
                    ((FloristVendor) vendor).setSpecialization(parts[8]);
                    ((FloristVendor) vendor).setProvidesSetupService(Boolean.parseBoolean(parts[9]));
                    ((FloristVendor) vendor).setProvidesConsultation(Boolean.parseBoolean(parts[10]));
                    break;

                default:
                    vendor = new Vendor();
                    break;
            }

            vendor.setId(id);
            vendor.setName(name);
            vendor.setEmail(email);
            vendor.setPhone(phone);
            vendor.setLocation(location);
            vendor.setDescription(description);
            vendor.setPriceRange(priceRange);
            vendor.setVendorType(vendorType);

            return vendor;
        } catch (Exception e) {
            System.err.println("Error deserializing vendor: " + e.getMessage());
            return null;
        }
    }
}
