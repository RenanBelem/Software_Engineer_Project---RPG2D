import java.util.HashMap;
import java.util.Map;

public class ConstrutorDeEntidades {
    private final Map<Character, MonstroFactory> monstros = new HashMap<>();
    private final Map<Character, AjudanteFactory> ajudantes = new HashMap<>();
    private final Map<Character, ItemFactory> itens = new HashMap<>();
    private final Map<Character, PocaoFactory> pocoes = new HashMap<>();

    public ConstrutorDeEntidades() {
        // ProxyMonstroFactory que encapsula a Factory real
        monstros.put('?', new ProxyMonstroFactory(new BichoPapaoFactory()));
        monstros.put('*', new ProxyMonstroFactory(new CurupiraFactory()));

        // ProxyAjudanteFactory que encapsula a Factory real
        ajudantes.put('^', new ProxyAjudanteFactory(new AnaoFactory()));
        ajudantes.put('&', new ProxyAjudanteFactory(new DuendeFactory()));

        // ProxyItemFactory que encapsula a Factory real
        itens.put('e', new ProxyItemFactory(new EspadaFactory()));
        itens.put('d', new ProxyItemFactory(new EscudoFactory()));

        //Não precisam de Proxy pq o log de uso será feito diretamente na interagirPocao
        pocoes.put('c', new CuraFactory()); 
    }

    public Monstro criarMonstro(char c, int x, int y) {
        MonstroFactory f = monstros.get(c);
        return f == null ? null : f.criarMonstro(x, y);
    }

    public Ajudante criarAjudante(char c, int x, int y) {
        AjudanteFactory f = ajudantes.get(c);
        return f == null ? null : f.criarAjudante(x, y);
    }

    public Item criarItem(char c, int x, int y) {
        ItemFactory f = itens.get(c);
        return f == null ? null : f.criarItem(x, y);
    }

    public Pocao criarPocao(char c, int x, int y) {
        PocaoFactory f = pocoes.get(c);
        return f == null ? null : f.criarPocao(x, y);
    }

    // útil quando você só tem o caractere e quer descobrir a categoria
    public boolean ehMonstro(char c)  { return monstros.containsKey(c); }
    public boolean ehAjudante(char c) { return ajudantes.containsKey(c); }
    public boolean ehItem(char c)     { return itens.containsKey(c); }
    public boolean ehPocao(char c)    { return pocoes.containsKey(c); }
}
