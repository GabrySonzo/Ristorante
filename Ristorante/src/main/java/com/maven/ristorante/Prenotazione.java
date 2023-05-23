package com.maven.ristorante;

import java.time.*;

public class Prenotazione
{
    String cliente;
    LocalDate dataInserimentoPrenotazione, dataPrenotazione ;
    int coperti;
    public Prenotazione(String cliente, LocalDate dataPrenotazione, int coperti){
        this.cliente = cliente;
        dataInserimentoPrenotazione = LocalDate.now();
        this.dataPrenotazione = dataPrenotazione;
        this.coperti = coperti;
    }
    
    public LocalDate getDataPrenotazione(){
        return dataPrenotazione;
    }
    
    public String toString(){
        return "Prenotazione di: " + cliente + " effettuata il " + dataInserimentoPrenotazione + " per il " + dataPrenotazione + " con N " + coperti + " coperti";
    }
}
