package com.maven.ristorante;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    Ristorante r = new Ristorante();


    @Test
    public void aggiungiClienteTest()
    {
        int l = r.prenotazioni.size();
        r.aggiungiCliente("Zebbone");
        assertEquals(l, r.prenotazioni.size()-1);
    }


    @Test
    public void modificaClienteTest(){
        int l = r.prenotazioni.size();
        r.aggiungiCliente("Zebbone");
        r.modificaCliente("Zebbone", "Darione");
        assertEquals(l, r.prenotazioni.size()-1);
        assertTrue(!r.prenotazioni.containsKey("Zebbone"));
        assertTrue(r.prenotazioni.containsKey("Darione"));
    }


    @Test
    public void rimuoviClienteTest(){
        r.aggiungiCliente("Zebbone");
        r.rimuoviCliente("Zebbone");
        assertTrue(!r.prenotazioni.containsKey("Zebbone"));
    }


    @Test
    public void aggiungiPrenotazioneTest(){
        r.aggiungiCliente("Zebbone");
        int l = r.prenotazioni.get("Zebbone").size();
        Prenotazione p = new Prenotazione("Zebbone", LocalDate.of(2023,03,27), 5);
        r.aggiungiPrenotazione(p);
        assertEquals(l, r.prenotazioni.get("Zebbone").size()-1);
    }


    @Test
    public void ModificaPrenotazioneTest(){
        r.aggiungiCliente("Zebbone");
        r.aggiungiCliente("Darione");
        Prenotazione p1 = new Prenotazione("Zebbone", LocalDate.of(2023,03,27), 5);
        r.aggiungiPrenotazione(p1);
        Prenotazione p2 = new Prenotazione("Zebbone", LocalDate.of(2023,03,27), 7);
        r.modificaPrenotazione(p1, p2);
        assertEquals(7, r.prenotazioni.get("Zebbone").get(0).coperti);
        Prenotazione p3 = new Prenotazione("Darione", LocalDate.of(2023,03,27), 7);
        r.modificaPrenotazione(p2, p3);
        assertEquals(1, r.prenotazioni.get("Darione").size());
    }


    @Test
    public void eliminaPrenotazioneTest(){
        r.aggiungiCliente("Zebbone");
        Prenotazione p = new Prenotazione("Zebbone", LocalDate.of(2023,03,27), 5);
        r.aggiungiPrenotazione(p);
        r.eliminaPrenotazione(p);
        assertEquals(0, r.prenotazioni.get("Zebbone").size());
    }


    @Test
    public void ricercaPerClienteTest(){
        r.aggiungiCliente("Zebbone");
        r.aggiungiCliente("Darione");
        Prenotazione p1 = new Prenotazione("Zebbone", LocalDate.of(2023,03,27), 5);
        Prenotazione p2 = new Prenotazione("Zebbone", LocalDate.of(2023,03,27), 8);
        Prenotazione p3 = new Prenotazione("Darione", LocalDate.of(2023,03,27), 8);
        r.aggiungiPrenotazione(p1);
        r.aggiungiPrenotazione(p2);
        r.aggiungiPrenotazione(p3);
        assertEquals(2, r.ricercaPerCliente("Zebbone").count());
    }


    @Test
    public void ricercaPerDataTest(){
        r.aggiungiCliente("Zebbone");
        r.aggiungiCliente("Darione");
        Prenotazione p1 = new Prenotazione("Zebbone", LocalDate.of(2023,03,25), 5);
        Prenotazione p2 = new Prenotazione("Zebbone", LocalDate.of(2023,03,27), 8);
        Prenotazione p3 = new Prenotazione("Darione", LocalDate.of(2023,03,25), 8);
        r.aggiungiPrenotazione(p1);
        r.aggiungiPrenotazione(p2);
        r.aggiungiPrenotazione(p3);
        assertEquals(2, r.ricercaPerData(LocalDate.of(2023,03,25)).count());
    }


    @Test
    public void copertiPerDataTest(){
        r.aggiungiCliente("Zebbone");
        r.aggiungiCliente("Darione");
        Prenotazione p1 = new Prenotazione("Zebbone", LocalDate.of(2023,03,25), 5);
        Prenotazione p2 = new Prenotazione("Zebbone", LocalDate.of(2023,03,27), 9);
        Prenotazione p3 = new Prenotazione("Darione", LocalDate.of(2023,03,25), 8);
        r.aggiungiPrenotazione(p1);
        r.aggiungiPrenotazione(p2);
        r.aggiungiPrenotazione(p3);
        assertEquals(13, r.copertiPerData(LocalDate.of(2023,03,25)));
    }

    @Test
    public void copertiPerClienteTest(){
        r.aggiungiCliente("Zebbone");
        r.aggiungiCliente("Darione");
        Prenotazione p1 = new Prenotazione("Zebbone", LocalDate.of(2023,03,25), 5);
        Prenotazione p2 = new Prenotazione("Zebbone", LocalDate.of(2023,03,27), 9);
        Prenotazione p3 = new Prenotazione("Darione", LocalDate.of(2023,03,25), 8);
        r.aggiungiPrenotazione(p1);
        r.aggiungiPrenotazione(p2);
        r.aggiungiPrenotazione(p3);
        assertEquals(14, r.copertiPerCliente("Zebbone"));
    }

    @Test
    public void copertiInRangeTest(){
        r.aggiungiCliente("Zebbone");
        r.aggiungiCliente("Darione");
        Prenotazione p1 = new Prenotazione("Zebbone", LocalDate.of(2023,03,25), 5);
        Prenotazione p2 = new Prenotazione("Zebbone", LocalDate.of(2023,03,27), 9);
        Prenotazione p3 = new Prenotazione("Darione", LocalDate.of(2023,03,25), 8);
        r.aggiungiPrenotazione(p1);
        r.aggiungiPrenotazione(p2);
        r.aggiungiPrenotazione(p3);
        assertEquals(13, r.copertiInRange(LocalDate.of(2022, 4, 1), LocalDate.of(2023, 03, 26)));
    }

    @Test
    public void maggiorNumeroCopertiTest(){
        r.aggiungiCliente("Zebbone");
        r.aggiungiCliente("Darione");
        Prenotazione p1 = new Prenotazione("Zebbone", LocalDate.of(2023,03,25), 5);
        Prenotazione p2 = new Prenotazione("Zebbone", LocalDate.of(2023,03,27), 9);
        Prenotazione p3 = new Prenotazione("Darione", LocalDate.of(2023,03,25), 8);
        Prenotazione p4 = new Prenotazione("Darione", LocalDate.of(2080,03,25), 99);
        r.aggiungiPrenotazione(p1);
        r.aggiungiPrenotazione(p2);
        r.aggiungiPrenotazione(p3);
        r.aggiungiPrenotazione(p4);
        assertEquals(LocalDate.of(2023, 03, 25), r.maggiorNumeroCoperti());
    }

    @Test
    public void clientePiuCopertiTest(){
        r.aggiungiCliente("Zebbone");
        r.aggiungiCliente("Darione");
        Prenotazione p1 = new Prenotazione("Darione", LocalDate.of(2023,03,25), 5);
        Prenotazione p2 = new Prenotazione("Zebbone", LocalDate.of(2023,03,27), 9);
        Prenotazione p3 = new Prenotazione("Darione", LocalDate.of(2023,03,25), 8);
        r.aggiungiPrenotazione(p1);
        r.aggiungiPrenotazione(p2);
        r.aggiungiPrenotazione(p3);
        assertEquals("Darione", r.clientePiuCoperti());
    }

    @Test
    public void clientiOrdinatiPerNumeroPrenotazioniTest(){
        r.aggiungiCliente("Zebbone");
        r.aggiungiCliente("Darione");
        r.aggiungiCliente("Pluto");
        Prenotazione p1 = new Prenotazione("Darione", LocalDate.of(2023,03,25), 5);
        Prenotazione p2 = new Prenotazione("Zebbone", LocalDate.of(2023,03,27), 9);
        Prenotazione p3 = new Prenotazione("Darione", LocalDate.of(2023,03,25), 8);
        Prenotazione p4 = new Prenotazione("Darione", LocalDate.of(2080,03,25), 99);
        Prenotazione p5 = new Prenotazione("Zebbone", LocalDate.of(2080,03,25), 99);
        Prenotazione p6 = new Prenotazione("Pluto", LocalDate.of(2080,03,25), 99);
        r.aggiungiPrenotazione(p1);
        r.aggiungiPrenotazione(p2);
        r.aggiungiPrenotazione(p3);
        r.aggiungiPrenotazione(p4);
        r.aggiungiPrenotazione(p5);
        r.aggiungiPrenotazione(p6);
        assertEquals("Pluto", r.clientiOrdinatiPerNumeroPrenotazioni().stream().findFirst().get());
    }

    @Test
    public void ClienteGiaInseritoExceptionTest(){
        r.aggiungiCliente("Zebbone");
        assertThrows(ClienteGiaInseritoException.class, () -> r.aggiungiCliente("Zebbone"));
    }

    @Test
    public void ClienteNonEsistenteExceptionTest(){
        assertThrows(ClienteNonEsistenteException.class, () -> r.aggiungiPrenotazione(new Prenotazione("Zebbone", LocalDate.of(2023,03,25), 5)));
    }

    @Test
    public void PrenotazioneNonEsistenteExceptionTest(){
        r.aggiungiCliente("Zebbone");
        assertThrows(PrenotazioneNonEsistenteException.class, () -> r.eliminaPrenotazione(new Prenotazione("Zebbone", LocalDate.of(2023,03,25), 5)));
    }
}

