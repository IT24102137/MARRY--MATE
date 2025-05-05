package com.weddingplanner.servlet;

import com.weddingplanner.model.Vendor;
import com.weddingplanner.util.FileHandler;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class CatererVendorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Load caterers from the file (filtering by type "Caterer")
        List<Vendor> caterers = FileHandler.loadVendorsByType("Caterer");

        // Set the caterers list as a request attribute to be accessed in JSP
        request.setAttribute("caterers", caterers);

        // Forward the request to a JSP to display the caterers
        request.getRequestDispatcher("/WEB-INF/views/caterer.jsp").forward(request, response);
    }
}

