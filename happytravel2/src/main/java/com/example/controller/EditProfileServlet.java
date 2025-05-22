package com.example.controller;

import com.example.dao.UserDAO;
import com.example.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/profile/edit")
public class EditProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("userId") != null) {
            int userId = (int) session.getAttribute("userId");
            User user = userDAO.getUserById(userId);

            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/WEB-INF/pages/edit-profile.jsp").forward(request, response);
            } else {
                session.invalidate(); 
                response.sendRedirect(request.getContextPath() + "/auth/login");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/auth/login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("userId") != null) {
            int userId = (int) session.getAttribute("userId");
            User user = userDAO.getUserById(userId); // Get existing user data

            if (user != null) {
                // Get updated information from form
                user.setEmail(request.getParameter("email"));
                user.setFullName(request.getParameter("fullName"));
                user.setPhone(request.getParameter("phone"));
                user.setAddress(request.getParameter("address"));

                if (userDAO.updateUser(user)) {
                    // Update successful, redirect to profile page
                    response.sendRedirect(request.getContextPath() + "/profile");
                } else {
                    // Update failed, show error on edit page
                    request.setAttribute("error", "Failed to update profile.");
                    doGet(request, response); // Show the edit page again with error
                }
            } else {
                // User not found, redirect to login (shouldn't happen if logged in)
                 session.invalidate(); 
                 response.sendRedirect(request.getContextPath() + "/auth/login");
            }
        } else {
            // Not logged in, redirect to login page
            response.sendRedirect(request.getContextPath() + "/auth/login");
        }
    }
} 