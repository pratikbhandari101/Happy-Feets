package com.example.controller;

import com.example.dao.TourPackageDAO;
import com.example.model.TourPackage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/tours/*")
public class TourPackageServlet extends HttpServlet {
    private TourPackageDAO tourPackageDAO;

    @Override
    public void init() throws ServletException {
        tourPackageDAO = new TourPackageDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            // Handle search and filter parameters
            String searchTerm = request.getParameter("search");
            String destination = request.getParameter("destination");
            String minPriceStr = request.getParameter("minPrice");
            String maxPriceStr = request.getParameter("maxPrice");
            String minDurationStr = request.getParameter("minDuration");
            String maxDurationStr = request.getParameter("maxDuration");

            // Convert parameters to appropriate types
            BigDecimal minPrice = null;
            BigDecimal maxPrice = null;
            Integer minDuration = null;
            Integer maxDuration = null;

            try {
                if (minPriceStr != null && !minPriceStr.isEmpty()) {
                    minPrice = new BigDecimal(minPriceStr);
                }
                if (maxPriceStr != null && !maxPriceStr.isEmpty()) {
                    maxPrice = new BigDecimal(maxPriceStr);
                }
                if (minDurationStr != null && !minDurationStr.isEmpty()) {
                    minDuration = Integer.parseInt(minDurationStr);
                }
                if (maxDurationStr != null && !maxDurationStr.isEmpty()) {
                    maxDuration = Integer.parseInt(maxDurationStr);
                }
            } catch (NumberFormatException e) {
                // Handle invalid number format
                request.setAttribute("error", "Invalid number format in filter parameters");
            }

            // Get filtered tour packages
            List<TourPackage> tourPackages = tourPackageDAO.searchTourPackages(
                searchTerm, destination, minPrice, maxPrice, minDuration, maxDuration);

            // Set attributes for the JSP
            request.setAttribute("tourPackages", tourPackages);
            request.setAttribute("searchTerm", searchTerm);
            request.setAttribute("destination", destination);
            request.setAttribute("minPrice", minPriceStr);
            request.setAttribute("maxPrice", maxPriceStr);
            request.setAttribute("minDuration", minDurationStr);
            request.setAttribute("maxDuration", maxDurationStr);

            // Forward to the tours JSP
            request.getRequestDispatcher("/WEB-INF/pages/tours.jsp").forward(request, response);
        } else if (pathInfo.startsWith("/view/")) {
            // Handle viewing a specific tour package
            try {
                int packageId = Integer.parseInt(pathInfo.substring(6));
                TourPackage tourPackage = tourPackageDAO.getTourPackageById(packageId);
                
                if (tourPackage != null) {
                    request.setAttribute("tourPackage", tourPackage);
                    request.getRequestDispatcher("/WEB-INF/pages/package-details.jsp")
                           .forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Tour package not found");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid tour package ID");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
} 