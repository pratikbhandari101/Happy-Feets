package com.example.model;

import java.math.BigDecimal;

public class TourPackage {
    private int packageId;
    private String name;
    private String destination;
    private String description;
    private BigDecimal price;
    private int duration;
    private String imageUrl;
    private String location;
    private int maxCapacity;
    private int availableSeats;
    private String status;

    // Constructors
    public TourPackage() {
    }

    public TourPackage(int packageId, String name, String destination, String description, 
                      BigDecimal price, int duration, String imageUrl, String location, int maxCapacity, int availableSeats, String status) {
        this.packageId = packageId;
        this.name = name;
        this.destination = destination;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.imageUrl = imageUrl;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.availableSeats = availableSeats;
        this.status = status;
    }

    // Getters and Setters
    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TourPackage{" +
               "packageId=" + packageId +
               ", name='" + name + '\'' +
               ", destination='" + destination + '\'' +
               ", description='" + description + '\'' +
               ", price=" + price +
               ", duration=" + duration +
               ", imageUrl='" + imageUrl + '\'' +
               ", location='" + location + '\'' +
               ", maxCapacity=" + maxCapacity +
               ", availableSeats=" + availableSeats +
               ", status='" + status + '\'' +
               '}';
    }
} 