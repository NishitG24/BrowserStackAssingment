package com.browserstack.Assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * OpinionPage class to interact with the Opinion section of El País.
 * It provides methods to scrape article links from the Opinion section.
 */
public class OpinionPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public OpinionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Navigates to the Opinion section of El País.
     *
     * @param LinkText the text of the link to click
     */
    public void navigateToOpinionSection(String LinkText) {
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(LinkText)));
     driver.findElements(By.partialLinkText(LinkText)).get(0).click();
    }
    /**
     * Gets the first N article links from the Opinion section.
     *
     * @param n the number of article links to retrieve
     * @return a list of article links
     */
    public List<String> getFirstNArticleLinks(int n) {
        List<String> articleLinks = new ArrayList<>();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("article > header > h2 > a")));

        List<WebElement> articles = driver.findElements(By.cssSelector("article > header > h2 > a"));
        for (WebElement article : articles) {
            String href = article.getAttribute("href");
            if (href != null && !href.isEmpty() && !articleLinks.contains(href)) {
                articleLinks.add(href);
                if (articleLinks.size() >= n) break;
            }
        }

        return articleLinks;
    }
}
