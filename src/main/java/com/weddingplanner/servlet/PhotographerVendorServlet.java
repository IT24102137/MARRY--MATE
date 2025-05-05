package com.weddingplanner.servlet;

import com.weddingplanner.model.Vendor;
import com.weddingplanner.util.FileHandler;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class PhotographerVendorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Load photographers from the file (filtering by type "Photographer")
        List<Vendor> photographers = FileHandler.loadVendorsByType("Photographer");

        // Set the photographers list as a request attribute to be accessed in JSP
        request.setAttribute("photographers", photographers);

        // Forward the request to a JSP to display the photographers
        request.getRequestDispatcher("/WEB-INF/views/photographer.jsp").forward(request, response);
    }
}
