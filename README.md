****PG Management System****
The PG Management System is a web-based application designed for managing paying guest accommodations. It provides role-based functionalities for admins and tenants, allowing user registration, login, and management of rooms, beds, and expenses.


****Admin Features****
Approve tenant registrations.
Manage room and bed allotments.
Track and manage monthly expenses (e.g., electricity, grocery, salaries).
View and download profit and loss statements as Excel reports.
Tenant Features
Register and login to the platform.
View assigned room and bed details.
Check available rooms and vacant beds.
Update profile information.

****Technologies Used****
Backend
Java 8
Spring Boot 3.4.0 (MVC, Data JPA, Security, Thymeleaf)
PostgreSQL (Database)
Frontend
Thymeleaf for rendering UI pages.
Other Tools
Maven for dependency management.
Swagger UI for API documentation.
Lombok for reducing boilerplate code.

****Setup and Installation****
Prerequisites
Java 8 or higher.
PostgreSQL installed and running.
Maven installed.
Steps to Run the Application
1. Clone the repository:
git clone https://github.com/your-username/pg-management.git
cd pg-management

2. Configure the database in application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/pg_management
spring.datasource.username=postgres
spring.datasource.password=your_password


API Endpoints
User Management
Endpoint	Method	Description
/api/users/register	POST	Register a new user
/api/users/login	POST	Login with email and password
/api/users/all	GET	Get a list of all registered users
Swagger Documentation
Visit:

bash
Copy code
http://localhost:8080/swagger-ui/index.html
Sample cURL Commands
Register a User
bash
Copy code
curl -X POST "http://localhost:8080/api/users/register" \
-H "Content-Type: application/json" \
-d '{
  "name": "John Doe",
  "email": "johndoe@example.com",
  "password": "securepassword",
  "role": "TENANT"
}'
Login
bash
Copy code
curl -X POST "http://localhost:8080/api/users/login" \
-H "Content-Type: application/json" \
-d '{
  "email": "johndoe@example.com",
  "password": "securepassword"
}'
Database Schema
users Table
Column	Type	Description
id	BIGINT	Primary key
name	VARCHAR	Name of the user
email	VARCHAR	Email address
password	VARCHAR	Hashed password
role	ENUM	User role (ADMIN or TENANT)
roomNumber	VARCHAR	Assigned room number
bedNumber	VARCHAR	Assigned bed number
status	VARCHAR	Registration status (PENDING, etc.)
Known Issues
Passwords are not hashed when Spring Security is removed (development mode).
Basic CORS configuration needed for external clients (e.g., React/Angular).
Contributors
Your Name - Developer
Other Contributors - Specify team members.
License
This project is licensed under the MIT License. See LICENSE file for details.
