INSERT INTO orders (id, address, city, date, email, first_name, last_name, phone_number, post_index, total_price)
    VALUES (1, 'Fevzi Cakmak mah. 2010 sk. no:1', 'İstanbul', '2023-01-04', 'aligormez@gmail.com', 'Ali', 'Görmez', '0534567521', 34522, 1200);
INSERT INTO orders (id, address, city, date, email, first_name, last_name, phone_number, post_index, total_price)
    VALUES (2, 'İzmir Caddesi 2/B no', 'Adana', '2023-01-04', 'yakupkrky@gmail.com', 'Yakup', 'Karakaya', '0534467148', 42363, 1530);
INSERT INTO orders (id, address, city, date, email, first_name, last_name, phone_number, post_index, total_price)
    VALUES (3, 'Karıncalar Sokak no:3', 'Trabzon', '2023-01-04', 'ismailunal@gmail.com', 'İsmail', 'Unal', '0537566521', 324252, 1236);
INSERT INTO orders (id, address, city, date, email, first_name, last_name, phone_number, post_index, total_price)
    VALUES (4, 'Devekaldırım Caddesi 4/A blok', 'İzmir', '2023-01-04', 'cetinkrz@gmail.com', 'Çetin', 'Kiraz', '0530567837', 5432, 950);
INSERT INTO orders (id, address, city, date, email, first_name, last_name, phone_number, post_index, total_price)
    VALUES (5, 'Kitapçı Sokak no:15', 'İstanbul', '2023-01-04', 'aysemermer@gmail.com', 'Ayşe', 'Mermer', '0534267590', 12342, 150);

INSERT INTO order_item (id, amount, quantity, product_id) VALUES (1, 1, 2, 12);
INSERT INTO order_item (id, amount, quantity, product_id) VALUES (2, 2, 3, 7);
INSERT INTO order_item (id, amount, quantity, product_id) VALUES (3, 3, 2, 10);
INSERT INTO order_item (id, amount, quantity, product_id) VALUES (4, 10, 1, 5);
INSERT INTO order_item (id, amount, quantity, product_id) VALUES (5, 5, 1, 6);

INSERT INTO orders_order_items (order_entity_id, order_items_id) VALUES (1, 1);
INSERT INTO orders_order_items (order_entity_id, order_items_id) VALUES (1, 2);
INSERT INTO orders_order_items (order_entity_id, order_items_id) VALUES (2, 3);
INSERT INTO orders_order_items (order_entity_id, order_items_id) VALUES (2, 4);
INSERT INTO orders_order_items (order_entity_id, order_items_id) VALUES (3, 5);

UPDATE user_role SET roles = 'ADMIN' WHERE user_id = 36;
