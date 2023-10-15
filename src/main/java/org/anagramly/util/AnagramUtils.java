package org.anagramly.util;

import org.anagramly.model.AnagramResult;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnagramUtils {

    private static final String EMPTY = "";

    private static final Pattern REMOVE_SPACES_REGEX = Pattern.compile("[^a-zA-Z]");

    private AnagramUtils() {
        //not instantiable util class
    }

    public static boolean isAnagram(String firstParam, String secondParam) {
        if (firstParam == null || secondParam == null) {
            return false;
        }

        firstParam = processInput(firstParam);
        secondParam = processInput(secondParam);

        if (firstParam.length() != secondParam.length()) {
            return false;
        }

        return Stream.of(firstParam, secondParam)
                .map(AnagramUtils::convertStrings)
                .distinct()
                .count() == 1;
    }

    private static String convertStrings(String input) {
        return Arrays.toString(input.chars().sorted().toArray());
    }

    private static String processInput(String firstParam) {
        return firstParam.replaceAll(REMOVE_SPACES_REGEX.pattern(), EMPTY).toLowerCase();
    }

    public static Set<String> convertResultsToUserOutput(List<AnagramResult> anagramResults, String searchString) {
        return anagramResults.stream()
                .filter(anagramResult -> filterResults(anagramResult, searchString))
                .map(AnagramUtils::convertToStringResults)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

    private static List<String> convertToStringResults(AnagramResult anagramResult) {
        return List.of(anagramResult.getFirstSubmittedAnswer(), anagramResult.getSecondSubmittedAnswer());
    }

    private static boolean filterResults(AnagramResult anagramResult, String searchString){
        return isAnagram(anagramResult.getFirstSubmittedAnswer(), searchString) && isAnagram(anagramResult.getSecondSubmittedAnswer(), searchString);
    }

}
