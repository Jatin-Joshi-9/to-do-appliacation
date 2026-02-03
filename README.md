# To Do Application
## Problem Statement

Design and implement a robust RESTful API for a Task Management System (backend only). The application must follow the Model-View-Controller \(MVC\) pattern where the "View" is JSON responses. No database; use in-memory storage. Demonstrate strong object-oriented design and Clean Architecture.

### Tech stack
- Java
- Spring Boot
- Maven

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


### implementation steps
1. Set up Spring Boot project with Maven. (completed)
2. Define Task model with required fields. (completed)