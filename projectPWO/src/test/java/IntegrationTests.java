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
        // Capture the original System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the VotingSystem
        VotingSystem.main(new String[]{});

        // Check if the results are saved correctly
        Path resultFilePath = Paths.get("wyniki.txt");
        assertTrue(Files.exists(resultFilePath));

        // Read from the file and check the correctness of the saved data
        String resultContent = Files.readString(resultFilePath);
        assertTrue(resultContent.contains("Jan Kowalski: YesNoQuestion-true, YesNoQuestion-false"));
    }
}


