package com.browserstack.Assignment.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Represents an article page in a web application.
 * Provides methods to retrieve the title, body content, and cover image URL of the article.
 */
public class ArticlePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final By TITLE_H1 = By.tagName("h1");
    private static final By PARAGRAPHS = By.cssSelector("article p");
    private static final By OG_IMAGE_META = By.cssSelector("meta[property='og:image']");
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public ArticlePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
    }
/**
     * Navigates to the article page using the provided URL.
     *
     * @param "url" The URL of the article page.
     */
    public String getTitle() {
        try {
            WebElement h1 = wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_H1));
            return h1.getText().trim();
        } catch (TimeoutException e) {
            System.err.println("[ArticlePage] Title not found.");
            return "";
        }
    }
/**
     * Retrieves the body content of the article.
     * The body is composed of all paragraphs within the article.
     *
     * @return A string containing the body text, with paragraphs separated by two newlines.
     */
    public String getBody() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(PARAGRAPHS));
            List<WebElement> paras = driver.findElements(PARAGRAPHS);
            return paras.stream()
                    .map(WebElement::getText)
                    .filter(p -> !p.isBlank())
                    .collect(Collectors.joining("\n\n"));
        } catch (TimeoutException e) {
            System.err.println("[ArticlePage] Body not found.");
            return "";
        }
    }
/**
     * Retrieves the cover image URL of the article.
     * The cover image is expected to be specified in the Open Graph meta tag.
     *
     * @return An Optional containing the cover image URL if present, otherwise an empty Optional.
     */
    public Optional<String> getCoverImageUrl() {
        try {
            String imageUrl = driver.findElement(OG_IMAGE_META).getAttribute("content");
            return Optional.ofNullable(imageUrl).filter(url -> !url.isBlank());
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }
}
