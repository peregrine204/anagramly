package org.anagramly.controller.mapper;

import org.anagramly.controller.ts.AnagramResultTs;
import org.anagramly.controller.ts.AnagramTs;
import org.anagramly.model.AnagramResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface AnagramMapper {

    AnagramTs toAnagramTs(String firstAnagramCandidate, String secondAnagramCandidate, boolean matched);

    @Mapping(target = "firstSubmittedAnswer", source = "firstAnagramCandidate")
    @Mapping(target = "secondSubmittedAnswer", source = "secondAnagramCandidate")
    AnagramResult toAnagramResult(String firstAnagramCandidate, String secondAnagramCandidate);

    AnagramResultTs toAnagramResultTs(String anagram);

    List<AnagramResultTs> toAnagramResults(Set<String> anagrams);
}
