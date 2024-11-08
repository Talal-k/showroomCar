CREATE SEQUENCE IF NOT EXISTS car_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS showroom_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE c_user
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    full_name VARCHAR(255),
    username  VARCHAR(255),
    password  VARCHAR(255),
    CONSTRAINT pk_c_user PRIMARY KEY (id)
);

CREATE TABLE car
(
    id          BIGINT           NOT NULL,
    vin         VARCHAR(25)      NOT NULL,
    maker       VARCHAR(25)      NOT NULL,
    model       VARCHAR(25)      NOT NULL,
    model_year  BIGINT           NOT NULL,
    price       DOUBLE PRECISION NOT NULL,
    showroom_id BIGINT           NOT NULL,
    CONSTRAINT pk_car PRIMARY KEY (id)
);

CREATE TABLE showroom
(
    id                             BIGINT       NOT NULL,
    name                           VARCHAR(100) NOT NULL,
    commercial_registration_number BIGINT       NOT NULL,
    manager_name                   VARCHAR(255),
    contact_number                 BIGINT       NOT NULL,
    address                        VARCHAR(255),
    is_delete                      BOOLEAN,
    CONSTRAINT pk_showroom PRIMARY KEY (id)
);

ALTER TABLE showroom
    ADD CONSTRAINT uc_showroom_commercial_registration_number UNIQUE (commercial_registration_number);

ALTER TABLE car
    ADD CONSTRAINT FK_CAR_ON_SHOWROOM FOREIGN KEY (showroom_id) REFERENCES showroom (id);

INSERT INTO c_user (full_name, username, password)
VALUES
    ('Talal', 'talal', 'password456');

INSERT INTO showroom (id, name, commercial_registration_number, manager_name, contact_number, address, is_delete)
VALUES
    (nextval('showroom_seq'), 'Best Cars', 1001, 'Alice Johnson', 1234567890, '123 Elm Street, Springfield', FALSE),
    (nextval('showroom_seq'), 'Luxury Motors', 1002, 'Bob Brown', 2345678901, '456 Oak Avenue, Springfield', FALSE);

-- Insert initial cars with references to the inserted showrooms
INSERT INTO car (id, vin, maker, model, model_year, price, showroom_id)
VALUES
    (nextval('car_seq'), '1HGCM82633A123456', 'Honda', 'Accord', 2020, 25000.00, (SELECT id FROM showroom WHERE name = 'Best Cars')),
    (nextval('car_seq'), '1FTFW1EF1EFC12345', 'Ford', 'F-150', 2019, 30000.00, (SELECT id FROM showroom WHERE name = 'Best Cars')),
    (nextval('car_seq'), '1HGCR2F33HA123456', 'Honda', 'Civic', 2021, 22000.00, (SELECT id FROM showroom WHERE name = 'Luxury Motors')),
    (nextval('car_seq'), '1C4RJFAG5JC123456', 'Jeep', 'Grand Cherokee', 2022, 45000.00, (SELECT id FROM showroom WHERE name = 'Luxury Motors'));
