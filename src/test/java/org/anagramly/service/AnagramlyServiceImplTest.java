package org.anagramly.service;

import org.anagramly.model.AnagramResult;
import org.anagramly.repository.AnagramRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Set;

import static org.anagramly.AnagramTestHelper.createAnagramResults;
import static org.anagramly.AnagramTestHelper.singleAnagramResult;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnagramlyServiceImplTest {

    @Mock
    private AnagramRepository anagramRepository;

    @InjectMocks
    private AnagramlyServiceImpl anagramlyServiceImpl;

    private static List<AnagramResult> anagramResults;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @BeforeAll
    public static void setup(){
        anagramResults = createAnagramResults();
    }

    @Test
    void GIVEN_anagram_searched_for_WHEN_get_matching_THEN_matching_anagrams_returned() {
        when(anagramRepository.findAll()).thenReturn(anagramResults);
        Set<String> anagrams = anagramlyServiceImpl.getMatchingAnagrams("Time");
        assertNotNull(anagrams);
        assertThat(anagrams.size(), is(5));
        assertThat(anagrams.containsAll(List.of("Item", "Emit", "Mite", "Etmi", "Time")), is(true));
    }

}