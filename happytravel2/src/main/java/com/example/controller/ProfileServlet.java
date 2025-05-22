package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.example.dao.UserDAO;
import com.example.model.User;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Don't create session if it doesn't exist

        if (session != null && session.getAttribute("userId") != null) {
            // User is logged in, fetch user data
            int userId = (int) session.getAttribute("userId");
            User user = userDAO.getUserById(userId);

            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
            } else {
                // User not found in database (shouldn't happen if logged in)
                session.invalidate(); // Invalidate the session
                response.sendRedirect(request.getContextPath() + "/auth/login"); // Redirect to login
            }
        } else {
            // User is not logged in, redirect to login page
            response.sendRedirect(request.getContextPath() + "/auth/login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle profile updates here later
        doGet(request, response);
    }
} 