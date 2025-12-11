// Proxy Factory para Monstro
public class ProxyMonstroFactory implements MonstroFactory {
    private MonstroFactory fabricaReal;

    public ProxyMonstroFactory(MonstroFactory fabricaReal) {
        this.fabricaReal = fabricaReal;
    }

    @Override
    public Monstro criarMonstro(int x, int y) {
        Monstro monstroReal = fabricaReal.criarMonstro(x, y);
        return new ProxyMonstro(monstroReal);
    }
}