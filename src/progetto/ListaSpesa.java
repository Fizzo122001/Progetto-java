package progetto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import jbook.util.Input;

public class ListaSpesa implements Iterable<Articolo> {
    private String nome;
    private List<Articolo> articoli;

    /**
     * costruttore ListaSpesa
     * @param nome
     */
    
    public ListaSpesa(String nome) {
        this.nome = nome;
        this.articoli = new ArrayList<>();
    }
    
    /**
     * Il metodo aggiungi utilizzato come appoggio in aggiungiArticolo
     * @param articolo
     */
    public void aggiungi(Articolo articolo) {
        articoli.add(articolo);
    }
    
    /**
     * Il metodo aggiungiArticolo serve per creare e aggiungere un articolo alla lista della spesa
     * @throws NomeVuotoException lancia un'eccezione in caso il nome sia vuoto
     * @throws QuantitaNegativaException lancia un'eccezione in caso la quantita inserita è negativa
     * @throws NomeStringaException lancia un'eccezione in caso il nome dell'articolo sia negativo
     */

    public void aggiungiArticolo() throws NomeVuotoException, QuantitaNegativaException, NomeStringaException,CostoException {
        System.out.print("Nome articolo: ");
        String nome = Input.readString();
        if (nome.isEmpty()) {
            throw new NomeVuotoException(nome);
        }

        if (!nome.matches("[a-zA-Z ]+")) {
            throw new NomeStringaException(nome);
        }        
        
        System.out.print("Costo: ");
        String cos = Input.readString();
        cos = cos.replace(",", ".");
        if(cos.matches("[a-zA-Z]+")){
        	throw new CostoException("");
        }
        float costo = Float.parseFloat(cos);
        System.out.print("Inserisci categoria prodotto (opzionale): ");
        String categoria = Input.readString();
        if (categoria.isEmpty()) {
            System.out.println("Non hai inserito la categoria, verrà impostata su \"non categorizzati\"");
            categoria = "non categorizzati";
        }

        	System.out.print("Quantità (opzionale): ");
            int quantita = 1;
            String quant = Input.readString();
            if (quant.isEmpty()) {
                System.out.println("Non hai inserito la quantità, verrà impostata pari a 1");
            } else {
                quantita = Integer.parseInt(quant);
                if (quantita <= 0) {
                    throw new QuantitaNegativaException("");
                }
            }
            
            Articolo articolo = new Articolo(nome, costo, categoria, quantita);
            aggiungi(articolo);
            System.out.println("Articolo aggiunto alla lista della spesa.");
    }
    
    
    /**
     * Il metodo rimuoviArticolo serve per rimuovere un articolo, il quale chiede il nome in input
     *  e scorre la lista finche non lo trova(in caso non lo trovi non rimuove nulla)
     */
    public void rimuoviArticolo() {
        System.out.print("Nome articolo da rimuovere: ");
        String nomeArticoloDaRimuovere = Input.readString();
        boolean articoloTrovato = false;

        Iterator<Articolo> iterator = articoli.iterator();
        while (iterator.hasNext()) {
            Articolo articolo = iterator.next();
            if (articolo.getNome().equals(nomeArticoloDaRimuovere)) {
                iterator.remove();
                articoloTrovato = true;
                break;
            }
            else {
            	System.out.print("Articolo non trovato");
            }
        }


        if (articoloTrovato) {
            System.out.println("Articolo rimosso dalla lista della spesa e articoli aggiornati.");
        } else {
            System.out.println("Articolo non trovato nella lista della spesa.");
        }
    }
    
    /**
     * Rimuove la categoria in input 
     * @param categoriaDaRimuovere viene inserito come parametro la categoria da rimuovere la cerca e in caso la trova la rimuove
     */
    public void rimuoviCategoria(String categoriaDaRimuovere) {
        boolean categoriaTrovata = false;

        for (Articolo articolo : articoli) {
            if (articolo.getCategoria().equals(categoriaDaRimuovere)) {
                articolo.setCategoria("non categorizzati");
                categoriaTrovata = true;
            }
        }

        if (categoriaTrovata) {
            System.out.println("Categoria rimossa dalla lista della spesa e articoli aggiornati.");
        } else {
            System.out.println("Categoria non trovata nella lista della spesa.");
        }
    }
    
    
    /**
     * Il metodo trovaCategoria serve per creare un arrayList e va in 
     * ricerca di tutti gli articoli con la categoria data come parametro,poi stampa il nome di tutti gli articoli con quella categoria.
     * @param categoriaDaTrovare
     * @return falso o vero in caso trovi o meno la categoria
     */
    public boolean trovaCategoria(String categoriaDaTrovare) {
        List<Articolo> articoliTrovati = new ArrayList<>();
        boolean categoriaTrovata = false;

        for (Articolo articolo : articoli) {
            if (articolo.getCategoria().equals(categoriaDaTrovare)) {
                articoliTrovati.add(articolo);
                categoriaTrovata = true;
            }
        }

        if (categoriaTrovata) {
            System.out.println("Gli articoli nella categoria '" + categoriaDaTrovare + "' sono: ");
            for (Articolo articolo : articoliTrovati) {
                System.out.println(articolo.getNome());
            }
            return true;
            
        } else {
            System.out.println("Categoria non trovata nella lista della spesa.");
            return false;
          
        }
		
    }
    
    
    /**
     * Serve a ottenere tutti gli articoli prensenti nella lista
     * @return Articoli
     */
    public List<Articolo> getArticoli() {
        return articoli;
    }

    /**
     * Serve a ottnere il nome della lista della spesa
     * @return nome(della lista)
     */
    public String getNome() {
        return nome;
    }

    @Override
    public Iterator<Articolo> iterator() {
        return articoli.iterator();
    }
    
    
    /**
     * l'utente assegna alla lista della spesa un nome
     * @return il nome della lista
     */
    public static ListaSpesa creaLista() {
        System.out.print("Inserisci il nome della nuova lista della spesa: ");
        String nomeLista = Input.readString();
        return new ListaSpesa(nomeLista);
    }

    /**
     * Inserisce il nome della categoria da rimuovere per passarlo all metodo rimuoviCategoria(string ...)
     * @param listaSpesa
     */
    public static void rimuoviCategoria(ListaSpesa listaSpesa) {
        System.out.print("Nome categoria da rimuovere: ");
        String categoriaDaRimuovere = Input.readString();
        listaSpesa.rimuoviCategoria(categoriaDaRimuovere);
    }
    
    /**
     * Rimuove l'articolo viene richiamata nel mainFrame
     * @param articolo
     */
    public void rimuoviArticolo(Articolo articolo) {
        articoli.remove(articolo);
    }
    
    /**
     * Calcola il prezzo totale della lista della spesa con un for each
     * @return prezzo totale della lista della spesa
     */
    public float Prezzo() {
        float prezzo = 0;
        for (Articolo articolo : articoli) {
            prezzo = prezzo + (articolo.getCosto() * articolo.getQuantita());
        }
        System.out.println("Prezzo totale: " + prezzo);
        return prezzo;
    }
}
