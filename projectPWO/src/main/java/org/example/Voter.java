package org.example;

// Interfejs reprezentujący użytkownika
public interface Voter {
    String getFullName();
    boolean vote(VotingQuestion question);
}
