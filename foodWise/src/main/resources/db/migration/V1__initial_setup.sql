CREATE TYPE user_type AS ENUM ('CUSTOMER', 'RESTAURANT_OWNER', 'ADMIN');
CREATE TABLE "user" (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    user_type user_type NOT NULL,
    is_active BOOLEAN DEFAULT TRUE NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp
);

CREATE TABLE "address" (
    id BIGSERIAL PRIMARY KEY,
    street VARCHAR(150) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    neighborhood VARCHAR(50) NOT NULL,
    postal_code VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL,
    latitude NUMERIC(9,6),
    longitude NUMERIC(9,6),
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp
);

CREATE TABLE "customer_profile" (
    user_id BIGINT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    address_id BIGINT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,

    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES "user" (id),
    CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES "address" (id)
);

CREATE TABLE "restaurant_profile" (
    user_id BIGINT PRIMARY KEY,
    business_name VARCHAR(100) NOT NULL,
    description TEXT,
    business_hours VARCHAR(100),
    delivery_radius SMALLINT,
    cuisine_type VARCHAR(50),
    is_open BOOLEAN DEFAULT FALSE NOT NULL,
    address_id BIGINT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,

    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES "user" (id),
    CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES "address" (id)
);

CREATE TYPE phone_type AS ENUM ('MOBILE', 'WORK', 'HOME', 'FAX');
CREATE TABLE "phone" (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT,
    restaurant_id BIGINT,
    area_code VARCHAR(100) NOT NULL,
    phone_number TEXT NOT NULL,
    phone_type phone_type NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,

    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES "customer_profile" (user_id),
    CONSTRAINT fk_restaurant FOREIGN KEY (restaurant_id) REFERENCES "restaurant_profile" (user_id)
);

CREATE TABLE "restaurant_review" (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    restaurant_id BIGINT NOT NULL,
    rating SMALLINT CHECK (rating BETWEEN 1 AND 5),
    review_text TEXT,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,

    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES "customer_profile" (user_id),
    CONSTRAINT fk_restaurant FOREIGN KEY (restaurant_id) REFERENCES "restaurant_profile" (user_id)
);

CREATE TABLE "menu" (
    id BIGSERIAL PRIMARY KEY,
    restaurant_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,

    CONSTRAINT fk_restaurant FOREIGN KEY (restaurant_id) REFERENCES "restaurant_profile" (user_id)
);

CREATE TABLE "menu_item" (
    id BIGSERIAL PRIMARY KEY,
    menu_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    category VARCHAR(50) NOT NULL,
    is_available BOOLEAN DEFAULT TRUE NOT NULL,
    image_url VARCHAR(255),
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,

    CONSTRAINT fk_menu FOREIGN KEY (menu_id) REFERENCES "menu" (id)
);

CREATE TABLE "order_status" (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp
);

CREATE TABLE "order" (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT,
    restaurant_id BIGINT,
    order_status_id BIGINT NOT NULL,
    order_date TIMESTAMPTZ NOT NULL,
    delivery_to_customer_address_id BIGINT NOT NULL,
    delivery_from_restaurant_address_id BIGINT NOT NULL,
    total_price NUMERIC(10,2) NOT NULL,
    transaction_date TIMESTAMPTZ DEFAULT current_timestamp,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,

    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES "customer_profile" (user_id),
    CONSTRAINT fk_restaurant FOREIGN KEY (restaurant_id) REFERENCES "restaurant_profile" (user_id),
    CONSTRAINT fk_order_status FOREIGN KEY (order_status_id) REFERENCES "order_status" (id),
    CONSTRAINT fk_delivery_to_customer_address FOREIGN KEY (delivery_to_customer_address_id) REFERENCES "address" (id),
    CONSTRAINT fk_delivery_from_restaurant_address FOREIGN KEY (delivery_from_restaurant_address_id) REFERENCES "address" (id)
);

CREATE TYPE payment_status AS ENUM ('PENDING', 'COMPLETED', 'FAILED', 'REFUNDED');
CREATE TABLE "order_payment" (
    id BIGSERIAL PRIMARY KEY,
    payment_status payment_status NOT NULL,
    transaction_reference VARCHAR(50) NOT NULL,
    transaction_date TIMESTAMPTZ DEFAULT current_timestamp,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp
);

CREATE TABLE "order_item" (
    id BIGSERIAL PRIMARY KEY,
    menu_item_id BIGINT NOT NULL,
    order_payment_id BIGINT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,

    CONSTRAINT fk_menu_item FOREIGN KEY (menu_item_id) REFERENCES "menu_item" (id),
    CONSTRAINT fk_order_payment FOREIGN KEY (order_payment_id) REFERENCES "order_payment" (id)
);