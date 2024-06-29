/**
* …………
*
* @author Marco Chiappa 20047162
* @author Beatrice Capella 20034984
* classe Articolo per definire l'oggetto Articolo
*/

package progetto;

public class Articolo {
    
	public String nome;
    public float costo;
    public String categoria;
    public int quantita;
    
    
    /**
     * costruttore per oggetti Articolo
     * @param nome
     * @param costo
     * @param categoria
     * @param quantita
     * 
     */
    
    public Articolo(String nome, float costo, String categoria, int quantita) {
        this.nome = nome;
        this.costo = costo;
        this.categoria = categoria;
        this.quantita = quantita;
    }
    
    /**
     * Costruttore per oggetti Articolo senza categoria e quantità specificate per la buona programmazione non vengono ripetute
     * @param nome
     * @param costo
     */
   
    
   /**
    * Imposta valore alla categoria
    * @param categoria valore della categoria inserito
    */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    /**
     * Imposta valore al costo
     * @param costo valore del costo inserito
     */
    
    public void setCosto(float costo) {
        this.costo = costo;
    }
    
    
    /**
     * Imposta valore alla quantità
     * @param quantità valore della quantità
     */
    
    public void setQuantita(int quantita){
        this.quantita = quantita;
    }
    
    /**
     * Permette di ottenere e di usare il categoria dell'articolo nelle altre classi
     * @param getCosto Ritorna la categoria dell'articolo
     */

    public String getCategoria() {
        return categoria;
    }
    
    /**
     * Permette di ottenere e di usare il costo dell'articolo nelle altre classi
     * @param getCosto Ritorna il costo dell'articolo
     */

    public float getCosto() {
        return costo;
    }
    
    /**
     * Permette di ottenere e di usare la quantita dell'articolo nelle altre classi
     * @param getQuantita Ritorna il nome dell'articolo
     */

    public int getQuantita() {
        return quantita;
    }
    
    /**
     * Permette di ottenere e di usare il nome dell'articolo nelle altre classi
     * @param getNome Ritorna il nome dell'articolo
     */

    public String getNome() {
        return nome;
    }
}
