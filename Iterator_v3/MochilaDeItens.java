import java.util.ArrayList;
import java.util.List;

// classe "Aggregate"
public class MochilaDeItens {
    private List<Item> itens;

    public MochilaDeItens() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        this.itens.add(item);
        System.out.println(item.getTipo() + " adicionado à mochila.");
    }

    //  cria e retorna o objeto Iterator.
    public Iterator<Item> criarIterator() {
        return new MochilaIterator(this.itens);
    }
}