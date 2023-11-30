import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class VotingSystemTest {

    private final InputStream originalSystemIn = System.in;

    @BeforeEach
    void setUp() {
        // Przed każdym testem zapamiętaj oryginalne System.in
        System.setIn(originalSystemIn);
    }

    @AfterEach
    void tearDown() {
        // Po każdym teście przywróć oryginalne System.in
        System.setIn(originalSystemIn);
    }
    @Test
    void testYesNoQuestionIsValidAnswer() {
        YesNoQuestion question = new YesNoQuestion("Test pytania?");
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
        Voter voter = new SimpleVoter("Jan Kowalski",scanner);

        assertTrue(voter.vote(question));

    }

    @Test
    void testTextFileResultWriter() {
        ResultWriter resultWriter = new TextFileResultWriter("test_wyniki.txt");
        resultWriter.writeResult("Testowa linia wyniku");

        assertAll(() -> {
            java.nio.file.Path path = java.nio.file.Paths.get("test_wyniki.txt");
            assertTrue(java.nio.file.Files.readAllLines(path).contains("Testowa linia wyniku"));
        });
    }

}

