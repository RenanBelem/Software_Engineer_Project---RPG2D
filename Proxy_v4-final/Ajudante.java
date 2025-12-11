public interface Ajudante {
    String getNome();

    void interagir(Heroi h);

    void aplicarEfeitosBatalha(Monstro monstro);

    default boolean processarResposta(String resposta, Heroi heroi) {
        return false;
    }
}