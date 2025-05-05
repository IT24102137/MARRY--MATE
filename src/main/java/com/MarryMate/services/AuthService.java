package com.MarryMate.services;

import com.MarryMate.models.User;
import com.MarryMate.models.RegularUser;
import com.MarryMate.models.Vendor;
import com.MarryMate.models.Admin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletContext;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Authentication and User Management Service for Marry Mate Wedding Planning System
 * 
 * Handles user authentication, registration, and JSON data operations
 * 
 * Current Date and Time: 2025-05-05 05:57:17
 * Current User: IT24102137
 */
public class AuthService {
    
    // File paths for JSON data storage
    private static final String USERS_FILE_PATH = "H:\\SLIIT\\Sem 2\\OOP\\Project\\Eclipse\\MarryMate-FinalMyPart\\src\\main\\webapp\\WEB-INF\\data\\users.json";
    private static final String VENDORS_FILE_PATH = "H:\\SLIIT\\Sem 2\\OOP\\Project\\Eclipse\\MarryMate-FinalMyPart\\src\\main\\webapp\\WEB-INF\\data\\vendors.json";
    private static final String ADMINS_FILE_PATH = "H:\\SLIIT\\Sem 2\\OOP\\Project\\Eclipse\\MarryMate-FinalMyPart\\src\\main\\webapp\\WEB-INF\\data\\admins.json";
    
    // LinkedList for user data storage
    private LinkedList<RegularUser> regularUsers;
    private LinkedList<Vendor> vendors;
    private LinkedList<Admin> admins;
    
    // GSON for JSON serialization/deserialization
    private Gson gson;
    
    // ServletContext for file operations
    private ServletContext servletContext;
    
    // Singleton instance
    private static AuthService instance;
    
    /**
     * Get the singleton instance of AuthService
     * @param servletContext ServletContext for file operations
     * @return AuthService instance
     */
    public static synchronized AuthService getInstance(ServletContext servletContext) {
        if (instance == null) {
            instance = new AuthService(servletContext);
        }
        return instance;
    }
    
    /**
     * Private constructor to enforce singleton pattern
     * @param servletContext ServletContext for file operations
     */
    private AuthService(ServletContext servletContext) {
        this.servletContext = servletContext;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        
        // Initialize data structures
        regularUsers = new LinkedList<>();
        vendors = new LinkedList<>();
        admins = new LinkedList<>();
        
        // Load data from JSON files
        loadData();
    }
    
    /**
     * Load all user data from JSON files
     */
    private void loadData() {
        // Load regular users
        regularUsers = loadRegularUsers();
        
        // Load vendors
        vendors = loadVendors();
        
        // Load admins
        admins = loadAdmins();
    }
    
    /**
     * Load regular users from JSON file
     * @return LinkedList of RegularUser objects
     */
    private LinkedList<RegularUser> loadRegularUsers() {
        LinkedList<RegularUser> users = new LinkedList<>();
        
        try {
            // Get the file path
            String fullPath = USERS_FILE_PATH;
            
            // Check if file exists, if not create an empty file
            File file = new File(fullPath);
            if (!file.exists()) {
                createEmptyUsersFile(fullPath);
            }
            
            // Read the JSON file
            String content = new String(Files.readAllBytes(Paths.get(fullPath)));
            
            // Parse JSON file
            JsonObject jsonObject = JsonParser.parseString(content).getAsJsonObject();
            JsonArray usersArray = jsonObject.getAsJsonArray("users");
            
            // Convert each JSON object to RegularUser object
            for (JsonElement element : usersArray) {
                JsonObject userJson = element.getAsJsonObject();
                
                // Only add users with role "user"
                if ("user".equals(userJson.get("role").getAsString())) {
                    RegularUser user = gson.fromJson(userJson, RegularUser.class);
                    users.add(user);
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading users from JSON: " + e.getMessage());
            e.printStackTrace();
            // Create empty list if file not found or error
            users = new LinkedList<>();
        }
        
        return users;
    }
    
    /**
     * Create empty users JSON file with structure
     * @param filePath Path to create the file
     */
    private void createEmptyUsersFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Create parent directories if needed
        
        JsonObject root = new JsonObject();
        root.add("users", new JsonArray());
        
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(gson.toJson(root));
        }
    }
    
    /**
     * Load vendors from JSON file
     * @return LinkedList of Vendor objects
     */
    private LinkedList<Vendor> loadVendors() {
        LinkedList<Vendor> vendorList = new LinkedList<>();
        
        try {
            // Get the file path
            String fullPath = VENDORS_FILE_PATH;
            
            // Check if file exists, if not create it
            File file = new File(fullPath);
            if (!file.exists()) {
                JsonObject root = new JsonObject();
                root.add("vendors", new JsonArray());
                
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(gson.toJson(root));
                }
            }
            
            // Read the JSON file
            String content = new String(Files.readAllBytes(Paths.get(fullPath)));
            
            // Parse JSON file
            JsonObject jsonObject = JsonParser.parseString(content).getAsJsonObject();
            JsonArray vendorsArray = jsonObject.getAsJsonArray("vendors");
            
            // Convert each JSON object to Vendor object
            for (JsonElement element : vendorsArray) {
                Vendor vendor = gson.fromJson(element, Vendor.class);
                vendorList.add(vendor);
            }
        } catch (Exception e) {
            System.err.println("Error loading vendors from JSON: " + e.getMessage());
            e.printStackTrace();
            // Create empty list if file not found or error
            vendorList = new LinkedList<>();
        }
        
        return vendorList;
    }
    
    /**
     * Load admins from JSON file
     * @return LinkedList of Admin objects
     */
    private LinkedList<Admin> loadAdmins() {
        LinkedList<Admin> adminList = new LinkedList<>();
        
        try {
            // Get the file path
            String fullPath = ADMINS_FILE_PATH;
            
            // Check if file exists, if not create it
            File file = new File(fullPath);
            if (!file.exists()) {
                JsonObject root = new JsonObject();
                root.add("admins", new JsonArray());
                
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(gson.toJson(root));
                }
            }
            
            // Read the JSON file
            String content = new String(Files.readAllBytes(Paths.get(fullPath)));
            
            // Parse JSON file
            JsonObject jsonObject = JsonParser.parseString(content).getAsJsonObject();
            JsonArray adminsArray = jsonObject.getAsJsonArray("admins");
            
            // Convert each JSON object to Admin object
            for (JsonElement element : adminsArray) {
                Admin admin = gson.fromJson(element, Admin.class);
                adminList.add(admin);
            }
        } catch (Exception e) {
            System.err.println("Error loading admins from JSON: " + e.getMessage());
            e.printStackTrace();
            // Create empty list if file not found or error
            adminList = new LinkedList<>();
        }
        
        return adminList;
    }
    
    /**
     * Save regular users to JSON file
     */
    private void saveRegularUsers() {
        try {
            // Get the file path
            String fullPath = USERS_FILE_PATH;
            
            File file = new File(fullPath);
            if (!file.exists()) {
                createEmptyUsersFile(fullPath);
            }
            
            // Read the current file to preserve structure
            String content = new String(Files.readAllBytes(Paths.get(fullPath)));
            JsonObject jsonObject;
            try {
                jsonObject = JsonParser.parseString(content).getAsJsonObject();
            } catch (Exception e) {
                // If parsing fails, create new structure
                jsonObject = new JsonObject();
            }
            
            // Create a new users array
            JsonArray usersArray = new JsonArray();
            
            // Add regular users
            for (RegularUser user : regularUsers) {
                JsonElement userJson = gson.toJsonTree(user);
                usersArray.add(userJson);
            }
            
            // Replace users array in the root object
            jsonObject.add("users", usersArray);
            
            // Write updated JSON back to file
            try (FileWriter writer = new FileWriter(fullPath)) {
                writer.write(gson.toJson(jsonObject));
            }
        } catch (Exception e) {
            System.err.println("Error saving users to JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Save vendors to JSON file
     */
    private void saveVendors() {
        try {
            // Get the file path
            String fullPath = VENDORS_FILE_PATH;
            
            File file = new File(fullPath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                JsonObject root = new JsonObject();
                root.add("vendors", new JsonArray());
                
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(gson.toJson(root));
                }
            }
            
            // Read the current file to preserve structure
            String content = new String(Files.readAllBytes(Paths.get(fullPath)));
            JsonObject jsonObject;
            try {
                jsonObject = JsonParser.parseString(content).getAsJsonObject();
            } catch (Exception e) {
                // If parsing fails, create new structure
                jsonObject = new JsonObject();
            }
            
            // Create a new vendors array
            JsonArray vendorsArray = new JsonArray();
            
            // Add vendors
            for (Vendor vendor : vendors) {
                JsonElement vendorJson = gson.toJsonTree(vendor);
                vendorsArray.add(vendorJson);
            }
            
            // Replace vendors array in the root object
            jsonObject.add("vendors", vendorsArray);
            
            // Write updated JSON back to file
            try (FileWriter writer = new FileWriter(fullPath)) {
                writer.write(gson.toJson(jsonObject));
            }
        } catch (Exception e) {
            System.err.println("Error saving vendors to JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Authenticate a user by username/email and password
     * @param usernameOrEmail Username or email to check
     * @param password Password to check
     * @return Optional containing the authenticated User object if successful, empty otherwise
     */
    public Optional<User> authenticateUser(String usernameOrEmail, String password) {
        // Check regular users
        for (RegularUser user : regularUsers) {
            if ((user.getUsername().equals(usernameOrEmail) || user.getEmail().equals(usernameOrEmail))) {
                // Check if password is correct
                if (user.authenticate(password)) {
                    // Check account status
                    if ("active".equals(user.getAccountStatus())) {
                        // Reset failed login attempts
                        user.resetFailedLoginAttempts();
                        // Update last login
                        user.setLastLogin(getCurrentDateTime());
                        // Save updates
                        saveRegularUsers();
                        return Optional.of(user);
                    }
                } else {
                    // Incorrect password - increment failed attempts
                    user.incrementFailedLoginAttempts();
                    
                    // If too many failed attempts, lock the account
                    if (user.getFailedLoginAttempts() >= 5) {
                        user.setAccountStatus("suspended");
                    }
                    
                    saveRegularUsers();
                }
                
                // Return empty for non-active accounts or wrong password
                return Optional.empty();
            }
        }
        
        // Check vendors
        for (Vendor vendor : vendors) {
            if ((vendor.getUsername().equals(usernameOrEmail) || vendor.getEmail().equals(usernameOrEmail))) {
                if (vendor.authenticate(password)) {
                    if ("active".equals(vendor.getAccountStatus())) {
                        vendor.resetFailedLoginAttempts();
                        vendor.setLastLogin(getCurrentDateTime());
                        saveVendors();
                        return Optional.of(vendor);
                    }
                } else {
                    vendor.incrementFailedLoginAttempts();
                    if (vendor.getFailedLoginAttempts() >= 5) {
                        vendor.setAccountStatus("suspended");
                    }
                    saveVendors();
                }
                
                return Optional.empty();
            }
        }
        
        // Check admins
        for (Admin admin : admins) {
            if ((admin.getUsername().equals(usernameOrEmail) || admin.getEmail().equals(usernameOrEmail))) {
                if (admin.authenticate(password)) {
                    if ("active".equals(admin.getAccountStatus())) {
                        admin.resetFailedLoginAttempts();
                        admin.setLastLogin(getCurrentDateTime());
                        return Optional.of(admin);
                    }
                }
                
                return Optional.empty();
            }
        }
        
        // No matching user found
        return Optional.empty();
    }
    
    /**
     * Get account status message based on status
     * @param accountStatus The account status string
     * @return Message to display to the user
     */
    public String getAccountStatusMessage(String accountStatus) {
        switch (accountStatus) {
            case "inactive":
                return "Your account is inactive. Please complete your registration or contact support.";
            case "suspended":
                return "Your account has been suspended. Please contact support for assistance.";
            case "pending":
                return "Your account is awaiting admin approval. Please check back later.";
            default:
                return "Invalid username or password. Please try again.";
        }
    }
    
    /**
     * Check account status without authentication
     * @param usernameOrEmail Username or email to check
     * @return Account status or null if user not found
     */
    public String checkAccountStatus(String usernameOrEmail) {
        // Check regular users
        for (RegularUser user : regularUsers) {
            if (user.getUsername().equals(usernameOrEmail) || user.getEmail().equals(usernameOrEmail)) {
                return user.getAccountStatus();
            }
        }
        
        // Check vendors
        for (Vendor vendor : vendors) {
            if (vendor.getUsername().equals(usernameOrEmail) || vendor.getEmail().equals(usernameOrEmail)) {
                return vendor.getAccountStatus();
            }
        }
        
        // Check admins
        for (Admin admin : admins) {
            if (admin.getUsername().equals(usernameOrEmail) || admin.getEmail().equals(usernameOrEmail)) {
                return admin.getAccountStatus();
            }
        }
        
        // No matching user found
        return null;
    }
    
    /**
     * Register a new regular user with all fields
     * @param username Username for new user
     * @param password Password for new user
     * @param email Email for new user
     * @param fullName Full name for new user
     * @param phoneNumber Phone number for new user
     * @param address Address for new user
     * @return Optional containing the new RegularUser if registration successful, empty otherwise
     */
    public Optional<RegularUser> registerUser(String username, String password, String email, 
                                             String fullName, String phoneNumber, String address) {
        // Check if username or email already exists
        if (isUsernameExists(username)) {
            return Optional.empty();
        }
        
        if (isEmailExists(email)) {
            return Optional.empty();
        }
        
        // Generate new user ID
        String userId = generateNextUserId();
        
        // Create new user with all fields
        RegularUser newUser = new RegularUser(userId, username, password, email, fullName, phoneNumber, address);
        newUser.setRegistrationDate(getCurrentDateTime());
        newUser.setLastLogin(getCurrentDateTime());
        newUser.setAccountStatus("active");
        
        // Add to list
        regularUsers.add(newUser);
        
        // Save to file
        saveRegularUsers();
        
        return Optional.of(newUser);
    }
    
    /**
     * Register a new regular user with basic fields
     * @param username Username for new user
     * @param password Password for new user
     * @param email Email for new user
     * @param fullName Full name for new user
     * @param phoneNumber Phone number for new user
     * @return Optional containing the new RegularUser if registration successful, empty otherwise
     */
    public Optional<RegularUser> registerUser(String username, String password, String email, 
                                             String fullName, String phoneNumber) {
        // Use the full method with empty address
        return registerUser(username, password, email, fullName, phoneNumber, "");
    }
    
    /**
     * Register a new vendor
     * @param username Username for new vendor
     * @param password Password for new vendor
     * @param email Email for new vendor
     * @param businessName Business name for new vendor
     * @param contactPerson Contact person for new vendor
     * @param phoneNumber Phone number for new vendor
     * @return Optional containing the new Vendor if registration successful, empty otherwise
     */
    public Optional<Vendor> registerVendor(String username, String password, String email, 
                                          String businessName, String contactPerson, String phoneNumber) {
        // Check if username or email already exists
        if (isUsernameExists(username)) {
            return Optional.empty();
        }
        
        if (isEmailExists(email)) {
            return Optional.empty();
        }
        
        // Generate new vendor ID
        String vendorId = generateNextVendorId();
        
        // Create new vendor
        Vendor newVendor = new Vendor(vendorId, username, password, email, businessName, contactPerson, phoneNumber);
        newVendor.setRegistrationDate(getCurrentDateTime());
        newVendor.setLastLogin(getCurrentDateTime());
        newVendor.setAccountStatus("pending"); // Vendors need approval
        
        // Add to list
        vendors.add(newVendor);
        
        // Save to file
        saveVendors();
        
        return Optional.of(newVendor);
    }
    
    /**
     * Check if username already exists across all user types
     * @param username Username to check
     * @return true if username exists, false otherwise
     */
    public boolean isUsernameExists(String username) {
        // Check regular users
        for (RegularUser user : regularUsers) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        
        // Check vendors
        for (Vendor vendor : vendors) {
            if (vendor.getUsername().equals(username)) {
                return true;
            }
        }
        
        // Check admins
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                return true;
            }
        }
        
        // Username not found
        return false;
    }
    
    /**
     * Check if email already exists across all user types
     * @param email Email to check
     * @return true if email exists, false otherwise
     */
    public boolean isEmailExists(String email) {
        // Check regular users
        for (RegularUser user : regularUsers) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        
        // Check vendors
        for (Vendor vendor : vendors) {
            if (vendor.getEmail().equals(email)) {
                return true;
            }
        }
        
        // Check admins
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email)) {
                return true;
            }
        }
        
        // Email not found
        return false;
    }
    
    /**
     * Generate next user ID
     * @return String containing the next user ID (e.g. U1006)
     */
    private String generateNextUserId() {
        // Find the highest user ID
        int maxId = 1000;
        
        for (RegularUser user : regularUsers) {
            String userId = user.getUserId();
            if (userId != null && userId.startsWith("U")) {
                try {
                    int id = Integer.parseInt(userId.substring(1));
                    if (id > maxId) {
                        maxId = id;
                    }
                } catch (NumberFormatException e) {
                    // Ignore and continue
                }
            }
        }
        
        // Return next ID
        return "U" + (maxId + 1);
    }
    
    /**
     * Generate next vendor ID
     * @return String containing the next vendor ID (e.g. V1006)
     */
    private String generateNextVendorId() {
        // Find the highest vendor ID
        int maxId = 1000;
        
        for (Vendor vendor : vendors) {
            String vendorId = vendor.getUserId();
            if (vendorId != null && vendorId.startsWith("V")) {
                try {
                    int id = Integer.parseInt(vendorId.substring(1));
                    if (id > maxId) {
                        maxId = id;
                    }
                } catch (NumberFormatException e) {
                    // Ignore and continue
                }
            }
        }
        
        // Return next ID
        return "V" + (maxId + 1);
    }
    
    /**
     * Get current date and time in the specified format
     * @return String containing formatted date and time
     */
    private String getCurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}