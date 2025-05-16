package com.marrymate.servlet;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import com.marrymate.model.Booking;
import com.marrymate.model.RefundPolicy;
import com.marrymate.model.PhotographerRefund;
import com.marrymate.model.CatererRefund;
import com.marrymate.model.FloristRefund;

// Servlet annotation to map this class to the /BookingServlet URL
@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String BOOKINGS_FILE = "data/bookings.json";

    /**
     * Handles HTTP GET requests for viewing or canceling bookings.
     * @param request The HttpServletRequest object containing the request parameters
     * @param response The HttpServletResponse object for sending the response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException If an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String bookingsFilePath = getServletContext().getRealPath("/") + BOOKINGS_FILE;

        if ("view".equals(action)) {
            String bookingId = request.getParameter("bookingId");
            Booking booking = findBooking(bookingId, bookingsFilePath);
            if (booking != null) {
                request.setAttribute("booking", booking);
                request.getRequestDispatcher("/viewBooking.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Booking not found.");
                response.sendRedirect("vendor_booking.jsp");
            }
        } else if ("cancel".equals(action)) {
            String bookingId = request.getParameter("bookingId");
            Booking booking = findBooking(bookingId, bookingsFilePath);
            if (booking != null) {
                String vendorType = booking.getVendorType();
                double amount = booking.getAmount();
                int daysBeforeEvent = 10; // Simulated days before event

                // Determine refund policy based on vendor type
                RefundPolicy refundPolicy = null;
                if ("Photographer".equals(vendorType)) {
                    refundPolicy = new PhotographerRefund();
                } else if ("Caterer".equals(vendorType)) {
                    refundPolicy = new CatererRefund();
                } else if ("Florist".equals(vendorType)) {
                    refundPolicy = new FloristRefund();
                }

                double refundAmount = (refundPolicy != null) ? refundPolicy.calculateRefund(amount, daysBeforeEvent) : 0.0;
                booking.setStatus("Cancelled");
                updateBooking(booking, bookingsFilePath);

                request.setAttribute("cancellationMessage", "Booking " + bookingId + " cancelled successfully.");
                request.setAttribute("refundAmount", refundAmount);
                request.getRequestDispatcher("/cancellationConfirmation.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Booking not found.");
                response.sendRedirect("vendor_booking.jsp");
            }
        } else {
            response.sendRedirect("vendor_booking.jsp");
        }
    }

    /**
     * Handles HTTP POST requests for creating a new booking.
     * @param request The HttpServletRequest object containing the request parameters
     * @param response The HttpServletResponse object for sending the response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException If an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String bookingId = request.getParameter("bookingId");
        String vendorType = request.getParameter("vendorType");
        String eventDate = request.getParameter("eventDate");
        String details = request.getParameter("details");
        double amount = Double.parseDouble(request.getParameter("amount"));

        // Create a new booking
        Booking booking = new Booking(bookingId, vendorType, eventDate, details, amount);
        String bookingsFilePath = getServletContext().getRealPath("/") + BOOKINGS_FILE;

        // Read existing bookings
        JSONObject jsonObject = readJsonFile(bookingsFilePath);
        JSONArray bookingsArray = jsonObject.getJSONArray("bookings");
        JSONObject bookingJson = new JSONObject();
        bookingJson.put("bookingId", booking.getBookingId());
        bookingJson.put("vendorType", booking.getVendorType());
        bookingJson.put("eventDate", booking.getEventDate());
        bookingJson.put("details", booking.getDetails());
        bookingJson.put("amount", booking.getAmount());
        bookingJson.put("status", booking.getStatus());
        bookingsArray.put(bookingJson);

        // Write back to file
        writeJsonFile(bookingsFilePath, jsonObject);

        request.setAttribute("message", "Booking " + bookingId + " created successfully!");
        response.sendRedirect("vendor_booking.jsp");
    }

    /**
     * Finds a booking by ID in the JSON file.
     * @param bookingId The ID of the booking to find
     * @param filePath The path to the bookings JSON file
     * @return The Booking object if found, null otherwise
     */
    private Booking findBooking(String bookingId, String filePath) {
        JSONObject jsonObject = readJsonFile(filePath);
        JSONArray bookingsArray = jsonObject.getJSONArray("bookings");
        for (int i = 0; i < bookingsArray.length(); i++) {
            JSONObject bookingJson = bookingsArray.getJSONObject(i);
            if (bookingJson.getString("bookingId").equals(bookingId)) {
                return new Booking(
                    bookingJson.getString("bookingId"),
                    bookingJson.getString("vendorType"),
                    bookingJson.getString("eventDate"),
                    bookingJson.getString("details"),
                    bookingJson.getDouble("amount")
                );
            }
        }
        return null;
    }

    /**
     * Updates a booking in the JSON file.
     * @param booking The Booking object to update
     * @param filePath The path to the bookings JSON file
     */
    private void updateBooking(Booking booking, String filePath) {
        JSONObject jsonObject = readJsonFile(filePath);
        JSONArray bookingsArray = jsonObject.getJSONArray("bookings");
        for (int i = 0; i < bookingsArray.length(); i++) {
            JSONObject bookingJson = bookingsArray.getJSONObject(i);
            if (bookingJson.getString("bookingId").equals(booking.getBookingId())) {
                bookingJson.put("status", booking.getStatus());
                break;
            }
        }
        writeJsonFile(filePath, jsonObject);
    }

    /**
     * Reads the JSON file and returns a JSONObject.
     * @param filePath The path to the JSON file
     * @return The JSONObject containing the file data
     */
    private JSONObject readJsonFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
            return new JSONObject(sb.toString());
        } catch (Exception e) {
            return new JSONObject().put("bookings", new JSONArray());
        }
    }

    /**
     * Writes a JSONObject to the JSON file.
     * @param filePath The path to the JSON file
     * @param jsonObject The JSONObject to write
     */
    private void writeJsonFile(String filePath, JSONObject jsonObject) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonObject.toString(4)); // Pretty print with indentation
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}