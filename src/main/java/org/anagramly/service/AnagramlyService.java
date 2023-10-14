package org.anagramly.service;

import org.anagramly.model.AnagramResult;

import java.util.List;
import java.util.Set;

public interface AnagramlyService {

    AnagramResult saveAnagram(AnagramResult anagramResult);

    List<AnagramResult> getAnagrams();

    Set<String> getMatchingAnagrams(String anagram);

    AnagramResult updateAnagram(AnagramResult anagramResult);

}
