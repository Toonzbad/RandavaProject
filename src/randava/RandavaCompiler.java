import java.io.*;
import java.util.*;

public class RandavaCompiler {

    public static void main(String[] args) throws IOException {
        LoggerRandava.log("### Início da compilação RandavaCompiler");

        if (args.length < 1) {
            System.out.println("Uso: java RandavaCompiler <arquivo.randjava>");
            return;
        }

        String inputPath = args[0];
        String baseName = inputPath.replaceFirst("\\.randjava$", "");
        String outputPath = baseName + ".java";
        String className = baseName.substring(baseName.lastIndexOf(File.separator) + 1);

        LoggerRandava.log("Arquivo de entrada: " + inputPath);
        LoggerRandava.log("Arquivo de saída: " + outputPath);
        LoggerRandava.log("Classe gerada: " + className);

        BufferedReader reader = new BufferedReader(new FileReader(inputPath));
        PrintWriter writer = new PrintWriter(new FileWriter(outputPath));

        writer.println("import java.util.*;");
        writer.println();
        writer.println("public class " + className + " {");
        writer.println("    public static void main(String[] args) {");

        String line;
        boolean inRandomBlock = false;
        List<String> randomLines = new ArrayList<>();
        int blockCount = 0;

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (line.equals("random {")) {
                inRandomBlock = true;
                randomLines.clear();
                blockCount++;
                LoggerRandava.log("Bloco 'random' iniciado (bloco #" + blockCount + ")");
            } else if (line.equals("}")) {
                if (inRandomBlock) {
                    LoggerRandava.log("Bloco 'random' fechado. Total de ações: " + randomLines.size());
                    writer.println("        List<Runnable> randActions = Arrays.asList(");
                    for (int i = 0; i < randomLines.size(); i++) {
                        String action = randomLines.get(i);
                        writer.print("            () -> " + action);
                        writer.print(i < randomLines.size() - 1 ? "," : "");
                        writer.println();
                    }
                    writer.println("        );");
                    writer.println("        int idx = QuantumRandum.nextInt(randActions.size());");
                    writer.println("        System.out.println(\"Índice escolhido: \" + idx);");
                    writer.println("        randActions.get(idx).run();");
                    inRandomBlock = false;
                } else {
                    writer.println("    }");
                }
            } else {
                if (inRandomBlock) {
                    randomLines.add(line.replace(";", ""));  // NÃO APAGA evita duplicar
                } else {
                    writer.println("        " + line);
                }
            }
        }

        writer.println("    }");
        writer.println("}");
        reader.close();
        writer.close();

        LoggerRandava.log("Compilação finalizada com sucesso.");
        System.out.println("Compilado como: " + outputPath);
    }
}