Feature: El Pais Opinion Feature

  Background: Navigate to El Pais Opinion page
    Given I open the browser
    And I navigate to "https://elpais.com/"

  Scenario: verify page title
    Then  I verify the page title is "EL PAÍS: el periódico global"

  Scenario: Extract and process articles
    Given I open the El País "Opinión" section
    When I scrape the first 5 articles
    Then I print each title and article content in Spanish
    And I download cover images if available

  Scenario: Translate and Print Article Headers
    Given I open the El País "Opinión" section
    When I scrape the first 5 articles
    When I translate each article title to English
    Then I print each translated title
    And I print repeated words across all translated titles


