package org.anagramly.repository;

import org.anagramly.model.AnagramResult;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.anagramly.AnagramTestHelper.createAnagramResults;
import static org.anagramly.AnagramTestHelper.singleAnagramResult;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
class AnagramRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private AnagramRepository anagramRepository;

    private static List<AnagramResult> anagramResultList;

    private static AnagramResult singleAnagramResult;

    @BeforeAll
    public static void setup(){
        anagramResultList = createAnagramResults();
        singleAnagramResult = singleAnagramResult();
    }


    @Test
    public void GIVEN_anagram_result_WHEN_saved_THEN_saved_result_returned(){
        AnagramResult savedAnagramResult = anagramRepository.save(singleAnagramResult);

        assertNotNull(savedAnagramResult);
        assertThat(savedAnagramResult.getFirstSubmittedAnswer(), is(singleAnagramResult.getFirstSubmittedAnswer()));
        assertThat(savedAnagramResult.getSecondSubmittedAnswer(), is(singleAnagramResult.getSecondSubmittedAnswer()));
        assertThat(savedAnagramResult.getId(), is(1L));
    }

    @Test
    public void GIVEN_list_of_results_WHEN_find_all_THEN_returned(){
        anagramResultList.forEach(testEntityManager::persist);
        List<AnagramResult> anagramResults = (List<AnagramResult>) anagramRepository.findAll();
        assertNotNull(anagramResults);
        assertThat(anagramResults.size(), is(5));
    }

}