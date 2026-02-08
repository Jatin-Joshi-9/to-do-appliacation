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
- **Case-Insensitive Input:** Status and priority values can be entered in any case format (e.g., pending, PENDING, Pending all will work).

### 2. List All Tasks API
Retrieve all tasks with optional filtering capabilities.
- **Retrieve All Tasks:** Fetch the complete list of tasks stored in the system.
- **Filter by Status:** Optionally filter tasks by their status (`PENDING`, `IN_PROGRESS`, `COMPLETED`).
- **Filter by Priority:** Optionally filter tasks by priority level (`LOW`, `MEDIUM`, `HIGH`).
- **Combined Filtering:** Apply both status and priority filters simultaneously for precise results.
- **Empty Response Handling:** Returns an empty list if no tasks match the specified criteria.

### 3. Get Single Task API
Retrieve a specific task by its unique identifier.
- **Retrieve by UUID:** Fetch a single task using its unique ID.
- **Error Handling:** Returns 404 Not Found if the task doesn't exist.
- **Complete Task Details:** Returns all task information including title, description, status, priority, and timestamps.

### 4. Update Task API
Modify existing tasks with flexible update options.
- **Partial Updates:** Update only the fields you need to change.
- **Automatic Timestamp Update:** The updatedAt field is automatically updated on every modification.
- **Field Validation:** Ensures updated fields meet all validation requirements.
- **Case-Insensitive Input:** Status and priority can be provided in any case format.

### 5. Delete Task API
Remove tasks from the system permanently.
- **Delete by UUID:** Remove a task using its unique identifier.
- **Success Response:** Returns 204 No Content when deletion is successful.
- **Error Handling:** Returns 404 Not Found if the task doesn't exist.


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

## 1. Create Task API

To create a task, send a POST request with the JSON payload shown below.

- Open Postman.
- Create a new request.
- Method: POST
- URL: `http://localhost:8080/v1/api/tasks`
- Body: raw `JSON` and paste the payload.

```
{
    "title": " Enter custom Title ",
    "description": "Enter custom Description ",
    "status": "PENDING"
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
    "priority": "High",
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

---

## 3. Update Task API

### Test with Postman

To update an existing task, send a PUT request with the task ID in the URL.

#### Case 1: Successful Update

First, create a task 
- Open Postman.
- Create a new request.
- Method: PUT
- URL: `http://localhost:8080/v1/api/tasks/{task-id}`
  - Replace `{task-id}` with the actual UUID of the task you want to update
- Body: raw `JSON` and paste the payload.

**Request Body:**

```json
{
    "title": "Updated Project Report",
    "description": "Finish and submit the quarterly project report with new requirements",
    "priority": "MEDIUM",
    "status": "IN_PROGRESS"
}
```

**Expected Success Response (200 OK):**

```json
{
    "id": "<same-uuid>",
    "title": "Updated Project Report",
    "description": "Finish and submit the quarterly project report with new requirements",
    "status": "IN_PROGRESS",
    "priority": "MEDIUM",
    "createdAt": "2026-02-04T10:30:00",
    "updatedAt": "2026-02-05T14:20:00"
}
```

**Note:** The `updatedAt` timestamp is automatically updated to reflect the modification time.

#### Case 2: Update Only Title and Description

You can update specific fields while keeping others unchanged:

- URL: `http://localhost:8080/v1/api/tasks/{task-id}`

**Request Body:**

```json
{
    "title": "Review and Merge Code",
    "description": "Review all pending pull requests and merge approved ones",
    "priority": "HIGH"
}
```

**Expected Response (200 OK):**

The task will be updated with the new title, description, and priority. If status is not provided, it remains unchanged from the existing value.

#### Case 3: Change Status

To mark a task as completed:

- URL: `http://localhost:8080/v1/api/tasks/{task-id}`

**Request Body:**

```json
{
    "title": "Complete Documentation",
    "description": "Finalize all API documentation",
    "priority": "LOW",
    "status": "COMPLETED"
}
```

**Expected Response (200 OK):**

```json
{
    "id": "<task-uuid>",
    "title": "Complete Documentation",
    "description": "Finalize all API documentation",
    "status": "COMPLETED",
    "priority": "LOW",
    "createdAt": "2026-02-04T09:00:00",
    "updatedAt": "2026-02-05T16:45:00"
}
```
=================================================================
## 4. Get Single Task API

### Test with Postman

To retrieve a specific task by its ID, send a GET request with the task UUID in the URL.

#### Case 1: Successful Retrieval

- Open Postman.
- Create a new request.
- Method: GET
- URL: `http://localhost:8080/v1/api/tasks/{task-id}`
  - Replace `{task-id}` with the actual UUID of the task you want to retrieve

**Expected Success Response (200 OK):**

```json
{
    "id": "<task-uuid>",
    "title": "Complete Project Report",
    "description": "Finish and submit the quarterly project report",
    "status": "PENDING",
    "priority": "HIGH",
    "createdAt": "2026-02-04T10:30:00",
    "updatedAt": "2026-02-04T10:30:00"
}
```

#### Case 2: Task Not Found

- URL: `http://localhost:8080/v1/api/tasks/{invalid-task-id}`

**Expected Response (404 Not Found):**

The endpoint returns a `404 Not Found` error when the specified task ID doesn't exist in the system.

---

## 4. Delete Task API

### Test with Postman

To delete a task, send a DELETE request with the task ID in the URL.

#### Case 1: Successful Deletion

- Open Postman.
- Create a new request.
- Method: DELETE
- URL: `http://localhost:8080/v1/api/tasks/{task-id}`
  - Replace `{task-id}` with the actual UUID of the task you want to delete

**Expected Success Response (204 No Content):**

The endpoint returns `204 No Content` when the task is successfully deleted. No response body is returned.

#### Case 2: Task Not Found

- URL: `http://localhost:8080/v1/api/tasks/{invalid-task-id}`

**Expected Response (404 Not Found):**

The endpoint returns a `404 Not Found` error when attempting to delete a task that doesn't exist in the system.

---

## API Endpoints Summary

| Method | Endpoint | Description | Success Response |
|--------|----------|-------------|------------------|
| POST | `/v1/api/tasks` | Create a new task | 200 OK |
| GET | `/v1/api/tasks` | Get all tasks (with optional filters) | 200 OK |
| GET | `/v1/api/tasks/{id}` | Get a single task by ID | 200 OK |
| PUT | `/v1/api/tasks/{id}` | Update an existing task | 200 OK |
| DELETE | `/v1/api/tasks/{id}` | Delete a task | 204 No Content |

---

## Additional Notes

- **Case-Insensitive Input:** All enum values (status and priority) can be entered in any case format (lowercase, uppercase, or mixed case).
- **Input Validation:** The API validates all inputs and returns appropriate error messages for invalid data.
- **Error Responses:** The API returns standard HTTP status codes (400 for validation errors, 404 for not found, 500 for server errors).
- **In-Memory Storage:** All data is stored in memory and will be lost when the server is restarted.







