# Portfolio Backend - Code by Gustav

Backend API för min fullstack portfolio-applikation.

## Tech Stack
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- MySQL
- Maven
- Lombok

## Setup

### 1. Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+

### 2. Create Database
```sql
CREATE DATABASE portfolio_db;
CREATE USER 'portfolio_user'@'localhost' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON portfolio_db.* TO 'portfolio_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Configure Database
Update `src/main/resources/application.properties` if needed.

### 4. Install Dependencies
```bash
mvn clean install
```

### 5. Run Application
```bash
mvn spring-boot:run
```

Application runs on: http://localhost:8080

## Test Endpoints
- `GET http://localhost:8080/api/hello`
- `GET http://localhost:8080/api/status`

## API Endpoints

### Projects
- `GET /api/projects` - Get all projects
- `GET /api/projects/{id}` - Get project by id
- `GET /api/projects/featured` - Get featured projects
- `POST /api/projects` - Create new project
- `PUT /api/projects/{id}` - Update project
- `DELETE /api/projects/{id}` - Delete project

### Contact
- `POST /api/contact` - Submit contact form
- `GET /api/contact` - Get all messages (admin)
- `GET /api/contact/unread` - Get unread messages (admin)
- `PUT /api/contact/{id}/read` - Mark message as read (admin)

### Skills
- `GET /api/skills` - Get all skills
- `GET /api/skills/category/{category}` - Get skills by category
- `GET /api/skills/top` - Get skills ordered by proficiency
- `POST /api/skills` - Create new skill
- `DELETE /api/skills/{id}` - Delete skill

## Project Structure
```
src/main/java/se/gustavnyberg/portfolio/
├── controller/     - REST Controllers
├── model/          - JPA Entities
├── repository/     - Spring Data Repositories
└── service/        - Business Logic (future expansion)
```

## Author
Gustav Nyberg - JAVA24

## License
Educational project
