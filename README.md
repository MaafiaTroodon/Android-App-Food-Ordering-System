# 📌 Mobile Application for Account Registration & Authentication

### 🏆 Software Engineering Project 
### 📅 Winter 2025 | Developer: **Malhar Mahajan**  
---
## 📖 Overview
This project is a mobile application developed using **Android Studio** that allows users to register using their **Dalhousie email address**, set a **valid password**, and select a role (**Buyer** or **Seller**). It ensures data validation, password hashing, and secure storage in **Firebase**. Additionally, it implements **Test-Driven Development (TDD)** using **JUnit, Espresso, and UIAutomator**.

---

## 🚀 Features Implemented
1. **User Registration**
   - Validates email, password, and role before registration.
   - Stores user credentials securely in **Firebase** with **SHA-256 password hashing**.
   - Redirects to a **welcome screen** upon successful registration.

2. **Validation Checks**
   - Email format validation (must be a valid Dalhousie email `@dal.ca`).
   - Password strength validation.
   - Ensures a valid role (`Buyer` or `Seller`) is selected.

3. **Retrieve Credentials**
   - Fetches stored user details from **Firebase**.
   - Displays user **email-password hash-role** in a **Snackbar**.

4. **Testing Frameworks**
   - **JUnit**: Unit testing for individual functions.
   - **Espresso**: UI testing for button clicks, form validation, and activity navigation.
   - **UIAutomator**: End-to-end testing, ensuring UI components behave correctly.

---

## 🛠️ Tech Stack
- **Java (Android Development)**
- **Firebase Realtime Database**
- **JUnit 4.13.2 (Unit Testing)**
- **Espresso 3.6.1 (UI Testing)**
- **UIAutomator 2.3.0 (UI Testing)**
- **Android Studio Ladybug — 2024.2.1 Patch 3**
- **Gradle 8.7.3**
- **JDK 17**
- **Android 14 + API Level 34**
- **Emulator: API Level 35 + Pixel 2**

---

## 🔧 Setup & Installation

### Step 1: Clone the Repository  
Fork the repository on **GitLab**, then clone it using:  
```bash
git clone https://github.com/MaafiaTroodon/Android-App-Food-Ordering-System.git

