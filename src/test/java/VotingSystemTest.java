import org.example.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class VotingSystemTest {

    @Test
    void testYesNoQuestionIsValidAnswer() {
        YesNoQuestion question = new YesNoQuestion("Czy test działa?");
        assertTrue(question.isValidAnswer("tak"));
        assertTrue(question.isValidAnswer("TAK"));
        assertTrue(question.isValidAnswer("nie"));
        assertTrue(question.isValidAnswer("NIE"));
        assertFalse(question.isValidAnswer("inna odpowiedz"));
    }

    @Test
    void testSimpleVoterVote() {
        String input = "Tak\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        VotingQuestion question = new YesNoQuestion("Test pytania?");
        Scanner scanner = new Scanner(System.in);
        Voter voter = new SimpleVoter("Jan Kowalski", scanner);

        assertTrue(voter.vote(question));
    }

    @Test
    void testTextFileResultWriter() {
        ResultWriter resultWriter = new TextFileResultWriter("test_wyniki.txt");
        resultWriter.writeResult("Testowa linia wyniku");

        // Odczyt z pliku i sprawdzenie poprawności zapisu
        assertAll(() -> {
            Path path = Paths.get("test_wyniki.txt");
            assertTrue(Files.readAllLines(path).contains("Testowa linia wyniku"));
        });
    }

    @Test
    void testVotingSystemMain() {
        // Symulacja danych wejściowych
        String input = "Jan Kowalski\nTak\nNie\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Przechwytywanie danych wyjściowych
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Uruchomienie systemu
        VotingSystem.main(new String[]{});

        // Sprawdzenie, czy wyniki są zapisane poprawnie
        Path resultFilePath = Paths.get("wyniki.txt");
        assertTrue(Files.exists(resultFilePath));

        // Odczyt z pliku i sprawdzenie poprawności zapisu
        assertAll(() -> {
            String resultContent = Files.readString(resultFilePath);
            assertTrue(resultContent.contains("Jan Kowalski: Czy jesteś za legalizacją marihuany?-tak, Czy uważasz, że edukacja powinna być bezpłatna?-nie"));
        });

        // Przywrócenie standardowego System.in i System.out
        System.setIn(System.in);
        System.setOut(System.out);
    }
}
