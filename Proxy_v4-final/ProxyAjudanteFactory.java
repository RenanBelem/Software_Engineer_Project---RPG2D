// Proxy Factory para Ajudante
public class ProxyAjudanteFactory implements AjudanteFactory {
    private AjudanteFactory fabricaReal;

    public ProxyAjudanteFactory(AjudanteFactory fabricaReal) {
        this.fabricaReal = fabricaReal;
    }

    @Override
    public Ajudante criarAjudante(int x, int y) {
        Ajudante ajudanteReal = fabricaReal.criarAjudante(x, y);
        return new ProxyAjudante(ajudanteReal);
    }
}