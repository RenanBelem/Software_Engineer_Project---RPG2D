public enum EnumBonusItem {
    BONUS_ESPADA(10),
    BONUS_ESCUDO(8),
    BONUS_CURA(15);

    private final int bonus;

    EnumBonusItem(int bonus) {
        this.bonus = bonus;
    }

    public int getBonus() {
        return this.bonus;
    }
}