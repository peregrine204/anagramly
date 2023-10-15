package org.anagramly.service;

import org.anagramly.model.AnagramResult;
import org.anagramly.repository.AnagramRepository;
import org.anagramly.util.AnagramUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AnagramlyServiceImpl implements AnagramlyService {

    private final AnagramRepository anagramRepository;

    public AnagramlyServiceImpl(AnagramRepository anagramRepository) {
        this.anagramRepository = anagramRepository;
    }

    @Override
    public AnagramResult saveAnagram(AnagramResult anagramResult) {
        return anagramRepository.save(anagramResult);
    }

    @Override
    public List<AnagramResult> getAnagrams() {
        return (List<AnagramResult>) anagramRepository.findAll();
    }

    @Override
    public Set<String> getMatchingAnagrams(String anagram) {
        return AnagramUtils.convertResultsToUserOutput(getAnagrams(), anagram);
    }

    @Override
    public AnagramResult updateAnagram(AnagramResult anagramResult) {
        return anagramRepository.save(anagramResult);
    }
}
