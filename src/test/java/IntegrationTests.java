import org.example.VotingSystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTests {

    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;

    @BeforeEach
    void setUp() {
        // Replace System.in with a new ByteArrayInputStream
        String input = "Jan Kowalski\nTak\nNie\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Replace System.out with a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        // Restore System.in and System.out to their original state
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    void testVotingSystemIntegration() throws IOException {
        // Symulacja danych wejściowych
        String input = "Jan Kowalski\nTak\nNie\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        // Przechwytywanie danych wyjściowych
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Uruchomienie systemu
        VotingSystem.main(new String[]{});

        // Sprawdzenie, czy wyniki są zapisane poprawnie
        Path resultFilePath = Paths.get("wyniki.txt");
        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        assertTrue(Files.exists(resultFilePath));

        // Odczyt z pliku i sprawdzenie poprawności zapisu
        String resultContent = Files.readString(resultFilePath);
        //assertTrue(resultContent.contains("Jan Kowalski: YesNoQuestion-true, YesNoQuestion-false"));
        assertTrue(resultContent.contains("Jan Kowalski: Czy jesteś za legalizacją marihuany?-tak, Czy uważasz, że edukacja powinna być bezpłatna?-nie"));


        // Przywrócenie standardowego System.in i System.out
        System.setIn(System.in);
        System.setOut(System.out);
    }
}


