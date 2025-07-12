package com.browserstack.Assignment.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TextAnalyzer {
    public static Map<String, Long> findRepeatedWords(List<String> texts) {
        return texts.stream()
                .flatMap(t -> Arrays.stream(t.toLowerCase().split("\\W+")))
                .filter(w -> w.length() > 2)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
    }
}
