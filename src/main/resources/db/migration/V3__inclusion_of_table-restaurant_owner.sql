CREATE TABLE "restaurant_owner" (
    user_id BIGINT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    business_registration_number VARCHAR(14) NOT NULL,
    business_email VARCHAR(100) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,

    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES "tb_user" (id)
);