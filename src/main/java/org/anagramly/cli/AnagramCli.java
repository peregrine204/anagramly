package org.anagramly.cli;

import org.anagramly.cli.service.AnagramCliService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

import static org.anagramly.cli.util.AnagramCliServiceConstants.CLI_SEPARATOR;

@Component
public class AnagramCli implements CommandLineRunner {
    private final AnagramCliService anagramCliService;

    public AnagramCli(AnagramCliService anagramCliService) {
        this.anagramCliService = anagramCliService;
    }

    @Override
    public void run(String... args) {
        System.out.println(CLI_SEPARATOR);
        System.out.println("Welcome to Anagramly! Please choose your operation. 1 to check for anagrams, 2 to check if a word matches previous anagrams found, or 0 to exit: ");

        anagramCliService.processUserInput(new Scanner(System.in));
    }

}
