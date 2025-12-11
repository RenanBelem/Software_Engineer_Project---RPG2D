import java.io.*;
import java.util.*;

public class Mapa {
    private char[][] matriz;
    private Heroi heroi;
    private Scanner scanner = new Scanner(System.in);
    private int linhas;
    private int colunas;

    public Mapa(String nomeArquivo, int linhas, int colunas) throws IOException {
        this.linhas = linhas;
        this.colunas = colunas;
        this.matriz = new char[linhas][colunas];
        this.heroi = new Heroi(1, 1, 100, 15, 5, 100);

        try(BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            int c;
            int i = 0, j = 0;

            while ((c = br.read()) != -1 && i < linhas) {
                if (c == '\n' || c == '\r') {
                    continue;
                }
                char caractere = (char) c;
                this.matriz[i][j] = caractere;

                switch (caractere) {
                    case '8':
                        this.heroi.setX(i);
                        this.heroi.setY(j);
                        break;
                }

                j++;
                if (j == colunas) {
                    j = 0;
                    i++;
                }
            }
        }
    }

    public Heroi getHeroi() { return heroi; }

    public void imprimeMapa() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print(matriz[i][j]);
            }
            System.out.println();
        }
    }

    public boolean encontraSaida(int x, int y) {
        if (this.heroi.getVida() <= 0) {
            System.out.println("\nHeroi morreu! Fim de jogo.");
            return false;
        }

        if (this.matriz[x][y] == '=') {
            return true;
        }

        char itemNoMapa = this.matriz[x][y];
        this.matriz[x][y] = '8';

        System.out.print("\033[H\033[2J");
        this.imprimeMapa();
        
        try {
            Thread.sleep(400);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        switch (itemNoMapa) {
            case '?':
                System.out.println("Encontrou um Bicho Papao!");
                batalhar(new BichoPapao(x, y));
                break;
            case '*':
                System.out.println("Encontrou um Curupira!");
                batalhar(new Curupira(x, y));
                break;
            case '^':
                interagirAjudante(new Duende(x, y));
                break;
            case '&':
                interagirAjudante(new Anao(x, y));
                break;
            case 'e':
                interagirItem(new Espada(x, y));
                break;
            case 'd':
                interagirItem(new Escudo(x, y));
                break;
            case 'c':
                interagirPocao(new Cura(x, y));
                break;
        }

        if (y + 1 < colunas && this.matriz[x][y + 1] != '#' && this.matriz[x][y + 1] != '8') {
            if (encontraSaida(x, y + 1)) return true;
        }

        if (x + 1 < linhas && this.matriz[x + 1][y] != '#' && this.matriz[x + 1][y] != '8') {
            if (encontraSaida(x + 1, y)) return true;
        }

        if (y - 1 >= 0 && this.matriz[x][y - 1] != '#' && this.matriz[x][y - 1] != '8') {
            if (encontraSaida(x, y - 1)) return true;
        }

        if (x - 1 >= 0 && this.matriz[x - 1][y] != '#' && this.matriz[x - 1][y] != '8') {
            if (encontraSaida(x - 1, y)) return true;
        }
        
        this.matriz[x][y] = '.';

        return false;
    }

    private void batalhar(Monstro monstro) {
        if (monstro == null) {
            System.out.println("Erro: Monstro nao encontrado para batalha.");
            return;
        }

        monstro.exibirHabilidade();
        
        // Loop para aplicar o efeito de cada ajudante
        if (!heroi.getAjudantes().isEmpty()) {
            for (Ajudante ajudante : heroi.getAjudantes()) {
                System.out.println("Acao do ajudante " + ajudante.getNome() + "!");
                ajudante.aplicarEfeitosBatalha(monstro);
            }
            System.out.println("Seus ajudantes foram embora!!!");
            heroi.getAjudantes().clear(); // Limpa a lista de ajudantes após a batalha
        }

        System.out.println("--- INICIO DA BATALHA ---");
        System.out.println("Heroi: Vida=" + heroi.getVida() + ", Ataque=" + heroi.getAtaque() + ", Defesa=" + heroi.getDefesa());
        System.out.println(monstro.getClass().getSimpleName() + ": Vida=" + monstro.getVida() + ", Ataque=" + monstro.getAtaque() + ", Defesa=" + monstro.getDefesa());
        
        while (heroi.getVida() > 0 && monstro.getVida() > 0) {
            // Heroi ataca
            int danoDoHeroi = heroi.getAtaque() - monstro.getDefesa();
            if (danoDoHeroi < 0) danoDoHeroi = 0;
            monstro.receberDano(danoDoHeroi);
            System.out.println("Voce atacou o " + monstro.getClass().getSimpleName() + " e causou " + danoDoHeroi + " de dano! Vida restante: " + monstro.getVida());
            
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (monstro.getVida() <= 0) break;
            
            // Monstro ataca
            monstro.atacar(heroi);
            System.out.println("Vida do heroi restante: " + heroi.getVida());

            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("--- FIM DA BATALHA ---");
        if (monstro.getVida() <= 0) {
            System.out.println("Voce derrotou o monstro!");
            System.out.println("Voce venceu a batalha, gostaria de continuar? (s/n)");
            String escolha = scanner.nextLine();
            if (escolha.equalsIgnoreCase("n")) {
                System.exit(0);
            }
        } else {
            System.out.println("Voce perdeu a batalha.");
        }
    }
    
    private void interagirAjudante(Ajudante ajudante) {
        if (ajudante == null) {
            System.out.println("Erro: Ajudante nao encontrado.");
            return;
        }
        
        ajudante.interagir(heroi);
        String escolha = scanner.nextLine();
        
        if (escolha.equalsIgnoreCase("s")) {
            heroi.adicionarAjudante(ajudante);
            if (ajudante.getNome().equals("Duende")) {
                 int perdaVida = (int)(heroi.getVida() * 0.1);
                 heroi.receberDano(perdaVida);
                 System.out.println("Voce aceitou a ajuda de um Duende! Voce perdeu 10% de sua vida! Sucesso! Agora voce tem um ajudante!!");
            } else if (ajudante.getNome().equals("Anao")) {
                 int bonusAtaque = (int)(heroi.getAtaque() * 0.85);
                 heroi.setAtaque(heroi.getAtaque() + bonusAtaque);
                 System.out.println("Voce aceitou a ajuda de um Anao! Seu ataque aumentou em 85%! Sucesso! Agora voce tem um ajudante!!");
            }
        } else {
            System.out.println("Voce recusou a ajuda de " + ajudante.getNome() + ".");
        }
    }

    private void interagirItem(Item item) {
        if (item == null) {
            System.out.println("Erro: Item nao encontrado.");
            return;
        }

        System.out.println("Voce encontrou uma " + item.getTipo() + ", capaz de " + item.imprimeDescricao() + ". Deseja apanha-la?");
        System.out.println("Digite 'd' para substituir a mao direita, 'e' para esquerda ou 'n' para ignorar.");
        String escolha = scanner.nextLine();

        if (escolha.equalsIgnoreCase("d")) {
            heroi.equipar(item, 'd');
        } else if (escolha.equalsIgnoreCase("e")) {
            heroi.equipar(item, 'e');
        } else {
            System.out.println("Voce ignorou o item.");
        }
    }

    private void interagirPocao(Pocao pocao) {
        if (pocao == null) {
            System.out.println("Erro: Pocao nao encontrada.");
            return;
        }

        System.out.println("Voce encontrou uma pocao de cura! Deseja usá-la? (s/n)");
        String escolha = scanner.nextLine();

        if (escolha.equalsIgnoreCase("s")) {
            pocao.aplicarEfeito(heroi);
        } else {
            System.out.println("Voce ignorou a pocao.");
        }
    }
}