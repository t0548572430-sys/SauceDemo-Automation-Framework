SauceDemo Advanced Automation Framework 🚀

This project implements a professional-grade automation solution for the SauceDemo e-commerce platform. It is designed with a focus on Resilience, Scalability, and Security validation, using Java, Selenium WebDriver, and TestNG.

🛠 Execution Instructions

Prerequisites: Ensure Java JDK 11+ and Maven are installed on your machine.

Environment Setup: Sensitive data and environment variables (URL, credentials) are externalized in src/test/resources/config.properties.

Execution: Run the entire suite via the testng.xml file or execute the tests package from your IDE.

Failure Analysis: A custom listener captures screenshots automatically upon test failure, saving them to the screenshots/ directory for rapid debugging.

📁 Architecture & Design Patterns

The framework strictly adheres to the Page Object Model (POM) to ensure a clean separation between test logic and UI interactions:

Infrastructure Layer: BasePage and BaseTest classes encapsulate shared behaviors (WebDriver lifecycle, synchronization, and configuration loading).

Object Repository: Each web page has a dedicated class in src/main/java/pages, promoting high reusability and low maintenance.

Test Suite: Organized by functional domains (Login, Cart, Purchase Flow, Security).

💡 Key Technical Decisions

To elevate the framework beyond basic scripting, the following professional practices were implemented:

1. Robustness Against UI Overlays (The JS Solution)
   Chrome’s built-in security alerts (e.g., leaked password warnings) often obstruct UI elements in automation. I implemented a JavaScript Executor layer for critical interactions. This bypasses physical pointer limitations, ensuring 100% click reliability even when the UI is visually obscured.

2. Intelligent Synchronization (Explicit vs. Implicit)
   I avoided unstable Thread.sleep() commands entirely. Instead, I implemented Fluent/Explicit Waits coupled with URL state validation. This ensures the tests are "aware" of the application's state before proceeding, significantly reducing flakiness.

3. Data-Driven Testing (Scalability)
   The Login module utilizes a @DataProvider architecture. This allows the suite to validate multiple personas and edge cases (locked-out users, invalid credentials, empty fields) through a single, maintainable test method.

4. Advanced Resilience & Security Edge Cases
   Beyond standard flows, I implemented a Session Integrity Test:

The Scenario: Deleting browser cookies (driver.manage().deleteAllCookies()) mid-checkout.

The Goal: To verify if the system enforces server-side session validation.

The Outcome: Proved the system's ability to protect the checkout funnel by redirecting unauthorized sessions back to the login page.