public class Duende implements Ajudante {
    private String nome;
    private int x, y;

    public Duende(int x, int y) {
        this.nome = "Duende";
        this.x = x;
        this.y = y;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void interagir(Heroi h) {
        System.out.println("Olá, sou um Duende. Posso reduzir pela metade a vida do próximo monstro.");
        System.out.println("Custo: você perde 10% da sua vida atual. Aceita? s ou n?");
    }

    @Override
    public boolean processarResposta(String resposta, Heroi heroi) {
        if (resposta.equalsIgnoreCase("s")) {
            int perdaVida = (int) (heroi.getVida() * 0.10);
            heroi.receberDano(perdaVida);
            System.out.println("Você aceitou a ajuda do Duende. Perdeu 10% de vida e ganhou o efeito para a próxima batalha.");
            return true;
        }
        System.out.println("Você recusou a ajuda do Duende.");
        return false;
    }

    @Override
    public void aplicarEfeitosBatalha(Monstro monstro) {
        monstro.setVida(monstro.getVida() / 2);
        System.out.println("Voce tem um DUENDE como ajudante! Por isso, a VIDA deste monstro foi reduzida pela metade!!");
    }

    public int getX() { return x; }
    public int getY() { return y; }
}