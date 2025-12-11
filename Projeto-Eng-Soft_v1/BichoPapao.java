public class BichoPapao extends Monstro {
    public BichoPapao(int x, int y) {
        super(x, y, 50, 25, 5); // Vida, Ataque, Defesa
    }

    @Override
    public void exibirHabilidade() {
        System.out.println("Batalha contra BICHO PAPÃO.");
        System.out.println("Habilidade: Ataque normal.");
    }

    @Override
    public void atacar(Heroi h) {
        int dano = this.ataque - h.getDefesa();
        if (dano < 0) dano = 0;
        h.receberDano(dano);
        System.out.println("Bicho Papão atacou e causou " + dano + " de dano!");
    }
}