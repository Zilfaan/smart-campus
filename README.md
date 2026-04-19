
# Smart Campus Sensor & Room Management API
![Java](https://img.shields.io/badge/Java-17%2B-blue?logo=java)
![JAX-RS](https://img.shields.io/badge/JAX--RS-3.1-green?logo=jakartaee)
![Maven](https://img.shields.io/badge/Maven-Build--Tool-C71A36?logo=apachemaven)

---

## Overview
This project was built as a part of the requirement for the Coursework submission for the Client-Server Architectures Module (5COSC022W), offered by the University of Westminster. It contains A RESTful API for managing rooms, sensors, and sensor readings for a university "Smart Campus".


---

## Video Demonstration
- [INSERT URL](TODO)

---

## Tech Stack
- Java 17+
- JAX-RS (Jersey)
- Maven
- Servlet Container (Tomcat, Payara, GlassFish, etc.)
---

## Quickstart

1. Clone the repository:
   ```sh
   git clone https://github.com/Zilfaan/smart-campus.git
   cd smart-campus
   ```
2. Build the project with Maven:
   ```sh
   mvn clean package
   ```

3. Download and extract Apache Tomcat (if you don't have it):
   - Download from https://tomcat.apache.org/download-90.cgi
   - Extract the ZIP file (e.g., to `C:\tomcat`)

4. Deploy the WAR file to Tomcat using Command Prompt:
   ```cmd
   copy target\smart-campus-1.0-SNAPSHOT.war C:\tomcat\webapps\smart-campus.war
   ```

5. Start Tomcat (from the Tomcat bin directory):
   ```cmd
   cd C:\tomcat\bin
   startup.bat
   ```

6. Access the API in your browser or with curl:
   - http://localhost:8080/smart-campus/api/v1/

---

## API Endpoints

| Method | Endpoint                          | Description                       |
|--------|-----------------------------------|-----------------------------------|
| GET    | /api/v1/                         | Discovery & API info              |
| GET    | /api/v1/rooms                    | List all rooms                    |
| POST   | /api/v1/rooms                    | Create a new room                 |
| GET    | /api/v1/rooms/{id}               | Get room details                  |
| DELETE | /api/v1/rooms/{id}               | Delete a room (only if empty)     |
| GET    | /api/v1/sensors                  | List all sensors (filter by type) |
| POST   | /api/v1/sensors                  | Register a new sensor             |
| GET    | /api/v1/sensors/{id}/readings    | Get readings for a sensor         |
| POST   | /api/v1/sensors/{id}/readings    | Add a new reading                 |

---

---

README Last updated 13/04/2026
