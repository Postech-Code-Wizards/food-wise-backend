INSERT INTO tb_user (email, password_hash, user_type)
    VALUES ('teste@teste.com', '$2a$10$N3XyRTwkcqUHA7LVTqqaW.GolvomAvQ5DzWap6ZPG1zhgmnif9jHC', 'RESTAURANT_OWNER');

INSERT INTO address (street, city, state, neighborhood, postal_code, country, latitude, longitude)
    VALUES ('Rua Teste', 'São Paulo', 'SP', 'Vila Teste', '00000-000', 'Brasil', -23.5505199, -46.63330939999999);

INSERT INTO phone (area_code, phone_number, phone_type)
    VALUES ('11', '999999999', 'MOBILE');

INSERT INTO restaurant_profile (business_name, description, business_hours, delivery_radius, cuisine_type, is_open, address_id, phone_id, user_id)
    VALUES ('Restaurante do Teste', 'Restaurante de teste', '08:00-18:00', 5, 'Brasileira', TRUE, 1, 1, 1);

INSERT INTO restaurant_owner (user_id, first_name, last_name, business_registration_number, business_email)
    VALUES (1, 'Teste', 'Teste', '38546898022', '');