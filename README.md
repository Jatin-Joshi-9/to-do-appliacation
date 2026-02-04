# To Do Application
## Problem Statement

Design and implement a robust RESTful API for a Task Management System (backend only). The application must follow the Model-View-Controller \(MVC\) pattern where the "View" is JSON responses. No database; use in-memory storage. Demonstrate strong object-oriented design and Clean Architecture.

### Tech stack used
- Language: [Java 17](https://www.oracle.com/in/java/technologies/downloads/#java17)
- Framework: [Spring Boot 4.0.2](https://spring.io/blog/2026/01/22/spring-boot-4-0-2-available-now)
- Build Tool: [Maven](https://maven.apache.org/)
- Architecture: [MVC](https://en.wikipedia.org/wiki/Model-view-controller)
### Task model
Each task must include:
- UUID id
- title (required, max 100 characters)
- description (required, max 500 characters)
- status: one of `PENDING`, `IN_PROGRESS`, `COMPLETED`
- priority: one of `LOW`, `MEDIUM`, `HIGH`
- timestamps: `createdAt`, `updatedAt`

### API requirements
- Create Task
    - Validate input
    - Prevent duplicate titles (case-insensitive)
- List All Tasks
    - Support optional filtering by `status` and/or `priority`
- Get Single Task
    - Retrieve by UUID
- Update Task
    - Support partial updates (PATCH semantics)
    - Validate updated fields
    - Update `updatedAt`
- Delete Task
    - Remove task by UUID
    - Return appropriate status code 

### Non-functional requirements
- Clear separation of concerns (controllers, services, repositories, models)
- Clean, well-documented code
- oop principles implementation


## Features Implemented

### 1. Create Task API
Allows users to create new tasks with strict validation rules.
- **Auto-generated ID:** Each task gets a unique UUID.
- **Timestamps:** Automatically records `createdAt` and `updatedAt`.
- **Default Status:** New tasks are set to `PENDING` by default.
- **Duplicate Prevention:** The system checks if a task with the same title already exists before saving.
- **Input Validation:** Ensures titles and descriptions meet length requirements.

```markdown
### Run the Application
You need to get the code and start the backend server first , should have java installed, with maven. 


```bash
# 1. Clone the repository
git clone https://github.com/Jatin-Joshi-9/to-do-appliacation.git

# 2. Navigate into the project directory
cd to-do-appliacation

# 3. Start the server (Using Maven Wrapper)
mvn spring-boot:run
```

Success: You will see Started TodoApplication in ... seconds in your terminal. The server is now running at http://localhost:8080.

### Test with Postman
To create a task, send a POST request with the JSON payload shown below.

- Open Postman.
- Create a new request.
- Method: POST
- URL: `http://localhost:8080/v1/api/tasks/`
- Body: raw `JSON` and paste the payload.

```json
{
    "title": " Enter custom Title ",
    "description": "Enter custom Description ",
    "priority": "enter custom Priority (LOW, MEDIUM, HIGH)"
}
```

### Verify the Result

Expected Success Response \(200 OK\):

```json
{
    "id": "<random-uuid>",
    "title": "your custom Title",
    "description": "your custom Description",
    "status": "PENDING",
    "priority": "your custom Priority",
    "createdAt": "current-timestamp",
    "updatedAt": "current-timestamp"
}
```

Expected Failure \(Duplicate Title\):

Plaintext:


Duplicate Task Failure
Send the same request twice
```
Result: 500 Internal Server Error

Logs show IllegalArgumentException
Validation Failure
Send empty title:
{
  "title": "",
  "description": "Desc",
  "priority": "HIGH"
}
Result: 400 Bad Request

```