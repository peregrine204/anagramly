package org.anagramly.controller;

import org.anagramly.controller.mapper.AnagramMapper;
import org.anagramly.controller.ts.AnagramTs;
import org.anagramly.model.AnagramResult;
import org.anagramly.service.AnagramlyService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

class AnagramlyControllerTest {

    @Mock
    private AnagramMapper anagramMapper;

    @Mock
    private AnagramlyService anagramlyService;

    @InjectMocks
    private AnagramlyController anagramlyController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void GIVEN_two_anagrams_WHEN_get_matching_THEN_return_result() {
        String firstParam = "time";
        String secondParam = "item";
        AnagramResult anagramResult = createAnagramResult(firstParam, secondParam);
        when(anagramlyService.saveAnagram(any())).thenReturn(anagramResult);
        when(anagramMapper.toAnagramTs(anagramResult.getFirstSubmittedAnswer(), anagramResult.getSecondSubmittedAnswer(), true))
                .thenReturn(createAnagramResultTs(firstParam, secondParam, true));
        AnagramTs anagramTs = anagramlyController.getAnagramResult(firstParam, secondParam);
        assertThat(anagramTs.getFirstAnagramCandidate(), is(firstParam));
        assertThat(anagramTs.getSecondAnagramCandidate(), is(secondParam));
        assertThat(anagramTs.isMatched(), is(true));
    }

    @Test
    void GIVEN_two_anagrams_WHEN_get_not_matching_THEN_return_result() {
        String firstParam = "time";
        String secondParam = "zone";
        AnagramResult anagramResult = createAnagramResult(firstParam, secondParam);
        when(anagramlyService.saveAnagram(any())).thenReturn(anagramResult);
        when(anagramMapper.toAnagramTs(anagramResult.getFirstSubmittedAnswer(), anagramResult.getSecondSubmittedAnswer(), false))
                .thenReturn(createAnagramResultTs(firstParam, secondParam, false));
        AnagramTs anagramTs = anagramlyController.getAnagramResult(firstParam, secondParam);
        assertThat(anagramTs.getFirstAnagramCandidate(), is(firstParam));
        assertThat(anagramTs.getSecondAnagramCandidate(), is(secondParam));
        assertThat(anagramTs.isMatched(), is(false));
    }

    private AnagramTs createAnagramResultTs(String firstParam, String secondParam, boolean matched) {
        AnagramTs anagramTs = new AnagramTs();
        anagramTs.setFirstAnagramCandidate(firstParam);
        anagramTs.setSecondAnagramCandidate(secondParam);
        anagramTs.setMatched(matched);
        return anagramTs;
    }

    private AnagramResult createAnagramResult(String firstParam, String secondParam) {
        AnagramResult anagramResult = new AnagramResult();
        anagramResult.setFirstSubmittedAnswer(firstParam);
        anagramResult.setSecondSubmittedAnswer(secondParam);
        return anagramResult;
    }
}