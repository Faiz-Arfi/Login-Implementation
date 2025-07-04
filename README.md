# Login Implementation Project

## Project Overview
![Page Layout](/src/main/resources/static/images/Page-Layout.png)


This is a secure user authentication system built with Spring Boot that provides login and registration functionality. It demonstrates best practices in implementing user authentication with proper security measures in a modern web application.

## Technologies Used

- **Backend**: 
  - Spring Boot 3.5.0
  - Spring Security
  - Spring Data JPA
  - Spring Web MVC
  - Thymeleaf templating engine

- **Frontend**:
  - HTML5
  - CSS3
  - Thymeleaf for server-side rendering

- **Database**:
  - MySQL

- **Tools & Environment**:
  - Java 21 (compatible with Java 22)
  - Maven for dependency management
  - BCrypt for password hashing

## Architecture

This application follows the MVC (Model-View-Controller) architecture pattern:

1. **Models**: Represent the data and business logic
   - `User.java`: Defines the user entity with fields for ID, fullName, email, password, and creation timestamp

2. **Views**: Thymeleaf templates for rendering the UI
   - `home.html`: Landing page with links to login and register
   - `login.html`: Authentication form
   - `register.html`: New user registration form
   - `userPage.html`: Dashboard accessible only to authenticated users

3. **Controllers**: Handle user requests and return appropriate responses
   - `PageController.java`: Manages page navigation and form submissions

## Key Features

### 1. User Registration
- Input validation (email format, password length)
- Password encryption using BCrypt
- Email uniqueness validation
- Feedback messages for registration success/failure

### 2. User Authentication
- Form-based login with Spring Security
- CSRF protection
- Session management
- Login failure handling

### 3. Access Control
- Public access to home, login, and registration pages
- Protected dashboard accessible only to authenticated users
- Logout functionality

### 4. Database Integration
- JPA/Hibernate ORM
- User data persistence
- Automatic schema updates

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/arfiProjects/Login_Implementation/
│   │       ├── Configuration/
│   │       │   └── SecurityConfig.java   # Spring Security configuration
│   │       ├── Controller/
│   │       │   └── PageController.java   # Request handling
│   │       ├── model/
│   │       │   └── User.java             # User entity
│   │       ├── repository/
│   │       │   └── UserRepository.java   # Data access
│   │       ├── service/
│   │       │   ├── CustomUserDetailsService.java  # User authentication
│   │       │   └── UserService.java      # User management
│   │       └── LoginImplementationApplication.java # Main application
│   └── resources/
│       ├── application.properties        # Core application config
│       ├── application-dev.properties    # Development environment config
│       ├── application-prod.properties   # Production environment config
│       ├── static/
│       │   ├── css/                      # Stylesheets
│       │   └── images/                   # Images and diagrams
│       └── templates/
│           ├── home.html                 # Landing page
│           ├── login.html                # Login page
│           ├── register.html             # Registration page
│           └── User/
│               └── userPage.html         # User dashboard
```

## How It Works

### Authentication Flow

1. **User Registration**:
   - User submits registration form with full name, email, and password
   - System validates input fields (email format, password strength)
   - System checks if email is already in use
   - On success, password is hashed using BCrypt and user is saved to database
   - User is redirected to login page

2. **User Login**:
   - User submits login form with email and password
   - Spring Security's authentication manager validates credentials
   - CustomUserDetailsService loads user details from database
   - BCryptPasswordEncoder verifies password
   - On success, user is authenticated and session is created
   - User is redirected to dashboard

3. **Protected Resource Access**:
   - All requests to protected URLs are intercepted by Spring Security
   - System checks if user is authenticated
   - If authenticated, request proceeds to the handler
   - If not authenticated, user is redirected to login page

4. **Logout**:
   - User clicks logout button
   - Session is invalidated
   - User is redirected to login page

## Security Features

- **Password Storage**: BCrypt hashing with salt
- **CSRF Protection**: Cross-Site Request Forgery prevention with CSRF tokens
- **Session Management**: Automatic session timeout and handling
- **Authentication Controls**: Login attempt management
## Database Configuration

The application supports multiple environments through profile-specific properties:

- `application-dev.properties`: Development environment settings
- `application-prod.properties`: Production environment settings

The MySQL connection is configured with:
- Automatic database creation
- Connection pooling with HikariCP
- Public key retrieval enabled for secure authentication

## Getting Started

### Prerequisites
- Java 21 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

### Running the Application

1. Clone the repository
2. Configure MySQL connection in `application-dev.properties`
3. Run the application using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
   
4. Access the application at `http://localhost:8080`

### Running the JAR File Locally

After building the application with `./mvnw clean package`, you can run the generated JAR file:

#### Basic Command

```powershell
java -jar .\target\Login-Implementation-0.0.1-SNAPSHOT.jar
```

#### Running with Environment Variables in PowerShell

Set environment variables for database credentials and active profile:

```powershell
# Set environment variables for the current PowerShell session
$env:ENV = "dev"
$env:DBUSERNAME = "yourDBUserName"
$env:DBPASSWORD = "yourSecurePassword"

# Run the JAR
java -jar .\target\Login-Implementation-0.0.1-SNAPSHOT.jar
```

You can also set all variables in a single line:

```powershell
$env:ENV="dev"; $env:DBUSERNAME="yourDBUserName"; $env:DBPASSWORD="yourSecurePassword"; java -jar .\target\Login-Implementation-0.0.1-SNAPSHOT.jar
```

## Future Enhancements

- Email verification for new registrations
- Password reset functionality
- OAuth2 integration for social login
- User profile management
- Role-based authorization