# 🐛 BugHive TrackIt

[![Stars](https://img.shields.io/github/stars/bughive?style=social)](https://github.com/cratonik/bughive)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![React](https://img.shields.io/badge/Frontend-React-61DAFB.svg)](https://reactjs.org/)
[![Node](https://img.shields.io/badge/Backend-SpringBoot-43853D.svg)](https://nodejs.org/)
[![MongoDB](https://img.shields.io/badge/Database-MongoDB-47A248.svg)](https://mongodb.com/)

**BugHive TrackIt** is a full-stack bug tracking and project management system. **Frontend**: React.js | **Backend**: Spring Boot REST API | **Database**: PostgreSQL (users/projects/issues) + MongoDB (comments/activity).

Project managers create projects and log bugs, developers handle assignments with status workflow (Open → In Progress → Review → Done), admins get overview dashboards.

## ✨ Features

- 🔐 **Role-Based Auth**: JWT + Spring Security (Admin/Manager/Developer)
- 📋 **Projects**: CRUD, assignment to developers
- 🐞 **Issues/Bugs**: Priority (Low/Med/High/Critical), Severity, Comments, History
- 📊 **Dashboards**: Role-specific views with charts
- ⚡ **Real-time**: WebSocket updates (Spring)
- 📱 **Responsive**: Tailwind CSS + React
- 🔍 **Search/Filter**: Advanced querying
- 🧪 **API-First**: OpenAPI/Swagger docs

## 🛠️ Tech Stack

| Category     | Technologies                              |
|--------------|-------------------------------------------|
| **Frontend** | React 18, React Router, Tailwind CSS, Axios |
| **Backend**  | Spring Boot 3.x, Spring Security, JPA/Hibernate |
| **Databases**| PostgreSQL (core), MongoDB (activity log) |
| **API**      | REST + OpenAPI 3.0, JWT                   |
| **Real-time**| Spring WebSocket                          |
| **DevOps**   | Docker, Docker Compose, Maven/Gradle     |

## 🚀 Quick Start

### Prerequisites
- Node.js 18+ (frontend)
- Java 17+ / Maven 3.8+ (backend)
- PostgreSQL 15+
- MongoDB 6+
- Docker (optional)


### Clone & Setup
```bash
git clone https://github.com/cratonik/BugHive.git
cd BugHive
```

### Backend
```
cd backend
mvn clean install
cp .env.example .env  # Edit DB creds
mvn spring-boot:run
```

### Frontend
```
cd frontend
npm install
npm run dev
```





