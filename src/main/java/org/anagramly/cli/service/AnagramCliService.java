package org.anagramly.cli.service;

import java.util.Scanner;

public interface AnagramCliService {
    void processUserInput(Scanner scanner);
    void playFindAnagram(Scanner scanner);
    void displayOptions(Scanner scanner);
    void displayHistoricAnagrams(Scanner scanner);


}
