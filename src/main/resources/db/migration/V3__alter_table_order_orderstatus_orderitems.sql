DROP TABLE IF EXISTS "order_item" CASCADE;
DROP TABLE IF EXISTS "order_payment" CASCADE;
DROP TABLE IF EXISTS "order_status" CASCADE;
DROP TABLE IF EXISTS "order" CASCADE;

CREATE TABLE "order_status" (
    id BIGSERIAL PRIMARY KEY,
    order_stage VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp
);

CREATE TABLE "order_payment" (
    id BIGSERIAL PRIMARY KEY,
    payment_status VARCHAR(50) NOT NULL,
    transaction_reference VARCHAR(50),
    transaction_date TIMESTAMPTZ,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,
    payment_method VARCHAR(50)
);

CREATE TABLE "orders" (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    restaurant_id BIGINT NOT NULL,
    order_date TIMESTAMPTZ,
    delivery_to_customer_address_id BIGINT NOT NULL,
    delivery_from_restaurant_address_id BIGINT NOT NULL,
    total_price NUMERIC(10,2) NOT NULL,
    transaction_date TIMESTAMPTZ DEFAULT current_timestamp,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,

    order_status_id BIGINT NOT NULL UNIQUE,
    order_payment_id BIGINT NOT NULL UNIQUE,

    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES "customer_profile" (user_id),
    CONSTRAINT fk_restaurant FOREIGN KEY (restaurant_id) REFERENCES "restaurant_profile" (user_id),
    CONSTRAINT fk_delivery_to_customer_address FOREIGN KEY (delivery_to_customer_address_id) REFERENCES "address" (id),
    CONSTRAINT fk_delivery_from_restaurant_address FOREIGN KEY (delivery_from_restaurant_address_id) REFERENCES "address" (id),
    CONSTRAINT fk_order_status FOREIGN KEY (order_status_id) REFERENCES "order_status" (id),
    CONSTRAINT fk_order_payment FOREIGN KEY (order_payment_id) REFERENCES "order_payment" (id)
);

CREATE TABLE "order_item" (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,  -- Add the missing order_id column
    menu_item_id BIGINT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,

    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES "orders" (id)
);