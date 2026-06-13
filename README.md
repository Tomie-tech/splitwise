# Splitwise Clone API

A REST API backend for expense splitting, built with Spring Boot and MySQL.

## Tech Stack
- Java 21
- Spring Boot 3.5
- Spring Data JPA / Hibernate
- MySQL
- Spring Security + JWT Authentication
- Maven

## Features
- User registration and login with JWT auth
- Create groups and add members
- Add expenses to groups — auto-split equally among members
- View all expenses in a group
- Settle individual splits

## API Endpoints

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /register | Register a new user |
| POST | /login | Login and receive JWT token |

### Groups
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /groups | Create a group |
| GET | /groups | Get all groups for current user |

### Expenses
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /expenses | Add an expense and split equally |
| GET | /expenses/group/{groupId} | Get all expenses in a group |
| PUT | /expenses/splits/{splitId}/settle | Mark a split as settled |

## Setup
1. Clone the repo
2. Create MySQL database: `CREATE DATABASE splitwise;`
3. Copy `application.properties.example` to `application.properties`
4. Update your MySQL credentials
5. Run the application

## Example Request

### Create Group
```json
POST /groups
Authorization: Bearer <token>

{
  "name": "Goa Trip",
  "memberIds": [2, 3]
}
```

### Add Expense
```json
POST /expenses
Authorization: Bearer <token>

{
  "description": "Hotel",
  "amount": 3000,
  "groupId": 1,
  "splitAmongIds": [1, 2, 3]
}
```
