public class ProxyMonstro extends Monstro {
    private Monstro monstroReal;

    // Construtor chama o construtor da classe base Monstro
    public ProxyMonstro(Monstro monstroReal) {
        super(monstroReal.getX(), monstroReal.getY(), monstroReal.getVida(), monstroReal.getAtaque(), monstroReal.getDefesa());
        this.monstroReal = monstroReal;
        
        // Registrar o evento de encontro do Monstro, que levará à batalha
        Logger.registrarEvento("Herói vai batalhar contra um " + monstroReal.getClass().getSimpleName());
    }

    // Delegação de métodos para a instância real
    @Override
    public void exibirHabilidade() {
        monstroReal.exibirHabilidade();
    }

    // Delegação de métodos para a instância real
    @Override
    public void atacar(Heroi h) {
        monstroReal.atacar(h);
    }
    
    // Sobrescrevendo setters e getters para garantir que a batalha do Mapa manipule 
    // os atributos do objeto Proxy, que então atualizará o objeto Real
    @Override
    public void receberDano(int dano) {
        monstroReal.receberDano(dano);
        this.vida = monstroReal.getVida(); // Manter o atributo vida do Proxy atualizado
    }
    
    @Override
    public int getVida() {
        return monstroReal.getVida();
    }
    
    @Override
    public void setVida(int vida) {
        monstroReal.setVida(vida);
        this.vida = vida;
    }
    
    public String getNomeReal() {
        return monstroReal.getClass().getSimpleName();
    }
}