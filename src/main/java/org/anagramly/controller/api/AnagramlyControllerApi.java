package org.anagramly.controller.api;

import org.anagramly.controller.ts.AnagramResultTs;
import org.anagramly.controller.ts.AnagramTs;

import java.util.List;

public interface AnagramlyControllerApi {

    AnagramTs getAnagramResult(String firstParam, String secondParam);

    List<AnagramResultTs> getMatchingAnagramResults(String anagram);

}
