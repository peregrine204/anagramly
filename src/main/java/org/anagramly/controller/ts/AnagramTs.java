package org.anagramly.controller.ts;

/**
 * Represents an Anagram model object
 */
public class AnagramTs {

    private String firstAnagramCandidate, secondAnagramCandidate;

    private boolean matched;

    public String getFirstAnagramCandidate() {
        return firstAnagramCandidate;
    }

    public void setFirstAnagramCandidate(String firstAnagramCandidate) {
        this.firstAnagramCandidate = firstAnagramCandidate;
    }

    public String getSecondAnagramCandidate() {
        return secondAnagramCandidate;
    }

    public void setSecondAnagramCandidate(String secondAnagramCandidate) {
        this.secondAnagramCandidate = secondAnagramCandidate;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}
