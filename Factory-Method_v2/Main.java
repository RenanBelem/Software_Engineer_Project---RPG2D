import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Mapa map = new Mapa("C:\\Users\\Usuario\\IdeaProjects\\engSoftware\\factory_method\\src\\mapa.txt", 17, 21);

            if (map.encontraSaida(map.getHeroi().getX(), map.getHeroi().getY())) {
                System.out.println("\nGANHOU! O Herói encontrou a saída!");
            } else {
                System.out.println("\nPERDEU! O Herói não conseguiu encontrar a saída ou morreu.");
            }
        } catch (IOException e){
            System.out.println("Erro ao carregar o mapa: " + e.getMessage());
            e.printStackTrace();
        }
    }
}