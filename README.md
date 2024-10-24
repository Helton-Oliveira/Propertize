# Propertize - Property Management System Refactoring

## Project Overview

**Propertize** is a refactored version of a system originally developed by me, called **PropertyManager**. This system manages properties, contracts, and users for real estate agencies or independent landlords. The purpose of this project is to demonstrate best coding practices and architectural patterns that enhance scalability, maintainability, and overall code quality.

In this repository, I am refactoring the entire system using industry-standard principles such as **SOLID**, **Clean Architecture**, **Design Patterns**, and **Clean Code**. This project serves as both a professional portfolio item and a learning experience to sharpen my development skills.

## Objectives

The key goals of this project are:

1. **Improve Code Quality**: Refactor the system to follow the **SOLID** principles, making it more modular and easier to extend.
2. **Apply Clean Architecture**: Implement a multi-layer architecture that decouples business logic from frameworks and infrastructure.
3. **Use Design Patterns**: Apply appropriate design patterns (such as **Factory Method**, **Builder**, and **Repository**) to ensure reusable and maintainable code.
4. **Demonstrate Clean Code**: Refactor the codebase for readability, simplicity, and consistency to make future contributions easier.

## Features

The system handles three key areas:

- **Property Management**: Supports the listing and management of properties (residential, commercial, or independent listings).
- **Contract Management**: Facilitates the creation, management, and tracking of property contracts.
- **User Management**: Handles different types of users (real estate agents, landlords, etc.) with role-based access control.

## System Design

Below is an overview of the system architecture, demonstrating the flow between different components and layers, following **Clean Architecture** principles:

![System Design](src\main\resources\templates\System_Design.png)

> *Note: propertize system architecture.*

The system is designed to be modular and scalable, with clear separations of concerns between layers, ensuring easy maintenance and extensibility.

## Technology Stack

- **Backend**: Java (Spring Boot)
- **Database**: PostgreSQL
- **Testing**: JUnit for unit and integration tests

## SOLID Principles

- **Single Responsibility Principle (SRP)**: Each class and module in this project has a clear, single responsibility.
- **Open/Closed Principle (OCP)**: The system is open to extensions for new features but closed to changes that would break existing functionality.
- **Liskov Substitution Principle (LSP)**: All subclasses or derived classes are interchangeable with their parent classes.
- **Interface Segregation Principle (ISP)**: The system uses multiple, smaller interfaces to avoid forcing classes to implement methods they don’t use.
- **Dependency Inversion Principle (DIP)**: High-level modules depend on abstractions, not on low-level implementations.

## Design Patterns

The following design patterns are implemented in this refactor:

- **Builder**: creational pattern that allows the step-by-step creation of a complex object, being able to use the same construction code to represent different objects
- **Strategy**: behavioral pattern that allows you to create families of algorithms with their separate specifications and make them interchangeable
- **State**: Behavioral pattern that allows you to define a class capable of changing the logic of its methods according to its "state", almost as if it were another class.

> *Note: Reference materials for this project were consulted from [Refactoring Guru](https://refactoring.guru/pt-br), which provides comprehensive insights into design patterns and refactoring techniques.*

## How to Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/propertize.git
   cd propertize



