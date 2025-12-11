public class AnaoFactory implements AjudanteFactory {
    @Override
    public Ajudante criarAjudante(int x, int y) {
        return new Anao(x, y);
    }
}