
CREATE TABLE products (
    id VARCHAR(250) PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE locations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE productlocations (
    product_id VARCHAR(250) NOT NULL,
    location_id INT NOT NULL,
    cnt INT NOT NULL DEFAULT 0,

    CONSTRAINT pk_productlocations PRIMARY KEY (product_id, location_id),
    CONSTRAINT fk_productlocations_product FOREIGN KEY (product_id) REFERENCES products(id),
    CONSTRAINT fk_productlocations_location FOREIGN KEY (location_id) REFERENCES locations(id),
);
