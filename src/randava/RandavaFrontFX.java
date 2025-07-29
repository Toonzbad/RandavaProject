// RandavaFrontFX.java
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;

public class RandavaFrontFX extends Application {
    private TextArea logArea = new TextArea();
    private CheckBox enableLogging = new CheckBox("Ativar logging detalhado");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Randava Compiler Interface");

        Button chooseFileBtn = new Button("Selecionar arquivo .randjava");
        chooseFileBtn.setOnAction(e -> handleFileSelection(primaryStage));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(chooseFileBtn, enableLogging, logArea);

        logArea.setEditable(false);
        logArea.setPrefHeight(300);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleFileSelection(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Randava Files", "*.randjava"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            log("Arquivo selecionado: " + file.getName());
            try {
                if (!verifySignature(file)) {
                    log("\n[ERRO] Assinatura inválida ou ausente. Operação abortada.");
                    return;
                }
                compileRandava(file);
            } catch (Exception ex) {
                log("Erro: " + ex.getMessage());
            }
        }
    }

    private boolean verifySignature(File file) throws Exception {
        List<String> lines = Files.readAllLines(file.toPath());
        if (lines.size() < 2 || !lines.get(0).startsWith("// Signature: ")) return false;
        String givenHash = lines.get(0).substring("// Signature: ".length()).trim();
        String content = String.join("\n", lines.subList(1, lines.size()));
        String computedHash = sha256(content);
        log("Assinatura encontrada: " + givenHash);
        log("Assinatura calculada: " + computedHash);
        return givenHash.equals(computedHash);
    }

    private String sha256(String input) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    private void compileRandava(File inputFile) throws IOException, InterruptedException {
        String javaPath = System.getProperty("java.home") + File.separator + "bin" + File.separator;
        String fileName = inputFile.getName().replace(".randjava", ".java");
        String compiler = "java RandavaCompiler \"" + inputFile.getAbsolutePath() + "\"";
        String javac = "javac " + fileName;
        String runner = "java " + fileName.replace(".java", "");

        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", compiler + " && " + javac + " && " + runner);
        builder.directory(new File(System.getProperty("user.dir")));
        builder.redirectErrorStream(true);

        Process process = builder.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                log(line);
            }
        }
        process.waitFor();
    }

    private void log(String message) {
        if (enableLogging.isSelected()) {
            logArea.appendText("[LOG] " + message + "\n");
        }
    }
}
