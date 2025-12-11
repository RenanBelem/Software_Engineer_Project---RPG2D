// Classe Espada
public class Espada extends Item {

    public Espada(int x, int y) {
        super(EnumBonusItem.BONUS_ESPADA, x, y);
    }

    @Override
    public void aplicaBonus(Heroi heroi) {
        heroi.setAtaque(heroi.getAtaque() + this.getBonus());
    }

    @Override
    public void retiraBonus(Heroi heroi) {
        heroi.setAtaque(heroi.getAtaque() - this.getBonus());
    }

    @Override
    public String imprimeDescricao() {
        return "Uma espada que aumenta seu ataque.";
    }

    @Override
    public String getTipo() {
        return "Espada";
    }
}