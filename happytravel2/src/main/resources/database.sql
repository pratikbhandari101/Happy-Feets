-- Create database
CREATE DATABASE IF NOT EXISTS happytravel;
USE happytravel;

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    address TEXT,
    role ENUM('user', 'admin') DEFAULT 'user',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create tour_packages table
CREATE TABLE IF NOT EXISTS tour_packages (
    package_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    duration INT NOT NULL,
    location VARCHAR(100) NOT NULL,
    max_capacity INT NOT NULL,
    available_seats INT NOT NULL,
    image_url VARCHAR(255),
    status ENUM('active', 'inactive') DEFAULT 'active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create bookings table
CREATE TABLE IF NOT EXISTS bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    package_id INT NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    number_of_travelers INT NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'Pending',
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (package_id) REFERENCES tour_packages(package_id) ON DELETE CASCADE
);

-- Insert sample admin user
INSERT INTO users (username, email, password, full_name, role)
VALUES ('admin', 'admin@happytravel.com', 'admin123', 'Administrator', 'admin');

-- Insert sample tour packages
INSERT INTO tour_packages (name, description, price, duration, location, max_capacity, available_seats, image_url)
VALUES 
('Bali Adventure', 'Experience the beauty of Bali with our comprehensive tour package.', 999.99, 7, 'Bali, Indonesia', 20, 20, 'destination1.jpg'),
('Paris Explorer', 'Discover the romantic city of Paris with guided tours and luxury accommodations.', 1499.99, 5, 'Paris, France', 15, 15, 'destination2.jpg'),
('Tokyo Discovery', 'Immerse yourself in Japanese culture with our Tokyo tour package.', 1299.99, 6, 'Tokyo, Japan', 18, 18, 'destination3.jpg'); 