public class Pocao {
    private int x, y;
    private int bonus;

    public Pocao(int x, int y) {
        this.x = x;
        this.y = y;
        this.bonus = 30; // cura fixa
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public int getBonus() { return bonus; }

    public void aplicarEfeito(Heroi heroi) {
        heroi.setVida(heroi.getVida() + bonus);
        System.out.println("Heroi encontrou uma pocao! Recuperando " + bonus + " de vida.");
    }
}