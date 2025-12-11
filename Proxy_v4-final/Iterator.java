public interface Iterator<T> {
    /**
     * Verifica se há um próximo elemento na coleção.
     * @return true se houver mais elementos, false caso contrário.
     */
    boolean hasNext();

    /**
     * Retorna o próximo elemento da coleção e avança o iterador.
     * @return O próximo elemento.
     */
    T next();
}