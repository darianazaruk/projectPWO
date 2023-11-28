package org.example;

// Interfejs reprezentujący użytkownika
interface Voter {
    String getFullName();
    boolean vote(VotingQuestion question);
}
