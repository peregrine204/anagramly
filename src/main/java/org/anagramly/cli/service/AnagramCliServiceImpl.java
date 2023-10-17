package org.anagramly.cli.service;

import org.anagramly.controller.api.AnagramlyControllerApi;
import org.anagramly.controller.ts.AnagramResultTs;
import org.anagramly.controller.ts.AnagramTs;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

import static org.anagramly.cli.util.AnagramCliServiceConstants.*;

@Service
public class AnagramCliServiceImpl implements AnagramCliService {

    private Scanner scanner;
    private final AnagramlyControllerApi anagramlyControllerApi;
    private final ApplicationContext applicationContext;
    private static final List<String> ALLOWED_NUMERIC_INPUTS = List.of("1", "2", "0");

    public AnagramCliServiceImpl(AnagramlyControllerApi anagramlyControllerApi, ApplicationContext applicationContext) {
        this.anagramlyControllerApi = anagramlyControllerApi;
        this.applicationContext = applicationContext;
    }

    @Override
    public void processUserInput(Scanner scanner) {
        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (IsInvalidOptionEntered(userInput)) {
                System.out.printf((INCORRECT_OPTION_ENTERED) + "%n", userInput);
                processUserInput(scanner);
            } else if (userInput.equalsIgnoreCase("1")) {
                System.out.println(CLI_SEPARATOR);
                playFindAnagram(scanner);
                displayOptions(scanner);
            } else if (userInput.equalsIgnoreCase("2")) {
                displayHistoricAnagrams(scanner);
                displayOptions(scanner);
            } else if (userInput.equalsIgnoreCase("0")) {
                System.out.println(EXIT_MESSAGE);
                scanner.close();
                int exitCode = SpringApplication.exit(applicationContext, () -> 0);
                System.exit(exitCode);
            }
        }
    }


    @Override
    public void playFindAnagram(Scanner scanner) {
        System.out.println(ENTER_FIRST_WORD);
        String firstAnswer = getUserInput(scanner);
        System.out.println(ENTER_SECOND_WORD);
        String secondAnswer = getUserInput(scanner);
        System.out.printf((PROCESSING_ANAGRAM_MESSAGE) + "%n", firstAnswer, secondAnswer);

        System.out.println(CLI_SEPARATOR);

        AnagramTs result = anagramlyControllerApi.getAnagramResult(firstAnswer, secondAnswer);

        processResult(result);
    }

    @Override
    public void displayOptions(Scanner scanner) {
        System.out.println(KEEP_PLAYING_MESSAGE);
        processUserInput(scanner);
    }

    @Override
    public void displayHistoricAnagrams(Scanner scanner) {
        System.out.println(FIND_OLD_ANAGRAMS_MESSAGE);
        String userInput = getUserInput(scanner);

        List<AnagramResultTs> anagramResultTsList = anagramlyControllerApi.getMatchingAnagramResults(userInput);

        processHistoricResults(anagramResultTsList, userInput);
    }

    private void processResult(AnagramTs result) {
        if (result.isMatched()) {
            System.out.printf((ANAGRAM_MATCHED_MESSAGE) + "%n", result.getFirstAnagramCandidate(), result.getSecondAnagramCandidate());
        } else {
            System.out.printf((ANAGRAM_NOT_MATCHED_MESSAGE) + "%n", result.getFirstAnagramCandidate(), result.getSecondAnagramCandidate());
        }
    }

    private void processHistoricResults(List<AnagramResultTs> anagramResults, String input) {
        List<String> anagrams = anagramResults.stream()
                .map(AnagramResultTs::getAnagram)
                .filter(anagram -> !anagram.equalsIgnoreCase(input))
                .toList();

        if (anagrams.isEmpty()) {
            System.out.printf((NO_MATCHING_SAVED_ANAGRAMS_MESSAGE) + "%n", input);
        } else {
            System.out.printf((MATCHING_ANAGRAMS_FOUND_MESSAGE) + "%n", String.join(",", anagrams));
        }

    }

    private boolean isNotValid(String s) {
        return s.chars()
                .anyMatch(Character::isDigit);
    }

    private boolean IsInvalidOptionEntered(String option) {
        if (option.chars().noneMatch(Character::isDigit) || option.chars().toArray().length > 1) {
            return true;
        }
        return !ALLOWED_NUMERIC_INPUTS.contains(option);
    }

    private String getUserInput(Scanner scanner) {
        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (isNotValid(userInput)) {
                System.out.printf((INVALID_ANAGRAM_INPUT) + "%n", userInput);
            } else {
                return userInput;
            }
        }
        return null;
    }
}
