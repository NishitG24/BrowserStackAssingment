package com.browserstack.Assignment.utils;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
/*
**
 * Translator class to handle translation of text from Spanish to English using Google Cloud Translate API.
 * It initializes the Translate service with credentials and provides a method to translate text.
 */
public class Translator {

    private static Translate translateService;

    static {
        try {
            // Set the path to your service account key
            String jsonPath = System.getProperty("user.dir") + "/src/test/resources/browserstackassignmentTranslate_Cred.json";
            System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", jsonPath);

            translateService = TranslateOptions.getDefaultInstance().getService();
            System.out.println("Google Translate initialized.");
        } catch (Exception e) {
            System.err.println("Failed to initialize Google Translate: " + e.getMessage());
        }
    }

    // Translate a given text from Spanish to English
    public static String translate(String textEs) {
        if (translateService == null) {
            return "[UNTRANSLATED] " + textEs;
        }

        try {
            Translation translation = translateService.translate(
                    textEs,
                    Translate.TranslateOption.sourceLanguage("es"),
                    Translate.TranslateOption.targetLanguage("en"),
                    Translate.TranslateOption.model("nmt") // Neural Machine Translation
            );
            return translation.getTranslatedText();
        } catch (Exception e) {
            System.err.println("Translation error: " + e.getMessage());
            return "[UNTRANSLATED] " + textEs;
        }
    }
}