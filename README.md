# 🏔️ TrekSafe (Trekking Safety Web App)

A Spring Boot web application designed to help trekkers find verified trails, navigate offline, and access emergency safety features.

## 🚀 Overview

This project addresses safety concerns in the trekking community by providing:
* **Trail Search:** Verified database of trekking trails with difficulty levels and reviews.
* **Offline Navigation:** GPX-based map rendering using Leaflet.js and OpenStreetMap.
* **Emergency SOS:** A simulated alert system that logs user coordinates for rescue.
* **User Accounts:** Secure login/signup with JWT authentication and Google OAuth.

## 🛠️ Tech Stack

* **Backend:** Java 17, Spring Boot 3
* **Database:** PostgreSQL (with PostGIS support recommended)
* **Frontend:** HTML5, CSS (Bootstrap), JavaScript (Vanilla)
* **Mapping:** Leaflet.js, OpenStreetMap
* **Build Tool:** Maven

## ⚙️ Quick Setup

### 1. Database Configuration
You need a PostgreSQL database running locally. Run this SQL to set it up:

```sql
CREATE DATABASE trek_app;
CREATE USER trek_user WITH PASSWORD 'trekapp2025';
GRANT ALL PRIVILEGES ON DATABASE trek_app TO trek_user;

\c trek_app
CREATE SCHEMA treksafe;
GRANT ALL ON SCHEMA treksafe TO trek_user;
