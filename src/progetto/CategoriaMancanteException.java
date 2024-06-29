package progetto;

@SuppressWarnings("serial")
public class CategoriaMancanteException extends Exception {
    public CategoriaMancanteException(String categoria) {
        super("La categoria non esiste: " + categoria);
    }
}
