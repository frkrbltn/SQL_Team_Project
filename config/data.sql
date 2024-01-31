-- Create the schema
CREATE SCHEMA IF NOT EXISTS `dealership`;

-- Use the schema
USE `dealership`;

-- Create tables within the schema with auto-incremented primary keys
CREATE TABLE Car (
  CarId INT AUTO_INCREMENT PRIMARY KEY,
  Make VARCHAR(255),
  Model VARCHAR(255),
  Year TIMESTAMP
);

CREATE TABLE Dealership (
  DealershipId INT AUTO_INCREMENT PRIMARY KEY,
  Name VARCHAR(255),
  Location VARCHAR(255),
  Phone INT
);

CREATE TABLE Inventory (
  InventoryId INT AUTO_INCREMENT PRIMARY KEY,
  DealershipId INT,
  CarId INT,
  FOREIGN KEY (DealershipId) REFERENCES Dealership(DealershipId),
  FOREIGN KEY (CarId) REFERENCES Car(CarId)
);

CREATE TABLE Employee (
  EmployeeId INT AUTO_INCREMENT PRIMARY KEY,
  DealershipId INT,
  Firstname VARCHAR(255),
  Lastname VARCHAR(255),
  Phone VARCHAR(255),
  Email VARCHAR(255),
  Salary INT,
  FOREIGN KEY (DealershipId) REFERENCES Dealership(DealershipId)
);

CREATE TABLE Customer (
  CustomerId INT AUTO_INCREMENT PRIMARY KEY,
  FirstName VARCHAR(255),
  LastName VARCHAR(255),
  BirthDate DATETIME,
  Bank VARCHAR(255),
  CreditCard VARCHAR(255),
  UserName VARCHAR(255),
  Password VARCHAR(255)
);

CREATE TABLE Service (
  ServiceId INT AUTO_INCREMENT PRIMARY KEY,
  EmployeeId INT,
  ServiceDate DATETIME,
  Total INT,
  FOREIGN KEY (EmployeeId) REFERENCES Employee(EmployeeId)
);

CREATE TABLE Ownership (
  CustomerId INT,
  CarId INT,
  DateOfPurchase TIMESTAMP,
  PRIMARY KEY (CustomerId, CarId),
  FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),
  FOREIGN KEY (CarId) REFERENCES Car(CarId)
);

CREATE TABLE Invoice (
  InvoiceId INT AUTO_INCREMENT PRIMARY KEY,
  CustomerId INT,
  PurchaseDate DATETIME,
  TotalAmount DECIMAL,
  FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId)
);

CREATE TABLE ServiceLine (
  ServiceLineId INT AUTO_INCREMENT PRIMARY KEY,
  ServiceId INT,
  CarId INT,
  CustomerId INT,
  ServiceDetails VARCHAR(255),
  IndividualPrice INT,
  FOREIGN KEY (ServiceId) REFERENCES Service(ServiceId),
  FOREIGN KEY (CarId) REFERENCES Car(CarId),
  FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId)
);

CREATE TABLE InvoiceLine (
  InvoiceLineId INT AUTO_INCREMENT PRIMARY KEY,
  DealershipId INT,
  InvoiceId INT,
  CarId INT,
  Amount DECIMAL,
  Date DATETIME,
  FOREIGN KEY (DealershipId) REFERENCES Dealership(DealershipId),
  FOREIGN KEY (InvoiceId) REFERENCES Invoice(InvoiceId),
  FOREIGN KEY (CarId) REFERENCES Car(CarId)
);
