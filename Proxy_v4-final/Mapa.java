import java.io.*;
import java.util.*;

public class Mapa {
    private char[][] matriz;
    private Heroi heroi;
    private Scanner scanner = new Scanner(System.in);
    private int linhas;
    private int colunas;
    private ConstrutorDeEntidades construtor;  // T1

    public Mapa(String nomeArquivo, int linhas, int colunas) throws IOException {
        this.linhas = linhas;
        this.colunas = colunas;
        this.matriz = new char[linhas][colunas];
        this.heroi = new Heroi(1, 1, 100, 15, 5, 100);
        this.construtor = new ConstrutorDeEntidades(); // T1

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
        // 1) condições de parada
        if (this.heroi.getVida() <= 0) {
            System.out.println("\nHerói morreu! Fim de jogo.");
            //log
            Logger.registrarEvento("Herói morreu! Fim de jogo.");
            return false;
        }
        if (this.matriz[x][y] == '=') {
            //log
            Logger.registrarEvento("Herói encontrou a saída do labirinto!");
            return true; // achou a saída
        }

        // 2) lê o que tinha na célula ANTES de marcar o herói
        char tile = this.matriz[x][y];

        // 3) marca posição do herói no mapa
        this.matriz[x][y] = '8';

        // 4) desenha mapa + pequeno delay
        System.out.print("\033[H\033[2J");
        this.imprimeMapa();
        try { Thread.sleep(400); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // IMPRESSAO DE STATUS E ITENS NA MOCHILA
        System.out.println("--- STATUS HEROI ---");
        System.out.println("Vida: " + heroi.getVida() + " | Ataque: " + heroi.getAtaque() + " | Defesa: " + heroi.getDefesa());
        System.out.println("Mochila:");

        // a. Cria o Iterator da Mochila
        Iterator<Item> it = heroi.getMochila().criarIterator();
        
        // b. Itera usando hasNext() e next()
        if (!it.hasNext()) {
            System.out.println("  (Mochila vazia)");
        }
        while (it.hasNext()) {
            Item itemMochila = it.next();
            System.out.println("  - " + itemMochila.getTipo() + " (" + itemMochila.imprimeDescricao() + ")");
        }
        System.out.println("--------------------");
        // FIM DA IMPRESSÃO

        // 5) resolve o encontro via registry
        if (construtor.ehMonstro(tile)) {
            Monstro monstro = construtor.criarMonstro(tile, x, y);
            if (monstro != null) {
                String nomeExibicao;
                if (monstro instanceof ProxyMonstro) {
                    nomeExibicao = ((ProxyMonstro) monstro).getNomeReal();
                } else {
                    nomeExibicao = monstro.getClass().getSimpleName();
                }

                System.out.println("Encontrou um " + nomeExibicao + "!"); // AQUI
                batalhar(monstro);
            }
        } else if (construtor.ehAjudante(tile)) {
            Ajudante ajudante = construtor.criarAjudante(tile, x, y);
            if (ajudante != null) {
                System.out.println("Encontrou um " + ajudante.getNome() + "!");
                interagirAjudante(ajudante);
            }
        } else if (construtor.ehItem(tile)) {
            Item item = construtor.criarItem(tile, x, y);
            if (item != null) {
                System.out.println("Encontrou uma " + item.getTipo() + "!");
                interagirItem(item);
            }
        } else if (construtor.ehPocao(tile)) {
            Pocao pocao = construtor.criarPocao(tile, x, y);
            if (pocao != null) {
                System.out.println("Encontrou uma poção de cura!");
                interagirPocao(pocao);
            }
        }

        // 6) busca recursiva (DFS) — só anda em células válidas, não parede (#) nem já visitadas (8)
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

        // 7) marca como visitado
        this.matriz[x][y] = '.';
        return false;
    }

    private void batalhar(Monstro monstro) {
        if (monstro == null) {
            System.out.println("Erro: Monstro nao encontrado para batalha.");
            return;
        }

        // --- Nome da Entidade para exibição ---
        String nomeExibicao;
        if (monstro instanceof ProxyMonstro) {
            nomeExibicao = ((ProxyMonstro) monstro).getNomeReal();
        } else {
            nomeExibicao = monstro.getClass().getSimpleName();
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
        
        // USAR nomeExibicao AQUI
        System.out.println(nomeExibicao + ": Vida=" + monstro.getVida() + ", Ataque=" + monstro.getAtaque() + ", Defesa=" + monstro.getDefesa());

        while (heroi.getVida() > 0 && monstro.getVida() > 0) {
            // Heroi ataca
            int danoDoHeroi = heroi.getAtaque() - monstro.getDefesa();
            if (danoDoHeroi < 0) danoDoHeroi = 0;
            monstro.receberDano(danoDoHeroi);
            
            // nomeExibicao
            System.out.println("Voce atacou o " + nomeExibicao + " e causou " + danoDoHeroi + " de dano! Vida restante: " + monstro.getVida());
                        
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
            System.out.println("Erro: Ajudante não encontrado.");
            return;
        }

        // Se ja esta seguindo este mesmo ajudante
        Ajudante atual = heroi.getAjudanteAtual();
        if (atual != null && atual.equals(ajudante)) {
            System.out.println(ajudante.getNome() + " já está te seguindo.");
            return;
        }

        ajudante.interagir(heroi);
        String escolha = scanner.nextLine();

        if (ajudante.processarResposta(escolha, heroi)) {
            if (heroi.hasAjudante()) {
                System.out.println("Aviso: " + heroi.getAjudanteAtual().getNome() +
                        " será dispensado para " + ajudante.getNome() + " te seguir.");
                System.out.print("Deseja continuar? (s/n): ");
                String confirm = scanner.nextLine().trim().toLowerCase();
                if (!confirm.equals("s") && !confirm.equals("sim")) {
                    System.out.println("A troca de ajudante foi cancelada.");
                    return;
                }
            }

            Ajudante antigo = heroi.substituirAjudante(ajudante);
            if (antigo != null) {
                System.out.println(antigo.getNome() + " foi dispensado.");
            }
            System.out.println(ajudante.getNome() + " agora está te seguindo.");
        } else {
            System.out.println("Você recusou a ajuda.");
        }
    }


    private void interagirItem(Item item) {
        if (item == null) {
            System.out.println("Erro: Item nao encontrado.");
            return;
        }

        System.out.println("Voce encontrou um(a) " + item.getTipo() + "," + item.imprimeDescricao() + ". O que deseja fazer?");
        // Opcao "g" pra guardar na mochila
        System.out.println("Digite 'g' para guardar na mochila, 'd' para equipar na mao direita, 'e' para esquerda ou 'n' para ignorar.");
        String escolha = scanner.nextLine();

        if (escolha.equalsIgnoreCase("d")) {
            heroi.equipar(item, 'd');
        } else if (escolha.equalsIgnoreCase("e")) {
            heroi.equipar(item, 'e');  
        } else if (escolha.equalsIgnoreCase("g")) { // guardar na mochila
            heroi.adicionarItemNaMochila(item);
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
            //log
            Logger.registrarEvento("Herói usou uma Poção de Cura.");
        } else {
            System.out.println("Voce ignorou a pocao.");
        }
    }
}