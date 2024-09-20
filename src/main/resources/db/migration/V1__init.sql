CREATE TABLE category
(
    id   BIGINT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id BIGINT NOT NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE price
(
    id       BIGINT NOT NULL,
    currency VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    CONSTRAINT pk_price PRIMARY KEY (id)
);

CREATE TABLE product
(
    id          BIGINT NOT NULL,
    title       VARCHAR(255) NULL,
    descr       VARCHAR(255) NULL,
    image       VARCHAR(255) NULL,
    category_id BIGINT NULL,
    price_id    BIGINT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE product_orders
(
    order_id   BIGINT NOT NULL,
    product_id BIGINT NOT NULL
);

ALTER TABLE product
    ADD CONSTRAINT uc_product_price UNIQUE (price_id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_PRICE FOREIGN KEY (price_id) REFERENCES price (id);

ALTER TABLE product_orders
    ADD CONSTRAINT fk_proord_on_order FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE product_orders
    ADD CONSTRAINT fk_proord_on_product FOREIGN KEY (product_id) REFERENCES product (id);