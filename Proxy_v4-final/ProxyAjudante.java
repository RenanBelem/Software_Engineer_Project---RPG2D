public class ProxyAjudante implements Ajudante {
    private Ajudante ajudanteReal;

    public ProxyAjudante(Ajudante ajudanteReal) {
        this.ajudanteReal = ajudanteReal;
        
        // Registrar o evento
        Logger.registrarEvento("Herói se aproximou de um " + ajudanteReal.getNome());
    }

    // Delegação de métodos
    @Override
    public String getNome() {
        return ajudanteReal.getNome();
    }

    @Override
    public void interagir(Heroi h) {
        ajudanteReal.interagir(h);
    }

    @Override
    public void aplicarEfeitosBatalha(Monstro monstro) {
        ajudanteReal.aplicarEfeitosBatalha(monstro);
        // Opcional: Registrar o efeito aplicado
        Logger.registrarEvento(ajudanteReal.getNome() + " aplicou um efeito de batalha.");
    }
    
    @Override
    public boolean processarResposta(String resposta, Heroi heroi) {
        boolean resultado = ajudanteReal.processarResposta(resposta, heroi);
        if (resultado) {
            Logger.registrarEvento("Herói aceitou a ajuda do " + ajudanteReal.getNome() + ".");
        } else {
            Logger.registrarEvento("Herói recusou a ajuda do " + ajudanteReal.getNome() + ".");
        }
        return resultado;
    }
}