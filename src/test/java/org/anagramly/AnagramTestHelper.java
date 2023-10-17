package org.anagramly;

import org.anagramly.model.AnagramResult;

import java.util.List;

public class AnagramTestHelper {

    private AnagramTestHelper() {
        //helper class
    }

    public static AnagramResult singleAnagramResult() {
        AnagramResult anagramResult = new AnagramResult();
        anagramResult.setFirstSubmittedAnswer("Mite");
        anagramResult.setSecondSubmittedAnswer("Time");
        return anagramResult;
    }

    public static List<AnagramResult> createAnagramResults() {
        AnagramResult anagramResult = new AnagramResult();
        anagramResult.setFirstSubmittedAnswer("Item");
        anagramResult.setSecondSubmittedAnswer("Mite");

        AnagramResult secondAnagramResult = new AnagramResult();
        secondAnagramResult.setFirstSubmittedAnswer("Emit");
        secondAnagramResult.setSecondSubmittedAnswer("Time");

        AnagramResult thirdAnagramResult = new AnagramResult();
        thirdAnagramResult.setFirstSubmittedAnswer("Etmi");
        thirdAnagramResult.setSecondSubmittedAnswer("Time");

        AnagramResult fourthAnagramResult = new AnagramResult();
        fourthAnagramResult.setFirstSubmittedAnswer("New York Times");
        fourthAnagramResult.setSecondSubmittedAnswer("Monkeys write");

        AnagramResult fifthAnagramResult = new AnagramResult();
        fifthAnagramResult.setFirstSubmittedAnswer("Tom Marvolo Riddle");
        fifthAnagramResult.setSecondSubmittedAnswer("I am Lord Voldemort");

        return List.of(anagramResult, secondAnagramResult, thirdAnagramResult, fourthAnagramResult, fifthAnagramResult);
    }
}
