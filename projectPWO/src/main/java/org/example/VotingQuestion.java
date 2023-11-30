package org.example;

// Interfejs reprezentujący pytanie do głosowania
public interface VotingQuestion {
    void askQuestion();
    boolean isValidAnswer(String answer);
    String getQuestionText();
}
