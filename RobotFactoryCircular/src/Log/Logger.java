package Log;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FILE = "logs.txt"; // Nome do arquivo de log

    // Método sincronizado para gravar mensagens no arquivo de log
    public static synchronized void log(String message) {
        try (FileWriter fileWriter = new FileWriter(LOG_FILE, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            // Adicionar timestamp à mensagem
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            // Escrever a mensagem no arquivo de log, sem acentuação
            printWriter.println(timestamp + " - " + removeAcentuacao(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para remover acentuação de uma string
    private static String removeAcentuacao(String texto) {
        return texto
                .replaceAll("[ÁÀÂÃÄ]", "A")
                .replaceAll("[ÉÈÊË]", "E")
                .replaceAll("[ÍÌÎÏ]", "I")
                .replaceAll("[ÓÒÔÕÖ]", "O")
                .replaceAll("[ÚÙÛÜ]", "U")
                .replaceAll("[Ç]", "C")
                .replaceAll("[áàâãä]", "a")
                .replaceAll("[éèêë]", "e")
                .replaceAll("[íìîï]", "i")
                .replaceAll("[óòôõö]", "o")
                .replaceAll("[úùûü]", "u")
                .replaceAll("[ç]", "c");
    }
}
