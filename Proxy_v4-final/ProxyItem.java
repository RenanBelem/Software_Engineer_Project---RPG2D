public class ProxyItem extends Item {
    private Item itemReal;

    public ProxyItem(Item itemReal) {
        super(itemReal.getBonusEnum(), itemReal.getX(), itemReal.getY());
        this.itemReal = itemReal;
        
        // Registrar o evento
        Logger.registrarEvento("Herói encontrou um(a) " + itemReal.getTipo());
    }

    // Delegação de métodos
    @Override
    public void aplicaBonus(Heroi heroi) {
        itemReal.aplicaBonus(heroi);
    }

    @Override
    public void retiraBonus(Heroi heroi) {
        itemReal.retiraBonus(heroi);
    }

    @Override
    public String imprimeDescricao() {
        return itemReal.imprimeDescricao();
    }

    @Override
    public String getTipo() {
        return itemReal.getTipo();
    }
}