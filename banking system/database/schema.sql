CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20),
    password VARCHAR(100)
);

CREATE TABLE account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20) UNIQUE,
    account_type VARCHAR(20),
    balance DECIMAL(15,2) DEFAULT 0.00,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user(id)
);