public class EspadaFactory implements ItemFactory {
    @Override
    public Item criarItem(int x, int y) {
        return new Espada(x, y);
    }
}