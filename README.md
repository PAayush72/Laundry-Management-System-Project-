# Laundry Management System

A comprehensive, open-source Laundry Management System built with Jakarta EE (Java EE 8). This enterprise-grade application offers a robust RESTful API and a JSF-based web interface for managing laundry operations effectively.

## Features

- **Secure Authentication:** JWT (JSON Web Tokens) based authentication and BCrypt password hashing.
- **RESTful APIs:** Built with JAX-RS (Jersey) to expose services for various clients.
- **Web Interface:** Interactive and responsive UI using JSF 2.3 and PrimeFaces.
- **Payment Integration:** Secure online payments powered by the Razorpay API.
- **Cloud Storage:** Image management using Cloudinary integration.
- **Invoice & Reporting:** Automatic PDF generation using iText PDF.
- **Email Notifications:** Automated mailing services using Jakarta Mail.
- **Database:** JPA (Java Persistence API) mapped entities with CDI (Contexts and Dependency Injection) services. Includes multiple SQL schemas.

## Tech Stack

- **Backend:** Java 11, Jakarta EE 8, EJB, CDI, JAX-RS
- **Frontend:** JSF 2.3, PrimeFaces 8.0
- **Security:** JWT, Soteria (Security API), jBCrypt
- **Database:** SQL (MySQL/MariaDB via JPA)
- **Third-party Services:** Razorpay, Cloudinary, iText PDF, Jakarta Mail
- **Build Tool:** Maven

## Prerequisites

- Java Development Kit (JDK) 11 or higher
- Apache Maven 3.6+
- Jakarta EE 8 compatible Application Server (e.g., Payara Server, GlassFish, or WildFly)
- MySQL / MariaDB Server

## Getting Started

### 1. Database Setup
The project includes database schemas. Import one of the included SQL files into your database server:
```sql
SOURCE laundry_management (6).sql;
```

### 2. Configuration
Update your database credentials, Mail server settings, Razorpay keys, and Cloudinary configuration inside the respective configuration files or environment variables as defined in `src/main/resources` or the specific configuration classes.

### 3. Build the Project
Open a terminal in the project root and run:
```bash
mvn clean install
```
This will generate a `laundrySystem-1.0-SNAPSHOT.war` file in the `target/` directory.

### 4. Deployment
Deploy the generated `.war` file to your preferred application server (Payara, GlassFish, etc.) and start the server.

## Contributing
Contributions are welcome! Please see the [CONTRIBUTING.md](CONTRIBUTING.md) for details on how to contribute to this project.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.