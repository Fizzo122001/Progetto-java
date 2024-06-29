package progetto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import jbook.util.Input;

public class Main {

    private static GestioneListe gestioneListe = new GestioneListe();
    private static ListaSpesa listaCorrente = null;

    public static void main(String[] args) throws SceltaException {
        try {
            int scelta;
            do {
                System.out.println("Menu:");
                System.out.println("1. Aggiungi articolo");
                System.out.println("2. Rimuovi articolo");
                System.out.println("3. Visualizza lista della spesa");
                System.out.println("4. Salva su File");
                System.out.println("5. Crea una nuova lista");
                System.out.println("6. Visualizza tutte le liste");
                System.out.println("7. Visualizza il totale");
                System.out.println("8. Elimina categoria");
                System.out.println("9. Visualizza articoli data una categoria");
                System.out.println("10. Quale lista vuoi scegliere?");
                System.out.println("11. Copia una lista della spesa da un file");
                System.out.println("0. Esci");
                System.out.print("Scelta: ");
                scelta = Input.readInt();
                if (scelta > 11) {
                    throw new SceltaException(null);
                }

                switch (scelta) {
                    case 1:
                        aggiungiArticolo(getListaSpesa());
                        break;
                    case 2:
                        rimuoviArticolo();
                        break;
                    case 3:
                        visualizzaListaSpesa(getListaSpesa());
                        break;
                    case 4:
                        salvaSuFile(getListaSpesa());
                        break;
                    case 5:
                        listaCorrente = ListaSpesa.creaLista();
                        gestioneListe.aggiungiLista(listaCorrente);
                        System.out.println("Nuova lista della spesa \"" + listaCorrente.getNome() + "\" creata con successo.");
                        break;
                    case 6:
                        visualizzaTutteLeListe();
                        break;
                    case 7:
                        getListaSpesa().Prezzo();
                        break;
                    case 8:
                        System.out.print("Inserisci la categoria da rimuovere: ");
                        String categoriaDaRimuovere = Input.readString();
                        getListaSpesa().rimuoviCategoria(categoriaDaRimuovere);
                        System.out.println("Categoria rimossa dagli articoli nella lista della spesa.");
                        break;
                    case 9:
                        System.out.print("Inserisci la categoria da cercare: ");
                        String categoriaDaTrovare = Input.readString();
                        getListaSpesa().trovaCategoria(categoriaDaTrovare);
                        break;
                    case 10:
                        if (listaCorrente == null) {
                            System.out.println("Non ci sono liste di spesa create. Creane una nuova.");
                            listaCorrente = ListaSpesa.creaLista();
                            gestioneListe.aggiungiLista(listaCorrente);
                            System.out.println("Nuova lista della spesa \"" + listaCorrente.getNome() + "\" creata con successo.");
                        } else {
                            scegliLista();
                        }
                        break;
                    case 11:
                        caricaDaFile();
                        break;
                    case 0:
                        System.out.println("Stai uscendo");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                        break;
                }
            } while (scelta != 0);
        } catch (IOException e) {
            System.out.println("Errore di input/output: " + e.getMessage());
            main(args);
        } catch (NomeVuotoException e) {
            System.out.println("Errore: il nome dell'articolo non può essere vuoto.");
            main(args);
        } catch (QuantitaNegativaException e) {
            System.out.println("Errore: la quantità non può essere negativa.");
            main(args);
        } catch (NomeStringaException e) {
            System.out.println("Errore: il nome dell'articolo non può essere un numero.");
            main(args);
        } catch (RimuoviException e) {
            System.out.println("Errore: La lista è vuota non puoi rimuovere articoli.");
            main(args);
        } catch (CostoException e) {
            System.out.println("Errore: Il costo non può essere una stringa,deve essere un numero.");
            main(args);
        }
    }

    private static void scegliLista() {
        if (gestioneListe.size() == 0) {
            System.out.println("Non ci sono liste di spesa create. Creane una nuova.");
            listaCorrente = ListaSpesa.creaLista();
            gestioneListe.aggiungiLista(listaCorrente);
            System.out.println("Nuova lista della spesa \"" + listaCorrente.getNome() + "\" creata con successo.");
        } else {
            visualizzaTutteLeListe();
            System.out.print("Scegli una lista (inserisci il numero corrispondente): ");
            int index = Input.readInt();
            if (index > 0 && index <= gestioneListe.size()) {
                listaCorrente = gestioneListe.get(index - 1);
                System.out.println("Hai scelto la lista: " + listaCorrente.getNome());
            } else {
                System.out.println("Indice non valido. Riprova.");
                scegliLista();
            }
        }
    }

    private static void aggiungiArticolo(ListaSpesa listaSpesa)
            throws NomeVuotoException, QuantitaNegativaException, NomeStringaException , CostoException {
        listaSpesa.aggiungiArticolo();
    }

    private static ListaSpesa getListaSpesa() {
        if (listaCorrente == null) {
            if (gestioneListe.size() == 0) {
                System.out.println("Non ci sono liste di spesa create. Creane una nuova.");
                listaCorrente = ListaSpesa.creaLista();
                gestioneListe.aggiungiLista(listaCorrente);
                System.out.println("Nuova lista della spesa \"" + listaCorrente.getNome() + "\" creata con successo.");
            } else {
                listaCorrente = gestioneListe.get(0);
            }
        }
        return listaCorrente;
    }

    private static void rimuoviArticolo() throws RimuoviException{
    	if(listaCorrente == null) {
    		 throw new RimuoviException("");
    	}
        listaCorrente.rimuoviArticolo();
    }

    private static void visualizzaListaSpesa(ListaSpesa listaSpesa) {
        System.out.println("Lista della spesa: " + listaSpesa.getNome());
        for (Articolo articolo : listaSpesa.getArticoli()) {
            System.out.println("Nome: " + articolo.getNome() + ", Costo: " + articolo.getCosto() + ", Quantità: "
                    + articolo.getQuantita() + ", Categoria: " + articolo.getCategoria());
        }
    }

    private static void salvaSuFile(ListaSpesa listaSpesa) throws IOException {
        System.out.print("Inserisci il nome del file per salvare la lista: ");
        String nomeFile = Input.readString();
        File file = new File(nomeFile);

        float prezzoTotale = 0;
        for (Articolo articolo : listaSpesa.getArticoli()) {
            prezzoTotale = prezzoTotale + (articolo.getCosto() * articolo.getQuantita());
        }

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("Questa è la lista di " + listaSpesa.getNome());
            for (Articolo articolo : listaSpesa.getArticoli()) {
                writer.println("Nome: " + articolo.getNome() + ", Costo: " + articolo.getCosto() + ", Quantità: "
                        + articolo.getQuantita() + ", Categoria: " + articolo.getCategoria());
            }
            writer.println("Prezzo totale: " + prezzoTotale);
            System.out.println("Lista della spesa salvata su file: " + nomeFile);
        }
    }

    private static void visualizzaTutteLeListe() {
        System.out.println("Tutte le liste di spesa:");
        int index = 1;
        for (ListaSpesa lista : gestioneListe) {
            System.out.println(index + ". " + lista.getNome());
            index++;
        }
    }

    private static void caricaDaFile()
            throws IOException, NomeVuotoException, QuantitaNegativaException, NomeStringaException {
        System.out.print("Inserisci il nome del file da cui caricare la lista: ");
        String nomeFile = Input.readString();
        File file = new File(nomeFile);

        if (!file.exists()) {
            System.out.println("File non trovato.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.print("Inserisci il nome della nuova lista della spesa: ");
            String nomeLista = Input.readString();
            ListaSpesa nuovaLista = new ListaSpesa(nomeLista);
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Nome: ")) {
                    String[] parts = line.split(", ");
                    String nome = parts[0].substring(6);
                    float costo = Float.parseFloat(parts[1].substring(7));
                    int quantita = Integer.parseInt(parts[2].substring(10));
                    String categoria = parts[3].substring(11);
                    Articolo articolo = new Articolo(nome, costo, categoria, quantita);
                    nuovaLista.aggiungi(articolo);
                }
            }

            gestioneListe.aggiungiLista(nuovaLista);
            listaCorrente = nuovaLista;
            System.out.println("Lista della spesa caricata con successo da " + nomeFile);
        }
    }
}
