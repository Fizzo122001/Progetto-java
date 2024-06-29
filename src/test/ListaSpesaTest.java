package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import progetto.Articolo;
import progetto.ListaSpesa;


public class ListaSpesaTest {

    private ListaSpesa listaSpesa;
    private Articolo articolo1;
    private Articolo articolo2;
    private Articolo articolo3;

    @BeforeEach
    public void setUp() {
        listaSpesa = new ListaSpesa("Spesa Settimanale");
        articolo1 = new Articolo("Latte", 1.20f, "Alimentari", 2);
        articolo2 = new Articolo("Sapone", 1, "Igiene", 1);
        articolo3 = new Articolo("Pasta", 1, "Alimentari", 3);
    }

    @Test
    public void testAggiungiArticolo() {
        listaSpesa.aggiungi(articolo1);
        listaSpesa.aggiungi(articolo2);
        listaSpesa.aggiungi(articolo3);
        assertEquals(3, listaSpesa.getArticoli().size());
        assertTrue(listaSpesa.getArticoli().contains(articolo1));
        assertTrue(listaSpesa.getArticoli().contains(articolo2));
        assertTrue(listaSpesa.getArticoli().contains(articolo3));
    }

    @Test
    public void testRimuoviArticolo() {
        listaSpesa.aggiungi(articolo1);
        listaSpesa.aggiungi(articolo2);
        listaSpesa.aggiungi(articolo3);
        listaSpesa.rimuoviArticolo(articolo1);
        listaSpesa.rimuoviArticolo(articolo2);
        assertEquals(1, listaSpesa.getArticoli().size());
        assertTrue(listaSpesa.getArticoli().contains(articolo3));
    }

    @Test
    public void testRimuoviCategoria() {
        listaSpesa.aggiungi(articolo1);
        listaSpesa.aggiungi(articolo2);
        listaSpesa.aggiungi(articolo3);
        listaSpesa.rimuoviCategoria("Alimentari");
        assertEquals(3, listaSpesa.getArticoli().size());
        assertEquals("non categorizzati", articolo1.getCategoria());
        assertEquals("Igiene", articolo2.getCategoria());
        assertEquals("non categorizzati", articolo3.getCategoria());
    }

    @Test
    public void testTrovaCategoria() {
        listaSpesa.aggiungi(articolo1);
        listaSpesa.aggiungi(articolo2);
        listaSpesa.aggiungi(articolo3);
        assertEquals(3, listaSpesa.getArticoli().size());
        assertTrue(listaSpesa.trovaCategoria("Alimentari"));
        assertFalse(listaSpesa.trovaCategoria("Vino"));
    }

    @Test
    public void testCreaLista() {
        ListaSpesa listaFede = new ListaSpesa("Fede");
        assertEquals("Fede", listaFede.getNome());
        assertEquals(0, listaFede.getArticoli().size());
        listaFede.aggiungi(articolo1);
        listaFede.aggiungi(articolo2);
        listaFede.aggiungi(articolo3);
        assertEquals(3, listaFede.getArticoli().size());
    }

    @Test
    public void testPrezzo() {
        listaSpesa.aggiungi(articolo1);
        listaSpesa.aggiungi(articolo2);
        listaSpesa.aggiungi(articolo3);
        assertEquals(6.4f, listaSpesa.Prezzo(), 0.001f);
    }

}
