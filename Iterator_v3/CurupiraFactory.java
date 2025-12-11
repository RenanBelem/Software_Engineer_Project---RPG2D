public class CurupiraFactory implements MonstroFactory {
    @Override
    public Monstro criarMonstro(int x, int y) {
        return new Curupira(x, y);
    }
}