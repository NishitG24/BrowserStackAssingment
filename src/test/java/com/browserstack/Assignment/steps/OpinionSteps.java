package com.browserstack.Assignment.steps;

import com.browserstack.Assignment.pages.ArticlePage;
import com.browserstack.Assignment.pages.OpinionPage;
import com.browserstack.Assignment.utils.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * OpinionSteps class contains step definitions for scraping articles from the El País Opinion section.
 * It handles navigation, article scraping, translation, and text analysis.
 */
public class OpinionSteps {

    OpinionPage opinionPage = new OpinionPage(DriverFactory.getDriver());
    List<String> articleLinks = new ArrayList<>();
    List<String> articleTitlesEs = new ArrayList<>();
    List<String> articleTitlesEn = new ArrayList<>();
    private final List<ArticleData> scrapedArticles = new ArrayList<ArticleData>();

    @Given("I open the browser")
    public void i_open_the_browser() {
        // handled by hooks
    }
    @Given("I navigate to {string}")
    public void i_navigate_to(String url) {
        DriverFactory.getDriver().get(url);
    }
    @Then("I verify the page title is {string}")
    public void i_verify_the_page_title_is(String expectedTitle) {
        String actualTitle = DriverFactory.getDriver().getTitle();
        if (!actualTitle.equals(expectedTitle)) {
            throw new AssertionError("Expected title: " + expectedTitle + ", but got: " + actualTitle);
        }
    }


    @Given("I open the El País {string} section")
    public void iOpenTheElPaísSection(String OpinionText) {
        opinionPage.navigateToOpinionSection(OpinionText);
    }
    @When("I scrape the first {int} articles")
    public void i_scrape_the_first_articles(Integer int1) {
        articleLinks = opinionPage.getFirstNArticleLinks(int1);

        for(String link : articleLinks) {
            WebDriver driver = DriverFactory.getDriver();
            driver.get(link);
            ArticlePage articlePage = new ArticlePage(driver);

            String titleEs = articlePage.getTitle();
            String bodyEs  = articlePage.getBody();

            Optional<String> imageUrl = articlePage.getCoverImageUrl();

            scrapedArticles.add(new ArticleData(link, titleEs, bodyEs, imageUrl));

            if (!titleEs.isBlank()) {
                articleTitlesEs.add(titleEs);
            }

        }
    }
    @Then("I print each title and article content in Spanish")
    public void i_print_each_title_and_article_content_in_spanish() {
        scrapedArticles.forEach(a -> {
            System.out.println("\n=====  " + a.getTitleEs() + "  =====");
            System.out.println(a.getBodyEs());
        });
    }
    @Then("I download cover images if available")
    public void i_download_cover_images_if_available() {
        for (ArticleData a : scrapedArticles) {
            a.getImageUrl().ifPresent(url -> {
                System.out.println("Downloading: " + url);
                ImageDownloader.downloadImage(url);
            });
        }

    }

    @When("I translate each article title to English")
    public void i_translate_each_article_title_to_english() {
        for (String titleEs : articleTitlesEs) {
            String titleEn = Translator.translate(titleEs);
            articleTitlesEn.add(titleEn);
        }
    }
    @Then("I print each translated title")
    public void i_print_each_translated_title() {
        for (String title : articleTitlesEn) {
            System.out.println("English Translated Title " + title);
        }
    }
    @Then("I print repeated words across all translated titles")
    public void i_print_repeated_words_across_all_translated_titles() {
        Map<String, Long> freq = TextAnalyzer.findRepeatedWords(articleTitlesEn);

        System.out.println("Repeated Words (more than twice):");
        freq.entrySet().stream()
                .filter(e -> e.getValue() > 2)
                .forEach(e -> System.out.println("• " + e.getKey() + ": " + e.getValue()));
    }

}

