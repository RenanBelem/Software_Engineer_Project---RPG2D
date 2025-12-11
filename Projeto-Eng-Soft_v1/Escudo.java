// Classe Escudo
public class Escudo extends Item {

    public Escudo(int x, int y) {
        super(EnumBonusItem.BONUS_ESCUDO, x, y);
    }

    @Override
    public void aplicaBonus(Heroi heroi) {
        heroi.setDefesa(heroi.getDefesa() + this.getBonus());
    }

    @Override
    public void retiraBonus(Heroi heroi) {
        heroi.setDefesa(heroi.getDefesa() - this.getBonus());
    }

    @Override
    public String imprimeDescricao() {
        return "Um escudo que aumenta sua defesa.";
    }

    @Override
    public String getTipo() {
        return "Escudo";
    }
}