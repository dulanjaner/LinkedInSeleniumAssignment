# LinkedIn Selenium Automation Testing

## Project Description
This project automates selected LinkedIn functionalities using Selenium WebDriver and TestNG. It demonstrates automated browser testing, Page Object Model (POM), explicit waits, login testing, search functionality, result interaction, and screenshot capture.

## Technologies Used
- Java 21
- Selenium WebDriver 4.51.0
- TestNG 7.9.0
- Maven
- Google Chrome
- IntelliJ IDEA

## Prerequisites
Before running the project, ensure that you have installed:
- Java Development Kit (JDK) 17 or later
- Apache Maven, if not using the Maven wrapper
- Google Chrome
- IntelliJ IDEA or another Java IDE

## Configuration

The tests use environment variables to obtain LinkedIn login credentials.

Configure these environment variables in your IDE or execution environment:

- `LINKEDIN_USERNAME` — LinkedIn login email or username
- `LINKEDIN_PASSWORD` — LinkedIn login password

Do not hardcode credentials in the source code or commit them to GitHub.

## How to Run

1. Clone or download this repository.
2. Open the project in IntelliJ IDEA.
3. Allow Maven to resolve the project dependencies.
4. Configure the required environment variables.
5. Run the desired TestNG test classes.
6. Review the test results in the IDE.

## Test Coverage

The project includes automated tests related to:
- Browser initialization
- Login functionality
- Search functionality
- Search results page verification
- Interaction with a search result
- Screenshot capture when a test fails

The actual outcome of each test depends on the current LinkedIn website behavior, account status, and authentication requirements.

## Screenshots

The project saves screenshots in the `screenshots` directory when the relevant test code or failure listener is executed. The screenshots directory is excluded from Git tracking.

## Project Structure

```text
LinkedInSeleniumAssignment/
├── src/
│   └── test/
│       └── java/
│           ├── BaseTest.java
│           ├── BrowserTest.java
│           ├── LoginPage.java
│           ├── LoginPageCheck.java
│           ├── LoginTest.java
│           ├── SearchPage.java
│           ├── SearchResultTest.java
│           ├── SearchTest.java
│           └── TestListener.java
├── .gitignore
├── pom.xml
└── README.md
```

## Notes
- LinkedIn may display additional verification or security challenges during automated browser sessions.
- Tests require valid credentials and a working internet connection.
- Do not publish personal login credentials, session cookies, or other sensitive information.