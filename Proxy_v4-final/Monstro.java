public abstract class Monstro {
    protected int x, y;
    protected int vida;
    protected int ataque;
    protected int defesa;

    public Monstro(int x, int y, int vida, int ataque, int defesa) {
        this.x = x;
        this.y = y;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void receberDano(int dano) {
        vida -= dano;
        if(vida < 0) vida = 0;
    }

    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }

    public abstract void exibirHabilidade();
    public abstract void atacar(Heroi heroi);
}