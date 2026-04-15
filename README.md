# 🚀 SauceDemo Advanced Automation Framework

This project implements a **professional-grade automation solution** for the SauceDemo e-commerce platform. It is engineered with a focus on **Resilience, Scalability, and Security validation**, leveraging the power of **Java, Selenium WebDriver, and TestNG**.

---

## 🛠 Execution Instructions

### 📋 Prerequisites
* **Java JDK 11+**
* **Apache Maven** installed and configured in your environment.

### ⚙️ Environment Setup
* **Externalized Configuration:** Sensitive data and environment variables (URL, credentials) are managed via `src/test/resources/config.properties`.

### 🏃 Running the Suite
* **via TestNG:** Run the entire suite using the `testng.xml` file.
* **via Maven:** Execute `mvn clean test` from the terminal.
* **via IDE:** Run the `tests` package directly from your preferred IDE.

> [!IMPORTANT]  
> **Failure Analysis:** A custom listener captures screenshots automatically upon test failure, saving them to the `screenshots/` directory for rapid debugging.

---

## 📁 Architecture & Design Patterns

The framework strictly adheres to the **Page Object Model (POM)** to ensure a clean separation between test logic and UI interactions.

### 🏗️ Infrastructure Layer
Classes like `BasePage` and `BaseTest` encapsulate shared behaviors, including:
* WebDriver lifecycle management.
* Dynamic synchronization (Waits).
* Global configuration loading.

### 📦 Object Repository
Each web page has a dedicated class in `src/main/java/pages`, promoting:
* **High Reusability:** Logic is defined once and used across multiple tests.
* **Low Maintenance:** UI changes only require updates in a single Page class.

### 🧪 Test Suite organization
The suite is logically segmented into functional domains:
* **Login:** Authentication and Data-Driven scenarios.
* **Cart:** Persistence and integrity.
* **Purchase Flow:** End-to-End business logic.
* **Security:** Session resilience.

---

## 💡 Key Technical Decisions

### 1️⃣ Robustness Against UI Overlays (The JS Solution)
Chrome’s built-in security alerts (e.g., leaked password warnings) can obstruct UI elements. I implemented a **JavaScript Executor layer** for critical interactions. This bypasses physical pointer limitations, ensuring **100% click reliability** even when the UI is visually obscured.

### 2️⃣ Intelligent Synchronization
I strictly avoided unstable `Thread.sleep()` commands. Instead, I implemented **Fluent/Explicit Waits** coupled with **URL state validation**. This ensures tests are "aware" of the application's state, significantly reducing flakiness.

### 3️⃣ Data-Driven Testing (Scalability)
The Login module utilizes a `@DataProvider` architecture. This allows the suite to validate multiple personas and edge cases (locked-out users, invalid credentials, empty fields) through a **single, maintainable test method**.

### 4️⃣ Advanced Resilience & Security Edge Cases
Beyond standard flows, I implemented a **Session Integrity Test**:
* **The Scenario:** Deleting browser cookies mid-checkout.
* **The Goal:** To verify if the system enforces server-side session validation.
* **The Outcome:** Proved the system's ability to protect the checkout funnel by redirecting unauthorized sessions back to the login page.

---
*Created with ❤️ by Tammar Chen.*
