# 💸 The Java Om Bank – Spring Boot Banking Application

A complete backend banking application built using Java 17 and Spring Boot 3.5.3.  
This project provides secure user registration, login with JWT, bank operations like credit/debit/fund transfer, PDF bank statements, and email notifications.

---

## 🚀 Features

- ✅ User Registration with email
- 🔐 Secure Login with JWT Authentication
- 💰 Credit, Debit, and Balance Inquiry
- 🔄 Fund Transfer between accounts
- 🧾 PDF Bank Statement generation using iText
- 📩 Email Notifications for transactions & statements
- 📊 Admin-friendly transaction tracking
- 🔒 Password encryption using BCrypt

---

## 🧱 Tech Stack

| Layer        | Technology                      |
|--------------|----------------------------------|
| Language     | Java 17                          |
| Framework    | Spring Boot 3.5.3                |
| Security     | Spring Security + JWT            |
| Database     | MySQL                            |
| ORM          | Spring Data JPA                  |
| Documentation| Swagger (OpenAPI)                |
| PDF Gen      | iText 7                          |
| Email        | Spring Email API (JavaMailSender) |
| IDE          | IntelliJ IDEA                    |
| Build Tool   | Maven                            |

---

## 🗃️ Database Schema

- `User` – Stores user info like name, email, password (hashed), address, account number, balance
- `Transaction` – Logs every credit, debit, or transfer transaction with timestamp, status, type, amount

---

## 📂 Project Structure

```

src/
├── config/                # JWT, Security configurations
├── controller/            # REST API endpoints
├── dto/                   # Request/Response DTOs
├── entity/                # JPA Entities (User, Transaction)
├── repository/            # Spring Data JPA Repos
├── service/               # Business logic
├── utils/                 # Helper classes (PDF, email)

````

---

## 🔐 Authentication

- Uses **JWT** for stateless authentication
- Passwords are hashed using **BCrypt**
- Every protected route validates the bearer token in headers

---

## 📧 Email Integration

- Users receive email alerts for:
  - Successful registration
  - Every credit/debit/transfer
  - Filtered PDF account statements (attached via email)

---

## 📄 API Endpoints (sample)

| Endpoint                       | Method | Description                     |
|--------------------------------|--------|---------------------------------|
| `/api/user`                    | POST   | Register new user               |
| `/api/user/login`              | POST   | Login and get JWT token         |
| `/api/user/credit`             | POST   | Credit money to account         |
| `/api/user/debit`              | POST   | Debit money from account        |
| `/api/bank/transfer`           | POST   | Transfer money to another user  |
| `/api/user/transfer`           | GET    | Get account balance             |
| `/bankStatement?accountNumber= |
|...&startDate=...&endDate=...`` | POST   | Generate and email PDF statement|

Use Swagger: `http://localhost:8080/swagger-ui/index.html`

---

## 📦 Running Locally

### Prerequisites
- Java 17+
- MySQL database
- Maven

### Steps

```bash
# Clone the repo
git clone https://github.com/yourusername/java-om-bank.git
cd java-om-bank

# Setup DB
# Create a MySQL DB named 'bank_db' and configure your credentials in application.properties

# Build the app
mvn clean install

# Run the app
mvn spring-boot:run
````

Access: `http://localhost:8080/`

---

## 🔧 Configuration

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bank_db
spring.datasource.username=your_user
spring.datasource.password=your_password

jwt.secret=your_jwt_secret

spring.mail.username=your_email@gmail.com
spring.mail.password=your_email_password
```

------

## 🙌 Acknowledgements

* [Spring Boot](https://spring.io/projects/spring-boot)
* [iText PDF](https://itextpdf.com/)
* [JWT](https://jwt.io/)
* [Swagger](https://swagger.io/tools/swagger-ui/)
* [JavaMailSender](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/mail/javamail/JavaMailSender.html)

---

## ✍️ Author

**Om Wankhede**
Built with ☕ and 💻
Reach me at: [omwankhede.nbnssoe.it@gmail.com]
GitHub:Om_Bank

---
