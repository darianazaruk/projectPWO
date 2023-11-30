package org.example;

import java.util.Scanner;

// Główna klasa programu
public class VotingSystem {
    public static void main(String[] args) {
        // Pytania do głosowania
        VotingQuestion[] questions = {
                new YesNoQuestion("Czy jesteś za legalizacją marihuany?"),
                new YesNoQuestion("Czy uważasz, że edukacja powinna być bezpłatna?")
                // Dodaj kolejne pytania według potrzeb
        };

        // Tworzenie systemu zapisu wyników do pliku
        ResultWriter resultWriter = new TextFileResultWriter("wyniki.txt");

        // Przykładowe głosowanie
        Scanner scanner = new Scanner(System.in);
        System.out.println("Witaj w systemie głosowania!");
        System.out.println("Podaj swoje imię i nazwisko:");
        String fullName = scanner.nextLine();

        // Tworzenie użytkownika
        Voter voter = new SimpleVoter(fullName);

        // Głosowanie na każde pytanie
        StringBuilder result = new StringBuilder(voter.getFullName()).append(": ");
        for (VotingQuestion question : questions) {
            boolean vote = voter.vote(question);
            result.append(question.getQuestionText()).append("-").append(vote ? "tak" : "nie").append(", ");
        }

        // Zapis wyników do pliku
        resultWriter.writeResult(result.toString());

        System.out.println("Dziękujemy za udział w głosowaniu!");
    }
}
