package org.example;

import java.util.Scanner;

// Klasa reprezentująca konkretnego użytkownika
class SimpleVoter implements Voter {
    private String fullName;

    public SimpleVoter(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean vote(VotingQuestion question) {
        Scanner scanner = new Scanner(System.in);
        question.askQuestion();

        String answer = scanner.nextLine();
        while (!question.isValidAnswer(answer)) {
            System.out.println("Niepoprawna odpowiedź. Wprowadź 'tak' lub 'nie'.");
            answer = scanner.nextLine();
        }

        return answer.equalsIgnoreCase("tak");
    }
}
