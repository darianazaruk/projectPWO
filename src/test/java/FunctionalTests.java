import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionalTests {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final InputStream originalSystemIn = System.in;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void testVotingSystem() {
        // Ustawienie danych wejściowych dla testu
        String input = "John Doe\n";
        input += "tak\nnie\n";  // Odpowiedzi na pytania

        // Symulacja wejścia od użytkownika
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Stworzenie atrapy (mock) dla ResultWriter
        ResultWriter mockResultWriter = Mockito.mock(ResultWriter.class);

        // Stworzenie systemu zapisu wyników do pliku
        ResultWriter resultWriter = new TextFileResultWriter("wyniki.txt");

        // Testowanie głównej funkcji systemu głosowania
        VotingSystem.main(new String[]{});

        // Sprawdzenie, czy system wypisuje odpowiednie komunikaty
        assertTrue(outputStreamCaptor.toString().contains("Witaj w systemie głosowania!"));
        assertTrue(outputStreamCaptor.toString().contains("Dziękujemy za udział w głosowaniu!"));
    }

    @Test
    public void testYesNoQuestion() {
        // Stworzenie pytania YesNoQuestion
        YesNoQuestion yesNoQuestion = new YesNoQuestion("Czy jesteś za legalizacją marihuany?");

        // Testowanie funkcji pytania
        assertEquals("Czy jesteś za legalizacją marihuany?", yesNoQuestion.getQuestionText());
        assertTrue(yesNoQuestion.isValidAnswer("tak"));
        assertTrue(yesNoQuestion.isValidAnswer("nie"));
        assertFalse(yesNoQuestion.isValidAnswer("innaOdpowiedz"));

        // Testowanie funkcji zadawania pytania
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        yesNoQuestion.askQuestion();
        assertEquals("Czy jesteś za legalizacją marihuany? (Odpowiedz 'tak' lub 'nie')\n", outputStreamCaptor.toString());
    }

    @AfterEach
    public void restoreSystemInput() {
        System.setIn(originalSystemIn);
    }
}
