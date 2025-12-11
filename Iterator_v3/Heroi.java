import java.util.ArrayList;
import java.util.List;

public class Heroi {
    private int x, y;
    private int vida;
    private int ataque;
    private int defesa;
    private int energia;
    private List<Ajudante> ajudantes;
    private Item maoDireita;
    private Item maoEsquerda;
    private MochilaDeItens mochila;

    public Heroi(int x, int y, int vida, int ataque, int defesa, int energia) {
        this.x = x;
        this.y = y;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.energia = energia;
        this.ajudantes = new ArrayList<>();
        this.maoDireita = null;
        this.maoEsquerda = null;
        this.mochila = new MochilaDeItens();
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public int getVida() { return vida; }
    public void setVida(int v) { vida = v; }
    public int getAtaque() { return ataque; }
    public void setAtaque(int a) { this.ataque = a; }
    public int getDefesa() { return defesa; }
    public void setDefesa(int d) { this.defesa = d; }

    //regra de ajudantes (no máximo 1)
    public boolean hasAjudante() {
        return ajudantes != null && !ajudantes.isEmpty();
    }

    public Ajudante getAjudanteAtual() {
        return hasAjudante() ? ajudantes.get(0) : null;
    }

    public Ajudante substituirAjudante(Ajudante novo) {
        if (ajudantes == null) ajudantes = new ArrayList<>();
        Ajudante antigo = getAjudanteAtual();
        ajudantes.clear();
        if (novo != null) ajudantes.add(novo);
        return antigo;
    }

    public void dispensarAjudanteAtual() {
        if (ajudantes != null) ajudantes.clear();
    }

    public void adicionarAjudante(Ajudante novo) {
        substituirAjudante(novo);
    }

    public List<Ajudante> getAjudantes() {
        return ajudantes;
    }

    public void receberDano(int dano) {
        vida -= dano;
        if (vida < 0) vida = 0;
    }

    public Item getMaoDireita() { return maoDireita; }
    public Item getMaoEsquerda() { return maoEsquerda; }

    public void equipar(Item item, char mao) {
        if (mao == 'd') {
            if (this.maoDireita != null) this.maoDireita.retiraBonus(this);
            this.maoDireita = item;
            this.maoDireita.aplicaBonus(this);
            System.out.println("Equipou " + item.getTipo() + " na mão direita.");
        } else if (mao == 'e') {
            if (this.maoEsquerda != null) this.maoEsquerda.retiraBonus(this);
            this.maoEsquerda = item;
            this.maoEsquerda.aplicaBonus(this);
            System.out.println("Equipou " + item.getTipo() + " na mão esquerda.");
        }
    }

    public MochilaDeItens getMochila() { return mochila; }
    public void adicionarItemNaMochila(Item item) { mochila.adicionarItem(item); }
}
