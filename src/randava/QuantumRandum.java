// QuantumRandum.java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.*;
import java.util.*;
import java.io.*;

public class QuantumRandum {
    private static final String CACHE_FILE = "entropy_cache.txt";
    private static final String API_URL = "https://qrng.anu.edu.au/API/jsonI.php?length=1024&type=uint8";
    private static final Queue<Integer> cache = new LinkedList<>();
    private static final Random fallbackRandom = new Random();

    static {
        loadCache();
        if (cache.isEmpty()) fetchQuantumData();
    }

    public static int nextInt(int bound) {
        if (cache.isEmpty()) fetchQuantumData();
        if (cache.isEmpty()) return fallbackRandom.nextInt(bound); // fallback pra quem querer saber 
        return cache.poll() % bound;
    }

    private static void fetchQuantumData() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();

            if (body.contains("data")) {
                int start = body.indexOf("[") + 1;
                int end = body.indexOf("]");
                String[] numbers = body.substring(start, end).split(",");
                for (String num : numbers) {
                    int val = Integer.parseInt(num.trim());
                    cache.add(val);
                }
                saveCache();
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar dados quânticos. Usando fallback.");
        }
    }

    private static void saveCache() {
        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(Paths.get(CACHE_FILE)))) {
            for (int val : cache) out.println(val);
        } catch (IOException ignored) {}
    }

    private static void loadCache() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(CACHE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cache.add(Integer.parseInt(line));
            }
        } catch (IOException ignored) {}
    }
}
