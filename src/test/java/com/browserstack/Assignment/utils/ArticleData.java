package com.browserstack.Assignment.utils;

import java.util.Optional;

/**
 * ArticleData class represents the data structure for storing article information.
 * It includes the URL, title in Spanish, body in Spanish, and an optional image URL.
 */
public class ArticleData {
    private final String url;
    private final String titleEs;
    private final String bodyEs;
    private final Optional<String> imageUrl;

    public ArticleData(String url, String titleEs, String bodyEs, Optional<String> imageUrl) {
        this.url = url;
        this.titleEs = titleEs;
        this.bodyEs = bodyEs;
        this.imageUrl = imageUrl;
    }

    public String getUrl() { return url; }
    public String getTitleEs() { return titleEs; }
    public String getBodyEs() { return bodyEs; }
    public Optional<String> getImageUrl() { return imageUrl; }
}

