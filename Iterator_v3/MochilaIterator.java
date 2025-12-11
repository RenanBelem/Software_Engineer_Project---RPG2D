import java.util.List;

// Iterator concreto para a Mochila
public class MochilaIterator implements Iterator<Item> {
    private List<Item> lista;
    private int posicao = 0;

    public MochilaIterator(List<Item> lista) {
        this.lista = lista;
    }

    @Override
    public boolean hasNext() {
        // verifica se a posição atual está dentro do limite da lista.
        return posicao < lista.size();
    }

    @Override
    public Item next() {
        // retorna o item da posição atual e avança o ponteiro.
        if (hasNext()) {
            Item item = lista.get(posicao);
            posicao++;
            return item;
        }
        return null; // Poderia lançar NoSuchElementException
    }
}