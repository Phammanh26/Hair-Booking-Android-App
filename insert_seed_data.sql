-- Thêm dữ liệu vào bảng user
INSERT INTO user (user_id, sdt, fullname, password, role, email)
VALUES 
    (1, '0123456789', 'Nguyễn Văn A', 'password', 'admin', 'nguyenvana@example.com'),
    (2, '0987654321', 'Trần Thị B', 'password', 'user', 'tranthib@example.com'),
    (3, '0909090909', 'Lê Văn C', 'password', 'user', 'levanc@example.com');

-- Thêm dữ liệu vào bảng category
INSERT INTO category (category_id, name)
VALUES 
    (1, 'Haircut'),
    (2, 'Hair color'),
    (3, 'Hair styling');

-- Thêm dữ liệu vào bảng service
INSERT INTO service (service_id, category_id, name, description, price)
VALUES 
    (1, 1, 'Men\'s Haircut', 'Basic haircut for men', 15.00),
    (2, 1, 'Women\'s Haircut', 'Basic haircut for women', 25.00),
    (3, 2, 'Partial Highlight', 'Highlight only a portion of the hair', 60.00),
    (4, 2, 'Full Highlight', 'Highlight the entire head of hair', 120.00),
    (5, 3, 'Blowout', 'Professional blow dry and styling', 35.00);

-- Thêm dữ liệu vào bảng slot
INSERT INTO slot (slot_id, date, start_time, end_time, quantity, available_quantity)
VALUES 
    (1, '2022-08-01', '10:00:00', '11:00:00', 5, 5),
    (2, '2022-08-01', '11:00:00', '12:00:00', 5, 5),
    (3, '2022-08-02', '10:00:00', '11:00:00', 5, 5),
    (4, '2022-08-02', '11:00:00', '12:00:00', 5, 5);

-- Thêm dữ liệu vào bảng order
INSERT INTO `order` (order_id, created_at, status, user_id)
VALUES 
    (1, '2022-08-01 09:00:00', 'pending', 2),
    (2, '2022-08-02 11:00:00', 'completed', 3);

-- Thêm dữ liệu vào bảng order_detail
INSERT INTO order_detail (order_detail_id, order_id, service_id, slot_id)
VALUES 
    (1, 1, 1, 1),
    (2, 2, 2, 3),
    (3, 2, 5, 4),
    (4, 2, 4, 4);