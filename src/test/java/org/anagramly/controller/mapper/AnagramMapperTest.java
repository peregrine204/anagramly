package org.anagramly.controller.mapper;

import org.anagramly.controller.ts.AnagramResultTs;
import org.anagramly.controller.ts.AnagramTs;
import org.anagramly.model.AnagramResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AnagramMapperImpl.class)
class AnagramMapperTest {

    @Autowired
    private AnagramMapper anagramMapper;

    private static final String FIRST_PARAM = "time";
    private static final String SECOND_PARAM = "emit";

    @Test
    void GIVEN_anagram_found_WHEN_to_anagram_ts_THEN_anagram_ts_returned() {
        AnagramTs anagramTs = anagramMapper.toAnagramTs(FIRST_PARAM, SECOND_PARAM, true);
        assertThat(anagramTs.getFirstAnagramCandidate(), is(FIRST_PARAM));
        assertThat(anagramTs.getSecondAnagramCandidate(), is(SECOND_PARAM));
        assertThat(anagramTs.isMatched(), is(true));
    }

    @Test
    void GIVEN_anagram_WHEN_mapped_to_anagram_result_THEN_anagram_result_mapped() {
        AnagramResult anagramResult = anagramMapper.toAnagramResult(FIRST_PARAM, SECOND_PARAM);
        assertThat(anagramResult.getFirstSubmittedAnswer(), is(FIRST_PARAM));
        assertThat(anagramResult.getSecondSubmittedAnswer(), is(SECOND_PARAM));
        assertThat(anagramResult.getId(), is(nullValue()));
    }

    @Test
    void GIVEN_anagram_is_searched_WHEN_to_anagram_results_THEN_anagram_results_are_mapped() {
        Set<String> anagrams = Set.of("time", "emit", "item", "mite", "etmi");
        List<AnagramResultTs> anagramResultTsList = anagramMapper.toAnagramResults(anagrams);
        assertThat(anagramResultTsList.size(), is(5));
        anagramResultTsList.forEach(anagramResultTs -> assertAnagramResultTs(anagramResultTs.getAnagram(), anagrams::contains));
    }

    private void assertAnagramResultTs(String anagram, Predicate<String> stringPredicate) {
        assertThat(stringPredicate.test(anagram), is(true));
    }

}