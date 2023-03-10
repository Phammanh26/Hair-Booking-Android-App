CREATE DATABASE HairBookingDatabase;

-- Bảng user
CREATE TABLE user (
    user_id INT PRIMARY KEY,
    sdt VARCHAR(20),
    fullname VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(20),
    email VARCHAR(255)
);

-- Bảng Service
CREATE TABLE service (
    service_id INT PRIMARY KEY,
    category_id INT,
    name VARCHAR(255),
    description TEXT,
    price FLOAT
);

-- Bảng Order
CREATE TABLE order (
    order_id INT PRIMARY KEY,
    created_at DATETIME,
    status VARCHAR(50),
    user_id INT REFERENCES user(user_id),
);

-- Bảng OrderDetail
CREATE TABLE order_detail (
    order_detail_id INT PRIMARY KEY,
    service_id INT REFERENCES service(service_id),
    slot_id INT REFERENCES slot(slot_id)
);

-- Bảng Slot
CREATE TABLE slot (
    slot_id INT PRIMARY KEY,
    date_id DATE,
    start_time TIME,
    end_time TIME,
    quantity INT,
    available_quantity INT
);

-- Bảng Category
CREATE TABLE category (
    category_id INT PRIMARY KEY,
    name VARCHAR(255)
);