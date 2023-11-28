package org.example;

// Klasa reprezentująca konkretne pytanie do głosowania
class YesNoQuestion implements VotingQuestion {
    private String question;

    public YesNoQuestion(String question) {
        this.question = question;
    }

    @Override
    public void askQuestion() {
        System.out.println(question + " (Odpowiedz 'tak' lub 'nie')");
    }

    @Override
    public boolean isValidAnswer(String answer) {
        return answer.equalsIgnoreCase("tak") || answer.equalsIgnoreCase("nie");
    }

    @Override
    public String getQuestionText() {
        return question;
    }
}

