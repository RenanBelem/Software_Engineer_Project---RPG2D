public class CuraFactory implements PocaoFactory {
    @Override
    public Pocao criarPocao(int x, int y) {
        // A classe Cura é uma Poção, mas seu construtor chama o construtor de Pocao
        return new Cura(x, y);
    }
}