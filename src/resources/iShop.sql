DROP DATABASE IF EXISTS ishop;
CREATE DATABASE ishop CHAR SET utf8;
USE ishop;

CREATE TABLE users(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    role VARCHAR(45) NOT NULL
);

CREATE TABLE products(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
	description VARCHAR(45) NOT NULL,
    price DECIMAL(5,2) not null
);

CREATE TABLE buckets(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id int NOT NULL,
    product_id int NOT NULL,
    purchase_date Date NOT NULL,
    foreign key (user_id) REFERENCES users(id),
    foreign key (product_id) REFERENCES products(id)
);