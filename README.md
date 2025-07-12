# BrowserStack Automation Assignment — El País Opinion Section

This project is a technical automation assignment showcasing the use of **Java**, **Selenium**, **Cucumber**, **TestNG**, and **BrowserStack** to automate a real-world scraping, translation, and cross-browser testing task.

### 🧠 What It Does

- Scrapes Spanish news articles from *El País* (Opinion section)
- Translates article titles to English
- Extracts repeated words from translated titles
- Downloads available article cover images (if present)
- Executes automated tests in parallel across multiple desktop and mobile browsers on BrowserStack

---

## Tech Stack

| Tool / Library                   | Purpose                                 |
|----------------------------------|-----------------------------------------|
| Java 17+                         | Programming Language                    |
| Selenium WebDriver               | Web browser automation                  |
| Cucumber                         | BDD-style test definition               |
| TestNG                           | Test orchestration                      |
| Maven                            | Dependency and build management         |
| BrowserStack Automate            | Cloud-based cross-browser testing       |
| LibreTranslate / Google Translate API | Translation service (configurable) |

---

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/NishitG24/BrowserStack_Assignment.git
cd BrowserStack_Assignment

**###  2. Create the Credentials File**

Create a file named browserstack.properties in the project root directory:

bs.username=YOUR_BROWSERSTACK_USERNAME
bs.key=YOUR_BROWSERSTACK_ACCESS_KEY

**###  3. To Run Tests Locally**

mvn clean test


**###  4. To Execute Tests on BrowserStack (Parallel)**

mvn clean test -Dsurefire.suiteXmlFiles=testng-browserstack.xml

**### Submission Link:**

Google Drive Link : https://drive.google.com/file/d/1mcb7kmEYcuZLd-yj5n7sIl5vON5dpD6k/view?usp=sharing

BrowserStack Automate Link : https://automate.browserstack.com/projects/Default+Project/builds/ElPais+Opinion+Scraper/1?tab=tests&testListView=flat


**### Project Structure **

BrowserStack_Assignment/
├── README.md
├── pom.xml
├── browserstack.properties         # (local file only – not committed)
├── src/
│   └── test/
│       ├── java/
│       │   └── com.browserstack.Assignment/
│       │       ├── runner/             # Cucumber TestNG Runner
│       │       ├── stepdefs/           # Step Definitions
│       │       ├── hooks/              # Setup / Teardown hooks
│       │       ├── pages/              # Page Objects: OpinionPage, ArticlePage
│       │       └── utils/              # Driver, Translator, ImageDownloader, etc.
│       └── resources/
│           ├── features/el_pais.feature
│           └── ArticleDownloadImage/   # Downloaded article cover images





