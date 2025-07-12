# BrowserStack Automation Assignment — El País Opinion Section

This project is a technical automation assignment showcasing the use of **Java**, **Selenium**, **Cucumber**, **TestNG**, and **BrowserStack** to automate a real-world scraping, translation, and cross-browser testing task.

The automation performs the following:
- Scrapes Spanish news articles from *El País* (Opinion section)
- Translates article titles to English
- Extracts repeated words from translated titles
- Downloads available article cover images
- Executes automated tests in parallel across multiple browsers on BrowserStack

---

## Tech Stack

| Tool             | Description                                  |
|------------------|----------------------------------------------|
| Java 17+         | Programming language                         |
| Selenium WebDriver | Web browser automation                     |
| Cucumber         | BDD-style test structure                     |
| TestNG           | Test orchestration                           |
| Maven            | Build and dependency management              |
| BrowserStack     | Cloud cross-browser testing platform         |
| LibreTranslate / Google Translate API | Translation service     |

---

##  Setup Instructions

## 1. Clone the Repository

```bash
git clone [https://github.com/NishitG24/BrowserStack_Assignment.git](https://github.com/NishitG24/BrowserStackAssingment.git
cd BrowserStack_Assignment

# Create a file named browserstack.properties in the project root:
bs.username=YOUR_BROWSERSTACK_USERNAME
bs.key=YOUR_BROWSERSTACK_ACCESS_KEY

To Test Locally -> mvn clean test

To Execute Test on BrowserStack in Parallel Mode  -> mvn clean test -Dsurefire.suiteXmlFiles=testng-browserstack.xml

### Build Execution Screenshot: https://drive.google.com/file/d/1mcb7kmEYcuZLd-yj5n7sIl5vON5dpD6k/view?usp=sharing

### BrowserStack Automate Build Link : https://automate.browserstack.com/projects/Default+Project/builds/ElPais+Opinion+Scraper/1?tab=tests&testListView=flat

## Project Structure

BrowserStack_Assignment/
├── README.md
├── pom.xml
├── browserstack.properties        # Not committed (for credentials)
├── src/
│   └── test/
│       ├── java/
│       │   └── com.browserstack.Assignment/
│       │       ├── runner/         # Cucumber runner
│       │       ├── stepdefs/       # Step definitions
│       │       ├── hooks/          # Cucumber hooks
│       │       ├── pages/          # Page Objects: OpinionPage, ArticlePage
│       │       └── utils/          # DriverFactory, BrowserStackFactory, ImageDownloader, Translator
│       └── resources/
│           ├── features/el_pais.feature
│           └── ArticleDownloadImage/   # Folder where article images are saved




