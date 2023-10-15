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
    private static final List<String> ALLOWED_INPUTS = List.of("1", "2", "0");

    public AnagramCliServiceImpl(AnagramlyControllerApi anagramlyControllerApi, ApplicationContext applicationContext) {
        this.anagramlyControllerApi = anagramlyControllerApi;
        this.applicationContext = applicationContext;
    }

    @Override
    public void processUserInput(Scanner scanner) {
        while (scanner.hasNext()) {
            String param = scanner.nextLine();
            if (IsInvalidOptionEntered(param)) {
                System.out.printf((INCORRECT_OPTION_ENTERED) + "%n", param);
                processUserInput(scanner);
            } else if (param.equalsIgnoreCase("1")) {
                System.out.println(CLI_SEPARATOR);
                playFindAnagram(scanner);
                displayOptions(scanner);
            } else if (param.equalsIgnoreCase("2")) {
                displayHistoricAnagrams(scanner);
                displayOptions(scanner);
            } else if (param.equalsIgnoreCase("0")) {
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
        String firstInput = getInput(scanner);
        System.out.println(ENTER_SECOND_WORD);
        String secondInput = getInput(scanner);
        System.out.printf((PROCESSING_ANAGRAM_MESSAGE) + "%n", firstInput, secondInput);

        System.out.println(CLI_SEPARATOR);

        AnagramTs result = anagramlyControllerApi.getAnagramResult(firstInput, secondInput);

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
        String input = getInput(scanner);

        List<AnagramResultTs> anagramResultTsList = anagramlyControllerApi.getMatchingAnagramResults(input);

        processHistoricResults(anagramResultTsList, input);
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
        return !ALLOWED_INPUTS.contains(option);
    }

    private String getInput(Scanner scanner) {
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (isNotValid(input)) {
                System.out.printf((INVALID_ANAGRAM_INPUT) + "%n", input);
            } else {
                return input;
            }
        }
        return null;
    }
}
