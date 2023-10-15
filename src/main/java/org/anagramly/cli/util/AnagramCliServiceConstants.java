package org.anagramly.cli.util;

public class AnagramCliServiceConstants {

    public static final String CLI_SEPARATOR = "###################################################";
    public static final String INCORRECT_OPTION_ENTERED = "The entered option %s is not correct. You must enter 1 or 2 to play Anagramly or 0 to exit";
    public static final String EXIT_MESSAGE = "Thank you for playing! All progress is wiped on system shutdown";
    public static final String ENTER_FIRST_WORD = "Please enter the first word ";
    public static final String ENTER_SECOND_WORD = "Now enter the second word: ";
    public static final String PROCESSING_ANAGRAM_MESSAGE = "Thank you! Now processing if %s and %s are anagrams";
    public static final String KEEP_PLAYING_MESSAGE = "Do you want to keep playing? 1 to check for anagrams, 2 to check if a word matches previous anagrams, or 0 to exit";
    public static final String FIND_OLD_ANAGRAMS_MESSAGE = "Please enter in the word you want to find old anagrams for: ";
    public static final String ANAGRAM_MATCHED_MESSAGE = "Woohoo! %s and %s are anagrams!";
    public static final String ANAGRAM_NOT_MATCHED_MESSAGE = "%s and %s are not anagrams of each other :(";
    public static final String NO_MATCHING_SAVED_ANAGRAMS_MESSAGE = "No matching anagram results found for: %s";
    public static final String MATCHING_ANAGRAMS_FOUND_MESSAGE = "Matching results found as follows: " + "\n" + "%s";
    public static final String INVALID_ANAGRAM_INPUT = "%s contains characters which are not letters. Please try again";
    private AnagramCliServiceConstants(){

    }


}
