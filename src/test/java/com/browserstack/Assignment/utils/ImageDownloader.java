package com.browserstack.Assignment.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.UUID;

/**
 * ImageDownloader.java
 * This class handles downloading images from a given URL and saving them to a specified directory.
 * The directory is dynamically set based on the current working directory.
 */
public class ImageDownloader {
    // Define base path dynamically using user.dir
    private static final String DOWNLOAD_PATH = System.getProperty("user.dir") +
            "/src/test/resources/ArticleDownloadImage/";

    /**
     * Downloads an image from the provided URL and saves it to the download directory.
     *
     * @param imageUrl The URL of the image to download.
     */
    public static void downloadImage(Object imageUrl) {
        try {
            // Ensure the download directory exists
            File downloadFolder = new File(DOWNLOAD_PATH);
            if (!downloadFolder.exists()) {
                boolean created = downloadFolder.mkdirs();
                if (created) {
                    System.out.println("📂 Created download directory: " + DOWNLOAD_PATH);
                }
            }

            // Generate unique filename
            String filename = "cover_" + UUID.randomUUID() + ".jpg";
            File destination = new File(downloadFolder, filename);

            // Download the image
            FileUtils.copyURLToFile(new URL((String) imageUrl), destination);
            System.out.println("✅ Image saved at: " + destination.getAbsolutePath());

        } catch (Exception e) {
            System.err.println("❌ Failed to download image: " + e.getMessage());
        }
    }
}
