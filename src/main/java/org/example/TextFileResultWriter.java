package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Klasa reprezentująca konkretny system zapisu wyników do pliku tekstowego
class TextFileResultWriter implements ResultWriter {
    private String filePath;

    public TextFileResultWriter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void writeResult(String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(result);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisu wyników do pliku.");
            e.printStackTrace();
        }
    }
}