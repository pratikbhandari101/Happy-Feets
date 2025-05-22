package com.example.dao;

import com.example.model.TourPackage;
import com.example.util.DatabaseUtil;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourPackageDAO {

    public List<TourPackage> getAllTourPackages() {
        List<TourPackage> tourPackages = new ArrayList<>();
        String sql = "SELECT package_id, name, description, price, duration, location, max_capacity, available_seats, image_url, status FROM tour_packages";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tourPackages.add(mapResultSetToTourPackage(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the exception properly
        }
        return tourPackages;
    }

    public List<TourPackage> searchTourPackages(String searchTerm, String destination, 
                                              BigDecimal minPrice, BigDecimal maxPrice, 
                                              Integer minDuration, Integer maxDuration) {
        List<TourPackage> tourPackages = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT package_id, name, description, price, duration, location, max_capacity, available_seats, image_url, status FROM tour_packages WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            sql.append(" AND (name LIKE ? OR description LIKE ?)");
            params.add("%" + searchTerm + "%");
            params.add("%" + searchTerm + "%");
        }

        if (destination != null && !destination.trim().isEmpty()) {
            sql.append(" AND location LIKE ?");
            params.add("%" + destination + "%");
        }

        if (minPrice != null) {
            sql.append(" AND price >= ?");
            params.add(minPrice);
        }

        if (maxPrice != null) {
            sql.append(" AND price <= ?");
            params.add(maxPrice);
        }

        if (minDuration != null) {
            sql.append(" AND duration >= ?");
            params.add(minDuration);
        }

        if (maxDuration != null) {
            sql.append(" AND duration <= ?");
            params.add(maxDuration);
        }

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            // Set parameters
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    tourPackages.add(mapResultSetToTourPackage(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the exception properly
        }
        return tourPackages;
    }

    private TourPackage mapResultSetToTourPackage(ResultSet rs) throws SQLException {
        TourPackage tourPackage = new TourPackage();
        tourPackage.setPackageId(rs.getInt("package_id"));
        tourPackage.setName(rs.getString("name"));
        tourPackage.setDescription(rs.getString("description"));
        tourPackage.setPrice(rs.getBigDecimal("price"));
        tourPackage.setDuration(rs.getInt("duration"));
        tourPackage.setLocation(rs.getString("location"));
        tourPackage.setMaxCapacity(rs.getInt("max_capacity"));
        tourPackage.setAvailableSeats(rs.getInt("available_seats"));
        tourPackage.setImageUrl(rs.getString("image_url"));
        tourPackage.setStatus(rs.getString("status"));
        return tourPackage;
    }

    public TourPackage getTourPackageById(int packageId) {
        String sql = "SELECT package_id, name, description, price, duration, location, max_capacity, available_seats, image_url, status FROM tour_packages WHERE package_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, packageId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTourPackage(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the exception properly
        }
        return null;
    }

    // Admin CRUD Operations
    public boolean addTourPackage(TourPackage tourPackage) {
        String sql = "INSERT INTO tour_packages (name, description, price, duration, location, max_capacity, available_seats, image_url, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, tourPackage.getName());
            pstmt.setString(2, tourPackage.getDescription());
            pstmt.setBigDecimal(3, tourPackage.getPrice());
            pstmt.setInt(4, tourPackage.getDuration());
            pstmt.setString(5, tourPackage.getLocation());
            pstmt.setInt(6, tourPackage.getMaxCapacity());
            pstmt.setInt(7, tourPackage.getAvailableSeats());
            pstmt.setString(8, tourPackage.getImageUrl());
            pstmt.setString(9, tourPackage.getStatus());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        tourPackage.setPackageId(generatedKeys.getInt(1));
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the exception properly
        }
        return false;
    }

    public boolean updateTourPackage(TourPackage tourPackage) {
        String sql = "UPDATE tour_packages SET name = ?, description = ?, price = ?, duration = ?, location = ?, max_capacity = ?, available_seats = ?, image_url = ?, status = ? " +
                    "WHERE package_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tourPackage.getName());
            pstmt.setString(2, tourPackage.getDescription());
            pstmt.setBigDecimal(3, tourPackage.getPrice());
            pstmt.setInt(4, tourPackage.getDuration());
            pstmt.setString(5, tourPackage.getLocation());
            pstmt.setInt(6, tourPackage.getMaxCapacity());
            pstmt.setInt(7, tourPackage.getAvailableSeats());
            pstmt.setString(8, tourPackage.getImageUrl());
            pstmt.setString(9, tourPackage.getStatus());
            pstmt.setInt(10, tourPackage.getPackageId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the exception properly
        }
        return false;
    }

    public boolean deleteTourPackage(int packageId) {
        String sql = "DELETE FROM tour_packages WHERE package_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, packageId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the exception properly
        }
        return false;
    }

    // You will add other methods here for search, admin, etc.
} 