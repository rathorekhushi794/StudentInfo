A Java + MySQL Student Information System upgraded to a Spring Boot REST API, built with Maven and Docker Compose.

Supports CRUD operations (Add, Delete, Update, Get, List Students) via REST endpoints.

🚀 Features

Add a new student

Delete a student by ID

Update student details

Fetch a student by ID

List all students

Data stored in MySQL database (inside Docker)

Java app containerized with Dockerfile

Project orchestrated with Docker Compose

Testable with Postman or curl

📁 Project Structure
StudentInfo/
├─ src/main/java/com/studentinfo/
│  ├─ StudentInfoApiApplication.java   # Main Spring Boot app
│  ├─ Student.java                     # Student entity (JPA)
│  ├─ StudentRepository.java           # Repository (CRUD)
│  └─ StudentController.java           # REST endpoints
├─ src/main/resources/
│  └─ application.properties           # DB & JPA config
├─ pom.xml                             # Maven dependencies
├─ Dockerfile                          # Container for Java app
├─ docker-compose.yml                  # Docker Compose (MySQL + API)
└─ README.md

⚙️ Prerequisites

Java 17+

Maven

Docker & Docker Compose

Git installed

🔨 Build & Run
1️⃣ Clone the Repository
git clone https://github.com/<your-username>/StudentInfo.git
cd StudentInfo

2️⃣ Build the JAR
mvn clean package -DskipTests


Creates target/app.jar.

3️⃣ Start Docker Containers
docker-compose up --build


Starts MySQL (database studentdb)

Builds & runs Spring Boot API on port 8080

🌐 REST API Endpoints
1️⃣ Add Student
POST http://localhost:8080/students
Content-Type: application/json

{
  "name": "John Doe",
  "age": 20,
  "course": "CS"
}

2️⃣ List All Students
GET http://localhost:8080/students

3️⃣ Get Student by ID
GET http://localhost:8080/students/{id}

4️⃣ Update Student
PUT http://localhost:8080/students/{id}
Content-Type: application/json

{
  "name": "John Smith",
  "age": 22,
  "course": "Data Science"
}

5️⃣ Delete Student
DELETE http://localhost:8080/students/{id}

🧪 Testing

Use Postman to send requests and inspect responses

Or use curl in terminal:

curl -X POST http://localhost:8080/students \
     -H "Content-Type: application/json" \
     -d '{"name":"John","age":20,"course":"CS"}'

🐳 Docker Commands

List running containers:

docker ps


Stop containers:

docker-compose down