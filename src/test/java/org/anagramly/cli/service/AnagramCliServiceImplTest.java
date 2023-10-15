package org.anagramly.cli.service;

import org.anagramly.controller.api.AnagramlyControllerApi;
import org.anagramly.controller.ts.AnagramResultTs;
import org.anagramly.controller.ts.AnagramTs;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class AnagramCliServiceImplTest {

    @Mock
    private AnagramlyControllerApi anagramlyControllerApi;

    @Mock
    private ApplicationContext applicationContext;

    @InjectMocks
    private AnagramCliServiceImpl anagramCliService;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void reset(){
        System.setOut(standardOut);
    }

    @Test
    void GIVEN_user_input_WHEN_two_strings_are_anagrams_THEN_user_informed_of_result() throws Exception {
        String mite = "mite";
        String time = "time";
        AnagramTs anagramTs = createAnagramTs(mite, time);
        when(anagramlyControllerApi.getAnagramResult(mite, time)).thenReturn(anagramTs);
        withTextFromSystemIn("1", "mite", "time")
                .execute(() -> {
                    Scanner scanner = new Scanner(System.in);
                    anagramCliService.processUserInput(scanner);
                });
        String logOutput = outputStreamCaptor.toString();
        assertThat(logOutput, containsString("Please enter the first word"));
        assertThat(logOutput, containsString("Now enter the second word: "));
        assertThat(logOutput, containsString("Thank you! Now processing if mite and time are anagrams"));
        assertThat(logOutput, containsString("Woohoo! mite and time are anagrams!"));
        assertThat(logOutput, containsString("Do you want to keep playing? 1 to check for anagrams, 2 to check if a word matches previous anagrams, or 0 to exit"));
    }

    @Test
    void GIVEN_user_input_WHEN_invalid_THEN_user_informed() throws Exception {
        String zero = "0";
        withTextFromSystemIn(zero)
                .execute(() -> {
                    int statusCode = catchSystemExit(() -> {
                        Scanner scanner = new Scanner(System.in);
                        anagramCliService.processUserInput(scanner);
                    });
                    assertThat(statusCode, is(0));
                });
        String logOutput = outputStreamCaptor.toString();
        assertThat(logOutput, containsString("Thank you for playing! All progress is wiped on system shutdown"));
    }

    @Test
    void GIVEN_user_input_WHEN_exit_THEN_system_exit() throws Exception {
        String four = "4";
        withTextFromSystemIn(four)
                .execute(() -> {
                    Scanner scanner = new Scanner(System.in);
                    anagramCliService.processUserInput(scanner);
                });
        String logOutput = outputStreamCaptor.toString();
        assertThat(logOutput, containsString("The entered option 4 is not correct. You must enter 1 or 2 to play Anagramly or 0 to exit"));
    }

    @Test
    void GIVEN_user_input_WHEN_option_two_entered_THEN_matching_stored_anagrams_retrieved() throws Exception {
        String item = "item";
        when(anagramlyControllerApi.getMatchingAnagramResults(item)).thenReturn(createAnagramResults());
        withTextFromSystemIn("2", item)
                .execute(() -> {
                    Scanner scanner = new Scanner(System.in);
                    anagramCliService.processUserInput(scanner);
                });
        String logOutput = outputStreamCaptor.toString();
        System.out.println(logOutput);
        assertThat(logOutput, containsString("Please enter in the word you want to find old anagrams for:"));
        assertThat(logOutput, containsString("Matching results found as follows:"));
        assertThat(logOutput, containsString("mite,emit"));
        assertThat(logOutput, containsString("Do you want to keep playing? 1 to check for anagrams, 2 to check if a word matches previous anagrams, or 0 to exit"));
    }


    private AnagramTs createAnagramTs(String firstParam, String secondParam) {
        AnagramTs anagramTs = new AnagramTs();
        anagramTs.setFirstAnagramCandidate(firstParam);
        anagramTs.setSecondAnagramCandidate(secondParam);
        anagramTs.setMatched(true);
        return anagramTs;
    }

    private List<AnagramResultTs> createAnagramResults() {
        AnagramResultTs anagramResultTs = new AnagramResultTs();
        anagramResultTs.setAnagram("mite");
        AnagramResultTs secondAnagramResultTs = new AnagramResultTs();
        secondAnagramResultTs.setAnagram("emit");
        return List.of(anagramResultTs, secondAnagramResultTs);
    }
}