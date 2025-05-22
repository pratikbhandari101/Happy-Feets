package com.example.controller;

import com.example.dao.BookingDAO;
import com.example.model.Booking;
import com.example.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/bookings")
public class BookingServlet extends HttpServlet {
    private BookingDAO bookingDAO;

    @Override
    public void init() throws ServletException {
        bookingDAO = new BookingDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId"); // Get userId from session

        // 1. Check if user is logged in (userId is in session)
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login"); // Redirect to login if not logged in
            return;
        }

        // Optional: Fetch the full User object if needed for further processing (e.g., role check)
        // UserDAO userDAO = new UserDAO(); // Need an instance of UserDAO
        // User currentUser = userDAO.getUserById(userId);

        // 2. Get packageId and numberOfTravelers from the form
        String packageIdStr = request.getParameter("packageId");
        String numberOfTravelersStr = request.getParameter("numberOfTravelers");

        if (packageIdStr == null || packageIdStr.isEmpty() || numberOfTravelersStr == null || numberOfTravelersStr.isEmpty()) {
            // Handle missing parameters (e.g., redirect with an error message)
            response.sendRedirect(request.getContextPath() + "/tours"); // Redirect back to tours page for now
            return;
        }

        try {
            int packageId = Integer.parseInt(packageIdStr);
            int numberOfTravelers = Integer.parseInt(numberOfTravelersStr);

            // 3. Validate input (basic validation)
            if (numberOfTravelers <= 0) {
                // Handle invalid number of travelers
                 response.sendRedirect(request.getContextPath() + "/tours/view/" + packageId); // Redirect back to package details with error
                return;
            }

            // 4. Create a new Booking object
            Booking booking = new Booking();
            booking.setUserId(userId); // Use the user's ID from session
            booking.setPackageId(packageId);
            booking.setNumberOfTravelers(numberOfTravelers);
            booking.setStatus("Pending"); // Initial status

            // 5. Add the booking to the database
            boolean success = bookingDAO.addBooking(booking);

            // 6. Redirect to confirmation page or show error
            if (success) {
                // Redirect to a booking confirmation page, passing the booking ID
                response.sendRedirect(request.getContextPath() + "/bookings/confirm?bookingId=" + booking.getBookingId());
            } else {
                // Handle database insertion failure
                // For now, redirect back to package details with a potential error message
                 response.sendRedirect(request.getContextPath() + "/tours/view/" + packageId); 
            }

        } catch (NumberFormatException e) {
            // Handle invalid number format for packageId or numberOfTravelers
             response.sendRedirect(request.getContextPath() + "/tours"); // Redirect to tours page for invalid format
        }
    }
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
         String pathInfo = request.getPathInfo();

         // Add logging to see what pathInfo is
         System.out.println("BookingServlet doGet pathInfo: " + pathInfo);

         // More robust path check
         boolean isConfirmationRequest = false;
         String requestURI = request.getRequestURI();
         String contextPath = request.getContextPath();

         if (requestURI.endsWith(contextPath + "/bookings/confirm")) {
             isConfirmationRequest = true;
         }

         if (isConfirmationRequest) {
             // Handle booking confirmation display
             String bookingIdStr = request.getParameter("bookingId");
             if (bookingIdStr != null && !bookingIdStr.isEmpty()) {
                 try {
                     int bookingId = Integer.parseInt(bookingIdStr);
                     Booking booking = bookingDAO.getBookingById(bookingId);
                     if (booking != null) {
                         request.setAttribute("booking", booking);
                         request.getRequestDispatcher("/WEB-INF/pages/booking-confirmation.jsp").forward(request, response);
                     } else {
                         response.sendError(HttpServletResponse.SC_NOT_FOUND, "Booking not found");
                     }
                 } catch (NumberFormatException e) {
                     response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid booking ID");
                 }
             } else {
                 response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Booking ID is required");
             }
         } else {
             // Handle other GET requests (e.g., user's bookings list later)
             response.sendError(HttpServletResponse.SC_NOT_FOUND);
         }
    }

} 