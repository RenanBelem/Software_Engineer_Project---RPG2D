public class Curupira extends Monstro {
    public Curupira(int x, int y) {
        super(x, y, 70, 20, 10); // Vida, Ataque, Defesa
    }

    @Override
    public void exibirHabilidade() {
        System.out.println("Batalha contra CURUPIRA.");
        System.out.println("Habilidade: Defesa alta.");
    }

    @Override
    public void atacar(Heroi h) {
        int dano = this.ataque - h.getDefesa();
        if (dano < 0) dano = 0;
        h.receberDano(dano);
        System.out.println("Curupira atacou e causou " + dano + " de dano!");
    }
}