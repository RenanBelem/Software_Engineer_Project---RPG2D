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
        System.out.println("Ola, eu sou um Anao! Sou capaz de aumentar o ataque do heroi em 85%, mas em compensacao, aumentarei a vida do proximo monstro que voce encontrar em 35%. Aceita? s ou n?");
    }

    @Override
    public void aplicarEfeitosBatalha(Monstro monstro) {
        monstro.setVida((int) (monstro.getVida() * 1.35));
        System.out.println("Voce tem um ANAO como ajudante! Por isso, a VIDA deste monstro foi aumentada em 35%!");
    }

    public int getX() { return x; }
    public int getY() { return y; }
}