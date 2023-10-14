package org.anagramly.util;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class AnagramResultUtilsTest {

    @Test
    public void GIVEN_two_string_inputs_WHEN_not_same_length_THEN_false(){
        String firstInput = "Hello";
        String secondInput = "Hello There";

        assertThat(AnagramUtils.isAnagram(firstInput, secondInput), is(false));
    }
    @Test
    public void GIVEN_two_string_inputs_WHEN_anagram_THEN_true(){
        String firstInput = "New York Times";
        String secondInput = "monkeys write";

        assertThat(AnagramUtils.isAnagram(firstInput, secondInput), is(true));
    }

}