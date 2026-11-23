# Sari-Sari Store Management System

A console-based Java application designed for local sari-sari store owners and cashiers to manage product inventory, process point-of-sale (POS) transactions, track business expenses, and monitor customer credit (*utang*) records[cite: 1, 2].

Developed in partial fulfillment of the requirements for **OOP101 – Object-Oriented Programming** at **Batangas State University – The National Engineering University (Alangilan Campus)**.

---

## Features

* **Role-Based Access Control:** Separate console menus and permissions for **Store Owners** and **Cashiers**.
* **Inventory Management:** Add, edit, delete, search (by ID or name), restock items, and receive automated low-stock warnings[cite: 1, 2].
* **Point-of-Sale (POS) & Receipts:** Process customer purchases, automatically deduct sold stock, compute change, and generate transaction receipts[cite: 1].
* **Customer Credit (*Utang*) Ledger:** Register customers, log unpaid credit purchases, process partial or full payments, and check remaining balances[cite: 2, 3].
* **Expense & Profit Reporting:** Record store expenses and generate daily/overall sales and profit comparisons[cite: 1, 2].
* **Persistent File Storage:** Automatically saves and loads all records using local CSV files so data remains intact between sessions[cite: 1, 2].

---

## Object-Oriented Programming (OOP) Concepts Applied

* **Encapsulation:** Private attributes with controlled getters and setters across core models (`Product`, `User`, `Customer`, `Sales`)[cite: 1].
* **Inheritance:** Specialized user roles (`Owner` and `Cashier`) inherit shared properties and authentication methods from the base `User` class[cite: 1].
* **Abstraction:** Uses abstract base classes (`User`) and interfaces (`Payable`) to define standard contracts while hiding complex backend calculations and file-handling operations[cite: 1, 8].
* **Polymorphism:** Method overriding (`showMenu()`, `calculateTotal()`) enables different behaviors depending on the logged-in user role or transaction type[cite: 1].
* **Exception Handling & Collections:** Uses `ArrayList` for dynamic record management and `try-catch` validation to prevent crashes from invalid inputs or insufficient stock[cite: 1].

---

## Project Structure

```text
SariSariStoreSystem/
├── src/
│   ├── Main.java                       # Entry point, login loop, and role-based menus
│   ├── models/                         # Core data entities & OOP hierarchy
│   │   ├── Product.java
│   │   ├── Sales.java
│   │   ├── SaleItem.java
│   │   ├── Payment.java
│   │   ├── Expense.java
│   │   ├── Customer.java
│   │   ├── User.java
│   │   ├── Owner.java
│   │   └── Cashier.java
│   ├── managers/                       # Business logic & ArrayList controllers
│   │   ├── InventoryManager.java
│   │   ├── SalesManager.java
│   │   ├── SalesReportManager.java
│   │   ├── CustomerManager.java
│   │   └── UserManager.java
│   └── util/
│       └── FileHandler.java            # CSV file reading and writing
├── data/                               # Local persistent storage (.csv files)
├── docs/                               # Project proposal & user documentation
├── diagrams/                           # UML / Class diagrams
├── screenshots/                        # Application output screenshots
└── README.md