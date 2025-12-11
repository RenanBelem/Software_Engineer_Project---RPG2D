public class DuendeFactory implements AjudanteFactory {
    @Override
    public Ajudante criarAjudante(int x, int y) {
        return new Duende(x, y);
    }
}