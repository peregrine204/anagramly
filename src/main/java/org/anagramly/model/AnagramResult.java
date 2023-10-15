package org.anagramly.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AnagramResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstSubmittedAnswer;

    private String secondSubmittedAnswer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstSubmittedAnswer() {
        return firstSubmittedAnswer;
    }

    public void setFirstSubmittedAnswer(String firstSubmittedAnswer) {
        this.firstSubmittedAnswer = firstSubmittedAnswer;
    }

    public String getSecondSubmittedAnswer() {
        return secondSubmittedAnswer;
    }

    public void setSecondSubmittedAnswer(String secondSubmittedAnswer) {
        this.secondSubmittedAnswer = secondSubmittedAnswer;
    }

    public static class AnagramResultBuilder {
        private String firstSubmittedAnswer;
        private String secondSubmittedAnswer;

        public AnagramResultBuilder(){
        }

        public AnagramResultBuilder withFirstSubmittedAnswer(String firstSubmittedAnswer){
            this.firstSubmittedAnswer = firstSubmittedAnswer;
            return this;
        }

        public AnagramResultBuilder withSecondSubmittedAnswer(String secondSubmittedAnswer){
            this.secondSubmittedAnswer = secondSubmittedAnswer;
            return this;
        }

        public AnagramResult build(){
            AnagramResult anagramResult = new AnagramResult();
            anagramResult.setFirstSubmittedAnswer(this.firstSubmittedAnswer);
            anagramResult.setSecondSubmittedAnswer(this.secondSubmittedAnswer);
            return anagramResult;
        }
    }
}
