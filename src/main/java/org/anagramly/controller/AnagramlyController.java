package org.anagramly.controller;

import org.anagramly.controller.api.AnagramlyControllerApi;
import org.anagramly.controller.mapper.AnagramMapper;
import org.anagramly.controller.ts.AnagramResultTs;
import org.anagramly.controller.ts.AnagramTs;
import org.anagramly.model.AnagramResult;
import org.anagramly.service.AnagramlyService;
import org.anagramly.util.AnagramUtils;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AnagramlyController implements AnagramlyControllerApi {

    private final AnagramMapper anagramMapper;
    private final AnagramlyService anagramlyService;

    public AnagramlyController(AnagramMapper anagramMapper, AnagramlyService anagramlyService) {
        this.anagramMapper = anagramMapper;
        this.anagramlyService = anagramlyService;
    }

    @Override
    public AnagramTs getAnagramResult(String firstParam, String secondParam) {
        boolean isAnagram = AnagramUtils.isAnagram(firstParam, secondParam);
        if (isAnagram) {
            AnagramResult anagramResult = anagramlyService.saveAnagram(anagramMapper.toAnagramResult(firstParam, secondParam));
            return anagramMapper.toAnagramTs(anagramResult.getFirstSubmittedAnswer(), anagramResult.getSecondSubmittedAnswer(), true);
        }
        return anagramMapper.toAnagramTs(firstParam, secondParam, false);
    }

    @Override
    public List<AnagramResultTs> getMatchingAnagramResults(String anagram) {
        return anagramMapper.toAnagramResults(anagramlyService.getMatchingAnagrams(anagram));
    }
}
