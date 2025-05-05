package com.weddingplanner.servlet;

import com.weddingplanner.model.Vendor;
import com.weddingplanner.util.FileHandler;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class VendorListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Load all vendors from the file
        List<Vendor> vendors = FileHandler.loadVendors();

        // Set the vendors list as a request attribute to be accessed in JSP
        request.setAttribute("vendors", vendors);

        // Forward the request to a JSP to display the vendors
        request.getRequestDispatcher("/WEB-INF/views/vendorList.jsp").forward(request, response);
    }
}
