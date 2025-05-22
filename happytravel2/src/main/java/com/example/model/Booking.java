package com.example.model;

import java.sql.Timestamp;

public class Booking {
    private int bookingId;
    private int userId;
    private int packageId;
    private Timestamp bookingDate;
    private int numberOfTravelers;
    private String status;

    // Constructors
    public Booking() {
    }

    public Booking(int bookingId, int userId, int packageId, Timestamp bookingDate, int numberOfTravelers, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.packageId = packageId;
        this.bookingDate = bookingDate;
        this.numberOfTravelers = numberOfTravelers;
        this.status = status;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNumberOfTravelers() {
        return numberOfTravelers;
    }

    public void setNumberOfTravelers(int numberOfTravelers) {
        this.numberOfTravelers = numberOfTravelers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
               "bookingId=" + bookingId +
               ", userId=" + userId +
               ", packageId=" + packageId +
               ", bookingDate=" + bookingDate +
               ", numberOfTravelers=" + numberOfTravelers +
               ", status='" + status + '\'' +
               '}';
    }
} 