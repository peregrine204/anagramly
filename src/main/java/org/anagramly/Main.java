package org.anagramly;

import org.anagramly.controller.AnagramlyController;
import org.anagramly.controller.mapper.AnagramMapperImpl;
import org.anagramly.controller.ts.AnagramResultTs;
import org.anagramly.controller.ts.AnagramTs;
import org.anagramly.service.AnagramlyServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<String> ALLOWED_INPUTS = List.of("1", "2", "0");

    public static void main(String[] args) {
        AnagramlyController controller = new AnagramlyController(new AnagramMapperImpl(), new AnagramlyServiceImpl());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Anagramly! Please choose your operation. 1 to check for anagrams, 2 to check if a word matches previous anagrams found, or 0 to exit: ");

        processUserInput(controller, scanner);
    }

    private static void processUserInput(AnagramlyController controller, Scanner scanner) {
        while (scanner.hasNext()) {
            String param = scanner.nextLine();
            if (IsInvalidOptionEntered(param)) {
                System.out.println("The entered option " + param + " is not correct. You must enter 1 or 2 to play Anagramly or 0 to exit");
                processUserInput(controller, scanner);
            } else if (param.equalsIgnoreCase("1")) {
                processAnagram(controller, scanner);
                presentOptions(controller, scanner);
            } else if (param.equalsIgnoreCase("2")) {
                getHistoricAnagrams(controller, scanner);
                presentOptions(controller, scanner);
            } else if (param.equalsIgnoreCase("0")) {
                System.out.println("Thank you for playing! All progress is wiped on system shutdown");
                System.exit(0);
            }
        }
    }

    private static void presentOptions(AnagramlyController controller, Scanner scanner) {
        System.out.println("Do you want to keep playing? 1 to check for anagrams, 2 to check if a word matches previous anagrams, or 0 to exit");

        processUserInput(controller, scanner);
    }

    private static void getHistoricAnagrams(AnagramlyController controller, Scanner scanner) {
        System.out.println("Please enter in the word you want to find old anagrams for: ");
        String input = getInput(scanner);

        List<AnagramResultTs> anagramResultTsList = controller.getMatchingAnagramResults(input);

        processHistoricResults(anagramResultTsList, input);

    }

    private static void processHistoricResults(List<AnagramResultTs> anagramResultTsList, String input) {
        List<String> anagrams = anagramResultTsList.stream()
                .map(AnagramResultTs::getAnagram)
                .toList();

        if (anagrams.isEmpty()) {
            System.out.println("No matching anagram results found for: " + input);
        } else {
            System.out.println("Matching results found as follows: " + "\n" + String.join(",", anagrams));
        }

    }

    private static void processAnagram(AnagramlyController controller, Scanner scanner) {
        System.out.println("Please enter the first word ");
        String firstInput = getInput(scanner);
        System.out.println("Now enter the second word: ");
        String secondInput = getInput(scanner);
        System.out.println("Thank you! Now processing if " + firstInput + " and " + secondInput + " are anagrams");

        AnagramTs result = controller.getAnagramResult(firstInput, secondInput);

        processResult(result);
    }

    private static void processResult(AnagramTs result) {
        if (result.isMatched()) {
            System.out.println("Woohoo! " + result.getFirstAnagramCandidate() + " and " + result.getSecondAnagramCandidate() + " are anagrams!");
        } else {
            System.out.println(result.getFirstAnagramCandidate() + " and " + result.getSecondAnagramCandidate() + " are not anagrams of each other :(");
        }
    }

    private static boolean isNotValid(String s) {
        return s.chars()
                .anyMatch(Character::isDigit);
    }

    private static boolean IsInvalidOptionEntered(String option) {
        if (option.chars().noneMatch(Character::isDigit) || option.chars().toArray().length > 1) {
            return true;
        }
        return !ALLOWED_INPUTS.contains(option);
    }

    private static String getInput(Scanner scanner) {
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (isNotValid(input)) {
                System.out.println(input + " contains characters which are not letters. Please try again");
            } else {
                return input;
            }
        }
        return null;
    }
}