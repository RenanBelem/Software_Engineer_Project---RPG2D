public class BichoPapaoFactory implements MonstroFactory {
    @Override
    public Monstro criarMonstro(int x, int y) {
        return new BichoPapao(x, y);
    }
}