public class EscudoFactory implements ItemFactory {
    @Override
    public Item criarItem(int x, int y) {
        return new Escudo(x, y);
    }
}