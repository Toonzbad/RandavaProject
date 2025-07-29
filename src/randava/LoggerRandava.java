import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerRandava {
    public static boolean LOG_ENABLED = true;
    private static final String LOG_FILE = "randava.log";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public static void log(String message) {
        if (!LOG_ENABLED) return;
        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            out.println("[" + FORMATTER.format(LocalDateTime.now()) + "] " + message);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no log: " + e.getMessage());
        }
    }

    public static void logQuantumFetch(int count, long millis) {
        log("Quantum API chamada. Tempo de resposta: " + millis + "ms. Dados obtidos: " + count + " valores (uint8)");
    }

    public static void logQuantumSelection(int raw, int bound, int result) {
        log("Número escolhido: " + raw + " (Quantum) -> Aplicado: " + raw + " % " + bound + " = " + result);
    }

    public static void logFallbackSelection(int result) {
        log("Cache vazio. Fallback ativado. Número pseudo-aleatório gerado: " + result);
    }

    public static void logError(String context, Exception e) {
        log("Erro em " + context + ": " + e.getClass().getSimpleName() + " - " + e.getMessage());
    }
}
