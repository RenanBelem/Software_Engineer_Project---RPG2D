public abstract class Item {

    protected EnumBonusItem bonus;
    protected int x, y;

    public Item(EnumBonusItem bonus, int x, int y) {
        this.bonus = bonus;
        this.x = x;
        this.y = y;
    }

    public EnumBonusItem getBonusEnum() {
        return this.bonus;
    }

    public int getBonus() {
        return this.bonus.getBonus();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void aplicaBonus(Heroi heroi);
    public abstract void retiraBonus(Heroi heroi);
    public abstract String imprimeDescricao();
    public abstract String getTipo();
}