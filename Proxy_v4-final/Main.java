import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            String caminhoMapa = Paths.get("Mapa.txt").toString();

            Mapa map = new Mapa(caminhoMapa, 17, 21);

            if (map.encontraSaida(map.getHeroi().getX(), map.getHeroi().getY())) {
                System.out.println("\nGANHOU! O Herói encontrou a saída!");
            } else {
                System.out.println("\nPERDEU! O Herói não conseguiu encontrar a saída ou morreu.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o mapa: " + e.getMessage());
            e.printStackTrace();
        }
    }
}