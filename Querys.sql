-- Script to create the BANKINC database
CREATE DATABASE BANKINC;

-- Script to create the Card table
CREATE TABLE Card (
    Card_ID SERIAL PRIMARY KEY,
    Product_ID INT NOT NULL,
    Card_Number VARCHAR(16) NOT NULL,
    Cardholder_Name VARCHAR(255) NOT NULL,
    Creation_Date DATE NOT NULL,
    Expiry_Date DATE NOT NULL,
    Balance DECIMAL(10, 2) NOT NULL,
    Status VARCHAR(20) NOT NULL
);

-- Script to create the Transaction table
CREATE TABLE Transaction (
    Transaction_ID SERIAL PRIMARY KEY,
    Card_ID INT REFERENCES Card(Card_ID),
    Transaction_Date TIMESTAMP NOT NULL,
    Amount DECIMAL(10, 2) NOT NULL,
    Status VARCHAR(20) NOT NULL
);

-- Script to create the Product table
CREATE TABLE Product (
    Product_ID SERIAL PRIMARY KEY,
    Description VARCHAR(200) NOT NULL
);

-- Script to create the intermediate table Card_Transaction
CREATE TABLE Card_Transaction (
    Card_Transaction_ID SERIAL PRIMARY KEY,
    Card_ID INT REFERENCES Card(Card_ID),
    Transaction_ID INT REFERENCES Transaction(Transaction_ID)
);


-- Script to create the intermediate table Product_Card
CREATE TABLE Product_Card (
    Product_Card_ID SERIAL PRIMARY KEY,
    Product_ID INT REFERENCES Product(Product_ID),
    Card_ID INT REFERENCES Card(Card_ID)
);

-- Insertar datos en la tabla Product
INSERT INTO Product (Description) VALUES 
    ('Credito'),
    ('Debito'),
    ('GitCard');

-- Insertar datos de prueba en la tabla Card
INSERT INTO Card (Product_ID, Card_Number, Cardholder_Name, Creation_Date, Expiry_Date, Balance, Status) VALUES
    (1, '1234567890123456', 'John Doe', '2022-01-01', '2023-12-31', 1000.00, 'Active'),
    (2, '9876543210987654', 'Jane Smith', '2022-02-15', '2024-01-15', 500.00, 'Active'),
    (3, '1111222233334444', 'Bob Johnson', '2022-03-20', '2024-02-28', 750.00, 'Inactive');

-- Insertar datos de prueba en la tabla Transaction
INSERT INTO Transaction (Card_ID, Transaction_Date, Amount, Status) VALUES
    (1, '2022-01-10 12:30:00', 50.00, 'Approved'),
    (1, '2022-02-05 14:45:00', 30.00, 'Approved'),
    (2, '2022-03-15 08:00:00', 20.00, 'Approved'),
    (3, '2022-04-22 18:20:00', 100.00, 'Approved');

-- Insertar datos de prueba en la tabla Card_Transaction
INSERT INTO Card_Transaction (Card_ID, Transaction_ID) VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (3, 4);

-- Insertar datos de prueba en la tabla Product_Card
INSERT INTO Product_Card (Product_ID, Card_ID) VALUES
    (1, 1),
    (2, 2),
    (3, 3);