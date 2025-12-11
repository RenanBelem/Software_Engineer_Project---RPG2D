import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Logger {
    private static final String NOME_ARQUIVO = "log_jogo.txt";
    private static final DateTimeFormatter FORMATADOR = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.forLanguageTag("pt-BR"));

    /**
     * Registra uma mensagem no arquivo de log com o timestamp atual.
     * @param mensagem O evento ocorrido.
     */
    public static void registrarEvento(String mensagem) {
        String timestamp = LocalDateTime.now().format(FORMATADOR);
        String logEntry = timestamp + " " + mensagem;

        try (FileWriter fw = new FileWriter(NOME_ARQUIVO, true); // 'true' para append
             PrintWriter pw = new PrintWriter(fw)) {
            
            pw.println(logEntry);
            // Opcional: imprimir no console para debug
            // System.out.println("[LOG]: " + logEntry); 
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }
    }
}