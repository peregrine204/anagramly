package org.anagramly.service;

import org.anagramly.model.AnagramResult;
import org.anagramly.util.AnagramUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AnagramlyServiceImpl implements AnagramlyService {


    List<AnagramResult> anagramResults = new ArrayList<>();
    @Override
    public AnagramResult saveAnagram(AnagramResult anagramResult) {
        anagramResults.add(anagramResult);
        return anagramResult;
    }

    @Override
    public List<AnagramResult> getAnagrams() {
        return anagramResults;
    }

    @Override
    public Set<String> getMatchingAnagrams(String anagram) {
        return AnagramUtils.convertResultsToUserOutput(anagramResults, anagram);
    }

    @Override
    public AnagramResult updateAnagram(AnagramResult anagramResult) {
        return null;
    }
}
