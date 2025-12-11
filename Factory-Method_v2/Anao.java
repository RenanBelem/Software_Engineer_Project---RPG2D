public class Anao implements Ajudante {
    private String nome;
    private int x, y;

    public Anao(int x, int y) {
        this.nome = "Anao";
        this.x = x;
        this.y = y;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void interagir(Heroi h) {
        System.out.println("Sou um Anão. Posso aumentar seu ataque em 85% até a próxima batalha.");
        System.out.println("Aceita? s ou n?");
    }


    @Override
    public boolean processarResposta(String resposta, Heroi heroi) {
        if (resposta.equalsIgnoreCase("s")) {
            int bonusAtaque = (int) (heroi.getAtaque() * 0.85);
            heroi.setAtaque(heroi.getAtaque() + bonusAtaque);
            System.out.println("Você aceitou a ajuda do Anão. Seu ataque aumentou em 85%.");
            return true;
        }
        System.out.println("Você recusou a ajuda do Anão.");
        return false;
    }

    @Override
    public void aplicarEfeitosBatalha(Monstro monstro) {
        monstro.setVida((int) (monstro.getVida() * 1.35));
        System.out.println("Voce tem um ANAO como ajudante! Por isso, a VIDA deste monstro foi aumentada em 35%!");
    }

    public int getX() { return x; }
    public int getY() { return y; }
}