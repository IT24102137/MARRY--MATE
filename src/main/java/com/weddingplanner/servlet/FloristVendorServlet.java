package com.weddingplanner.servlet;

import com.weddingplanner.model.Vendor;
import com.weddingplanner.util.FileHandler;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class FloristVendorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Load florists from the file (filtering by type "Florist")
        List<Vendor> florists = FileHandler.loadVendorsByType("Florist");

        // Set the florists list as a request attribute to be accessed in JSP
        request.setAttribute("florists", florists);

        // Forward the request to a JSP to display the florists
        request.getRequestDispatcher("/WEB-INF/views/florist.jsp").forward(request, response);
    }
}

