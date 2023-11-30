package org.example;

import java.util.Scanner;

// Klasa reprezentująca konkretnego użytkownika
public class SimpleVoter implements Voter {
    private String fullName;
    private Scanner scanner;

//    public SimpleVoter(String fullName) {
//        this.fullName = fullName;
//        this.scanner = new Scanner(System.in);
//    }

    public SimpleVoter(String fullName,Scanner scanner) {
        this.fullName = fullName;
        this.scanner = scanner;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean vote(VotingQuestion question) {
        //Scanner scanner = new Scanner(System.in);
        question.askQuestion();

        String answer = scanner.nextLine();
        while (!question.isValidAnswer(answer)) {
            System.out.println("Niepoprawna odpowiedź. Wprowadź 'tak' lub 'nie'.");
            answer = scanner.nextLine();
        }

        return answer.equalsIgnoreCase("tak");

    }
}
