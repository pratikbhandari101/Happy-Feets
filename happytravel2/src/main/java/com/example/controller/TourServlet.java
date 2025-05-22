package com.example.controller;

import com.example.dao.TourPackageDAO;
import com.example.model.TourPackage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/tours")
public class TourServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TourPackageDAO tourPackageDAO;

    public void init() {
        tourPackageDAO = new TourPackageDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        
        if (action == null || action.equals("/")) {
            // Handle listing all packages
            List<TourPackage> tourPackages = tourPackageDAO.getAllTourPackages();
            request.setAttribute("tourPackages", tourPackages);
            request.getRequestDispatcher("/WEB-INF/pages/tours.jsp").forward(request, response);
        } else if (action.equals("/details")) {
            // Handle viewing package details
            String packageIdParam = request.getParameter("id");
            if (packageIdParam != null) {
                try {
                    int packageId = Integer.parseInt(packageIdParam);
                    TourPackage pkg = tourPackageDAO.getTourPackageById(packageId);
                    if (pkg != null) {
                        request.setAttribute("tourPackage", pkg);
                        request.getRequestDispatcher("/WEB-INF/pages/package-details.jsp").forward(request, response);
                    } else {
                        // Package not found
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Tour package not found");
                    }
                } catch (NumberFormatException e) {
                    // Invalid package ID format
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid package ID format");
                }
            } else {
                // No package ID provided
                 response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Package ID not provided");
            }
        } else {
            // Unknown action
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: Handle other requests like search/filter if needed
        doGet(request, response); // For now, just call doGet
    }
} 