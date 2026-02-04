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

### 2. List All Tasks API
Retrieve all tasks with optional filtering capabilities.
- **Retrieve All Tasks:** Fetch the complete list of tasks stored in the system.
- **Filter by Status:** Optionally filter tasks by their status (`PENDING`, `IN_PROGRESS`, `COMPLETED`).
- **Filter by Priority:** Optionally filter tasks by priority level (`LOW`, `MEDIUM`, `HIGH`).
- **Combined Filtering:** Apply both status and priority filters simultaneously for precise results.
- **Empty Response Handling:** Returns an empty list if no tasks match the specified criteria.


-**Note**: The status and priority filtering is case-sensitive.

### Run the Application
You need to get the code and start the backend server first , should have java installed, with maven. 



 1. Clone the repository
 ```
git clone https://github.com/Jatin-Joshi-9/to-do-appliacation.git
```
 2. Navigate into the project directory
 ```
cd to-do-appliacation
```
3. Start the server 
```
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

```
{
    "title": " Enter custom Title ",
    "description": "Enter custom Description ",
    "priority": "enter custom Priority (LOW, MEDIUM, HIGH)"
}
```

### Verify the Result

Expected Success Response \(200 OK\):

```
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
```
Send empty title:
```
{
  "title": "",
  "description": "Desc",
  "priority": "HIGH"
}
```
Result: 400 Bad Request

## 2. List All Tasks API

### Test with Postman

To retrieve all tasks, send a GET request. You can use optional query parameters to filter results.

#### Case 1: Get All Tasks (No Filters)

- Open Postman.
- Create a new request.
- Method: GET
- URL: `http://localhost:8080/v1/api/tasks`

**Expected Success Response (200 OK):**

```
[
    {
        "id": "<uuid-1>",
        "title": "Complete Project Report",
        "description": "Finish and submit the quarterly project report",
        "status": "PENDING",
        "priority": "HIGH",
        "createdAt": "2026-02-04T10:30:00",
        "updatedAt": "2026-02-04T10:30:00"
    },
    {
        "id": "<uuid-2>",
        "title": "Review Code",
        "description": "Review pull requests from team members",
        "status": "IN_PROGRESS",
        "priority": "MEDIUM",
        "createdAt": "2026-02-04T11:15:00",
        "updatedAt": "2026-02-04T11:15:00"
    },
    {
        "id": "<uuid-3>",
        "title": "Update Documentation",
        "description": "Update API documentation",
        "status": "COMPLETED",
        "priority": "LOW",
        "createdAt": "2026-02-04T09:00:00",
        "updatedAt": "2026-02-04T14:00:00"
    }
]
```

#### Case 2: Filter by Status

- URL: `http://localhost:8080/v1/api/tasks?status=PENDING`

**Expected Response (200 OK):**

```json
[
    {
        "id": "<uuid-1>",
        "title": "Complete Project Report",
        "description": "Finish and submit the quarterly project report",
        "status": "PENDING",
        "priority": "HIGH",
        "createdAt": "2026-02-04T10:30:00",
        "updatedAt": "2026-02-04T10:30:00"
    }
]
```

#### Case 3: Filter by Priority

- URL: `http://localhost:8080/v1/api/tasks?priority=HIGH`

**Expected Response (200 OK):**

```json
[
    {
        "id": "<uuid-1>",
        "title": "Complete Project Report",
        "description": "Finish and submit the quarterly project report",
        "status": "PENDING",
        "priority": "HIGH",
        "createdAt": "2026-02-04T10:30:00",
        "updatedAt": "2026-02-04T10:30:00"
    }
]
```

#### Case 4: Filter by Status AND Priority

- URL: `http://localhost:8080/v1/api/tasks?status=IN_PROGRESS&priority=MEDIUM`

**Expected Response (200 OK):**

```json
[
    {
        "id": "<uuid-2>",
        "title": "Review Code",
        "description": "Review pull requests from team members",
        "status": "IN_PROGRESS",
        "priority": "MEDIUM",
        "createdAt": "2026-02-04T11:15:00",
        "updatedAt": "2026-02-04T11:15:00"
    }
]
```

#### Scenario 5: No Matching Results

- URL: `http://localhost:8080/v1/api/tasks?status=COMPLETED&priority=HIGH`

**Expected Response (200 OK):**

```json
[]
```

The endpoint returns an **empty list** when no tasks match the applied filters.




