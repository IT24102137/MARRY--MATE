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
import com.marrymate.model.BankTransferPayment;
import com.marrymate.model.CreditCardPayment;
import com.marrymate.model.PayPalPayment;

// Servlet annotation to map this class to the /PaymentServlet URL
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String PAYMENTS_FILE = "data/payments.json";

    /**
     * Handles HTTP GET requests for checking payment status.
     * @param request The HttpServletRequest object containing the request parameters
     * @param response The HttpServletResponse object for sending the response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException If an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String paymentsFilePath = getServletContext().getRealPath("/") + PAYMENTS_FILE;

        if ("status".equals(action)) {
            String bookingId = request.getParameter("bookingId");
            JSONObject payment = findPayment(bookingId, paymentsFilePath);
            if (payment != null) {
                request.setAttribute("paymentStatus", payment.getString("status") + " for Booking " + bookingId);
            } else {
                request.setAttribute("paymentStatus", "No payment found for Booking " + bookingId);
            }
            request.getRequestDispatcher("/paymentStatus.jsp").forward(request, response);
        } else {
            response.sendRedirect("payment.jsp");
        }
    }

    /**
     * Handles HTTP POST requests for processing a payment.
     * @param request The HttpServletRequest object containing the request parameters
     * @param response The HttpServletResponse object for sending the response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException If an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String bookingId = request.getParameter("bookingId");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String paymentMethod = request.getParameter("paymentMethod");
        String paymentsFilePath = getServletContext().getRealPath("/") + PAYMENTS_FILE;

        // Process payment based on the selected method
        boolean paymentSuccess = false;
        String transactionId = "TXN" + bookingId;
        String status = "Failed";

        if ("creditCard".equals(paymentMethod)) {
            String cardNumber = request.getParameter("cardNumber");
            String expiryDate = request.getParameter("expiryDate");
            String cvv = request.getParameter("cvv");
            CreditCardPayment payment = new CreditCardPayment(bookingId, amount, cardNumber, expiryDate, cvv);
            paymentSuccess = payment.processPayment();
            status = payment.getStatus();
        } else if ("paypal".equals(paymentMethod)) {
            String paypalEmail = request.getParameter("paypalEmail");
            PayPalPayment payment = new PayPalPayment(bookingId, amount, paypalEmail);
            paymentSuccess = payment.processPayment();
            status = payment.getStatus();
        } else if ("bankTransfer".equals(paymentMethod)) {
            String accountNumber = request.getParameter("accountNumber");
            String routingNumber = request.getParameter("routingNumber");
            BankTransferPayment payment = new BankTransferPayment(bookingId, amount, accountNumber, routingNumber);
            paymentSuccess = payment.processPayment();
            status = payment.getStatus();
        }

        // Save payment details to JSON
        JSONObject jsonObject = readJsonFile(paymentsFilePath);
        JSONArray paymentsArray = jsonObject.getJSONArray("payments");
        JSONObject paymentJson = new JSONObject();
        paymentJson.put("bookingId", bookingId);
        paymentJson.put("amount", amount);
        paymentJson.put("paymentMethod", paymentMethod);
        paymentJson.put("transactionId", transactionId);
        paymentJson.put("status", status);
        paymentsArray.put(paymentJson);
        writeJsonFile(paymentsFilePath, jsonObject);

        if (paymentSuccess) {
            request.setAttribute("transactionId", transactionId);
            request.setAttribute("amount", amount);
            request.getRequestDispatcher("/paymentConfirmation.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Payment failed. Please check your details.");
            request.getRequestDispatcher("/payment.jsp").forward(request, response);
        }
    }

    /**
     * Finds a payment by booking ID in the JSON file.
     * @param bookingId The ID of the booking to find the payment for
     * @param filePath The path to the payments JSON file
     * @return The JSONObject containing the payment if found, null otherwise
     */
    private JSONObject findPayment(String bookingId, String filePath) {
        JSONObject jsonObject = readJsonFile(filePath);
        JSONArray paymentsArray = jsonObject.getJSONArray("payments");
        for (int i = 0; i < paymentsArray.length(); i++) {
            JSONObject paymentJson = paymentsArray.getJSONObject(i);
            if (paymentJson.getString("bookingId").equals(bookingId)) {
                return paymentJson;
            }
        }
        return null;
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
            return new JSONObject().put("payments", new JSONArray());
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