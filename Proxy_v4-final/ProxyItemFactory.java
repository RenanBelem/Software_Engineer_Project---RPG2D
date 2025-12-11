// Proxy Factory para Item
public class ProxyItemFactory implements ItemFactory {
    private ItemFactory fabricaReal;

    public ProxyItemFactory(ItemFactory fabricaReal) {
        this.fabricaReal = fabricaReal;
    }

    @Override
    public Item criarItem(int x, int y) {
        Item itemReal = fabricaReal.criarItem(x, y);
        return new ProxyItem(itemReal);
    }
}