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
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public List<Ajudante> getAjudantes() { return ajudantes; }
    
    public void adicionarAjudante(Ajudante a) { this.ajudantes.add(a); }
    
    public Item getMaoDireita() { return maoDireita; }
    public Item getMaoEsquerda() { return maoEsquerda; }

    public void receberDano(int dano) {
        vida -= dano;
        if(vida < 0) vida = 0;
    }

    public void setVida(int v) { vida = v; }
    public int getVida() { return vida; }

    public int getAtaque() { return ataque; }
    public void setAtaque(int a) { this.ataque = a; }

    public int getDefesa() { return defesa; }
    public void setDefesa(int d) { this.defesa = d; }

    public void equipar(Item item, char mao) {
        if (mao == 'd') {
            if (this.maoDireita != null) {
                this.maoDireita.retiraBonus(this);
            }
            this.maoDireita = item;
            this.maoDireita.aplicaBonus(this);
            System.out.println("Equipou " + item.getTipo() + " na mao direita.");
        } else if (mao == 'e') {
            if (this.maoEsquerda != null) {
                this.maoEsquerda.retiraBonus(this);
            }
            this.maoEsquerda = item;
            this.maoEsquerda.aplicaBonus(this);
            System.out.println("Equipou " + item.getTipo() + " na mao esquerda.");
        }
    }
}