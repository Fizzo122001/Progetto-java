package progetto;


public class SceltaException extends Exception {
	private static final long serialVersionUID = 1L;
	public SceltaException(String message) {
	 super("La scelta non è valida");
	 }
}
