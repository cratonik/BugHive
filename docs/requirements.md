# Requirements Specification

## Core Features
- Authentication & Role Management (Admin/Manager/Developer)
- Project CRUD operations
- Issue/Bug tracking (create/assign/update)
- Status workflow: Open → In Progress → Review → Done
- Priority (Low/Medium/High/Critical) & Severity levels

## Database Schema
**PostgreSQL** (Users, Projects, Issues):
```sql
users: id, email, password_hash, role, created_at
projects: id, name, manager_id, created_at
issues: id, title, description, project_id, assignee_id, status, priority, severity
```

**MongoDB**  (Comments/Activity/History):
```sql
comments: {issue_id, user_id, text, timestamp}
activity: {issue_id, action, user_id, timestamp}
history: {issue_id, status_changes[]}
```
