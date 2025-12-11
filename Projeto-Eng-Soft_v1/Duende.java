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
        System.out.println("Ola, eu sou um Duende! Sou capaz de reduzir pela metade a vida do proximo monstro que voce encontrar, a troca de uma reducao de 10% da sua vida atual. Aceita? s ou n?");
    }

    @Override
    public void aplicarEfeitosBatalha(Monstro monstro) {
        monstro.setVida(monstro.getVida() / 2);
        System.out.println("Voce tem um DUENDE como ajudante! Por isso, a VIDA deste monstro foi reduzida pela metade!!");
    }

    public int getX() { return x; }
    public int getY() { return y; }
}