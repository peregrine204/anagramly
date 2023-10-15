package org.anagramly.util;

import org.anagramly.model.AnagramResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.*;

class AnagramResultUtilsTest {
    private static final String MCDONALDS_RESTAURANTS = "McDonald's restaurants";
    private static final String UNCLE_SAMS_STANDARD_ROT = "Uncle Sam's standard rot";
    private static final String WILLIAM_SHAKESPEARE = "William Shakespeare";
    private static final String I_AM_A_WEAKISH_SPELLER = "I am a weakish speller";
    private static final String RANDOM = "jZetGlvBsqaIwyrfmqkbXizCdEblUb";
    private static final String SECOND_RANDOM = "WmkqVwITNFNJXUOdSYZwCxyiYSCDfn";
    private static final String TIME = "TiME";
    private static final String ITEM = "item";
    private static final String EMIT = "Emit";
    private static final String MITE = "mite";
    private static final String EVIL = "evil";
    private static final String VILE = "vile";

    @Test
    public void GIVEN_two_string_inputs_WHEN_not_same_length_THEN_false() {
        String firstInput = "Hello";
        String secondInput = "Hello There";

        assertThat(AnagramUtils.isAnagram(firstInput, secondInput), is(false));
    }

    @Test
    public void GIVEN_two_string_inputs_WHEN_anagram_THEN_true() {
        String firstInput = "New York Times";
        String secondInput = "monkeys write";

        assertThat(AnagramUtils.isAnagram(firstInput, secondInput), is(true));
    }

    @Test
    public void GIVEN_null_input_WHEN_checking_anagram_THEN_false() {
        assertThat(AnagramUtils.isAnagram(null, null), is(false));
    }

    @Test
    public void GIVEN_complex_inputs_WHEN_checking_anagram_THEN_results_as_expected() {
        assertThat(AnagramUtils.isAnagram(MCDONALDS_RESTAURANTS, UNCLE_SAMS_STANDARD_ROT), is(true));
        assertThat(AnagramUtils.isAnagram(WILLIAM_SHAKESPEARE, I_AM_A_WEAKISH_SPELLER), is(true));
        assertThat(AnagramUtils.isAnagram(RANDOM, SECOND_RANDOM), is(false));
    }

    @Test
    public void GIVEN_anagram_results_WHEN_filtered_THEN_matching_results_returned() {
        Set<String> expectedResults = AnagramUtils.convertResultsToUserOutput(createAnagramResults(), "time");
        assertNotNull(expectedResults);
        assertThat(expectedResults.size(), is(4));
        assertThat(expectedResults.containsAll(List.of(MITE, TIME, ITEM, EMIT)), is(true));
    }

    private List<AnagramResult> createAnagramResults() {
        AnagramResult anagramResult = new AnagramResult.AnagramResultBuilder()
                .withFirstSubmittedAnswer(MCDONALDS_RESTAURANTS)
                .withSecondSubmittedAnswer(UNCLE_SAMS_STANDARD_ROT)
                .build();
        AnagramResult secondAnagramResult = new AnagramResult.AnagramResultBuilder()
                .withFirstSubmittedAnswer(TIME)
                .withSecondSubmittedAnswer(MITE)
                .build();

        AnagramResult thirdAnagramResult = new AnagramResult.AnagramResultBuilder()
                .withFirstSubmittedAnswer(ITEM)
                .withSecondSubmittedAnswer(EMIT)
                .build();

        AnagramResult fourthAnagramResult = new AnagramResult.AnagramResultBuilder()
                .withFirstSubmittedAnswer(EVIL)
                .withSecondSubmittedAnswer(VILE)
                .build();

        AnagramResult fifthAnagramResult = new AnagramResult.AnagramResultBuilder()
                .withFirstSubmittedAnswer(WILLIAM_SHAKESPEARE)
                .withSecondSubmittedAnswer(I_AM_A_WEAKISH_SPELLER)
                .build();

        return List.of(anagramResult, secondAnagramResult, thirdAnagramResult, fourthAnagramResult, fifthAnagramResult);
    }

}