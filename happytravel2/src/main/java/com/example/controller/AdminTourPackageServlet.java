package com.example.controller;

import com.example.dao.TourPackageDAO;
import com.example.model.TourPackage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@WebServlet("/admin/tours/*")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 10,  // 10 MB
    maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
public class AdminTourPackageServlet extends HttpServlet {
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
            // List all tour packages
            List<TourPackage> tourPackages = tourPackageDAO.getAllTourPackages();
            request.setAttribute("tourPackages", tourPackages);
            request.getRequestDispatcher("/WEB-INF/pages/admin/tour-list.jsp")
                   .forward(request, response);
        } else if (pathInfo.equals("/add")) {
            // Show add form
            request.getRequestDispatcher("/WEB-INF/pages/admin/tour-form.jsp")
                   .forward(request, response);
        } else if (pathInfo.startsWith("/edit/")) {
            // Show edit form
            try {
                int packageId = Integer.parseInt(pathInfo.substring(6));
                TourPackage tourPackage = tourPackageDAO.getTourPackageById(packageId);
                
                if (tourPackage != null) {
                    request.setAttribute("tourPackage", tourPackage);
                    request.getRequestDispatcher("/WEB-INF/pages/admin/tour-form.jsp")
                           .forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Tour package not found");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid tour package ID");
            }
        } else if (pathInfo.startsWith("/delete/")) {
            // Handle delete
            try {
                int packageId = Integer.parseInt(pathInfo.substring(8));
                if (tourPackageDAO.deleteTourPackage(packageId)) {
                    response.sendRedirect(request.getContextPath() + "/admin/tours");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                                     "Failed to delete tour package");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid tour package ID");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            // Handle form submission for add/edit
            String packageIdStr = request.getParameter("packageId");
            String packageName = request.getParameter("packageName");
            String destination = request.getParameter("destination");
            String description = request.getParameter("description");
            String priceStr = request.getParameter("price");
            String durationStr = request.getParameter("duration");
            
            // Handle file upload
            Part filePart = request.getPart("image");
            String imagePath = null;
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
                String uploadPath = getServletContext().getRealPath("/resources/images/");
                filePart.write(uploadPath + fileName);
                imagePath = fileName;
            }

            try {
                TourPackage tourPackage = new TourPackage();
                if (packageIdStr != null && !packageIdStr.isEmpty()) {
                    tourPackage.setPackageId(Integer.parseInt(packageIdStr));
                }
                tourPackage.setName(packageName);
                tourPackage.setLocation(destination);
                tourPackage.setDescription(description);
                tourPackage.setPrice(new BigDecimal(priceStr));
                tourPackage.setDuration(Integer.parseInt(durationStr));
                
                // Set default or placeholder values for new fields from DB schema
                tourPackage.setMaxCapacity(0); // Default value, update form/logic later
                tourPackage.setAvailableSeats(0); // Default value, update form/logic later
                tourPackage.setStatus("active"); // Default status, update form/logic later
                
                if (imagePath != null) {
                    tourPackage.setImageUrl(imagePath);
                } else if (packageIdStr != null && !packageIdStr.isEmpty()) {
                    // If updating and no new image is uploaded, keep the old image path
                    TourPackage existingPackage = tourPackageDAO.getTourPackageById(Integer.parseInt(packageIdStr));
                    if (existingPackage != null) {
                        tourPackage.setImageUrl(existingPackage.getImageUrl());
                    }
                }

                boolean success;
                if (packageIdStr != null && !packageIdStr.isEmpty()) {
                    success = tourPackageDAO.updateTourPackage(tourPackage);
                } else {
                    success = tourPackageDAO.addTourPackage(tourPackage);
                }

                if (success) {
                    response.sendRedirect(request.getContextPath() + "/admin/tours");
                } else {
                    request.setAttribute("error", "Failed to save tour package");
                    request.setAttribute("tourPackage", tourPackage);
                    request.getRequestDispatcher("/WEB-INF/pages/admin/tour-form.jsp")
                           .forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Invalid input data: " + e.getMessage());
                
                // Attempt to reconstruct tourPackage from request parameters to repopulate form
                TourPackage tourPackage = new TourPackage();
                try {
                    if (packageIdStr != null && !packageIdStr.isEmpty()) {
                        tourPackage.setPackageId(Integer.parseInt(packageIdStr));
                    }
                    tourPackage.setName(packageName);
                    tourPackage.setLocation(destination);
                    tourPackage.setDescription(description);
                    tourPackage.setPrice(new BigDecimal(priceStr));
                    tourPackage.setDuration(Integer.parseInt(durationStr));
                    
                    // Set default or placeholder values for new fields
                    tourPackage.setMaxCapacity(0);
                    tourPackage.setAvailableSeats(0);
                    tourPackage.setStatus("active");
                } catch (Exception ex) {
                    ex.printStackTrace(); // Log reconstruction errors
                }
                request.setAttribute("tourPackage", tourPackage);
                request.getRequestDispatcher("/WEB-INF/pages/admin/tour-form.jsp")
                       .forward(request, response);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
} 