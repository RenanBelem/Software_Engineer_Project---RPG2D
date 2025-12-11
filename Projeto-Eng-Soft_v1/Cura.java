public class Cura extends Pocao {
    private int bonus;

    public Cura(int x, int y) {
        super(x, y);
        this.bonus = 30; // valor de cura
    }

    @Override
    public void aplicarEfeito(Heroi heroi) {
        heroi.setVida(heroi.getVida() + bonus);
        System.out.println("Heroi encontrou uma pocao! Recuperando " + bonus + " de vida.");
    }
}