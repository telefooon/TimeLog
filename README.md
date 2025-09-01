⏱️ TimeLog Application

A Spring Boot + MongoDB application that allows users to register, log in, and manage their daily journal entries.
It uses Spring Security with BCrypt encryption for authentication and ensures transaction safety when updating users and journals.

🚀 Features

🔐 User Registration & Login (Basic Auth)

📝 Create, Update, Delete Journal Entries

💾 MongoDB persistence with transactions

✅ Secure password hashing (BCrypt)

🔗 REST API endpoints ready for frontend/mobile integration

⚙️ How It Works
1. Application Startup

Spring Boot initializes with @SpringBootApplication

Security config, transaction manager, services, and repositories are loaded as beans

2. User Registration
POST /public/create-user
{
  "username": "john_doe",
  "password": "mypassword123",
  "email": "john@example.com"
}


Password is hashed with BCrypt

Default role USER assigned

User saved into users collection

3. Login & Authentication
POST /login
Authorization: Basic base64(username:password)


Spring Security extracts credentials

Looks up user from MongoDB via UserDetailsService

Verifies password using BCrypt

If valid → authentication stored in SecurityContext

4. Create Journal Entry
POST /journal/john_doe
Authorization: Basic <base64>
{
  "title": "My First Day",
  "content": "Today was amazing! I learned Spring Boot."
}


Journal entry saved in journal_entries collection

User document updated with reference to the new entry

Both operations run inside a transaction

5. Update Journal Entry
PUT /journal/id/john_doe/{id}
{
  "title": "Updated Title",
  "content": ""
}


Finds the existing entry

Updates only non-empty fields

Saves back to MongoDB

6. Delete Journal Entry
DELETE /journal/id/john_doe/{id}


Removes reference from user’s journal list

Deletes the journal entry document

🗄️ Database Design

Users Collection

{
  "username": "john_doe",
  "password": "$2a$10$hashedPassword...",
  "roles": ["USER"],
  "journalEntries": [ ObjectId("...") ]
}


Journal Entries Collection

{
  "title": "My First Day",
  "content": "Today was amazing! I learned Spring Boot.",
  "date": "2025-09-01T10:30:00"
}

🧩 Tech Stack

Backend: Spring Boot (Java 17)

Database: MongoDB

Security: Spring Security (Basic Auth + BCrypt)

⚡ Getting Started
Prerequisites

Java 17+

MongoDB running locally (mongodb://localhost:27017/timelog)

Run the Application
git clone https://github.com/your-username/timelog.git
cd timelog
./mvnw spring-boot:run

API Endpoints

POST /public/create-user → Register user

POST /login → Login with Basic Auth

POST /journal/{username} → Create entry

PUT /journal/id/{username}/{id} → Update entry

DELETE /journal/id/{username}/{id} → Delete entry
