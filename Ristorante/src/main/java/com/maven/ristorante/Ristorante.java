package com.maven.ristorante;

import java.util.HashMap;
import java.util.*;
import java.time.*;
import java.util.stream.*;

public class Ristorante
{
    
    HashMap<String, LinkedList<Prenotazione>> prenotazioni;    
    public Ristorante(){
        prenotazioni = new HashMap<String, LinkedList<Prenotazione>>();
    }
    
    public void aggiungiCliente(String cliente){
        if(prenotazioni.containsKey(cliente)){
            throw new ClienteGiaInseritoException();
        }
        prenotazioni.put(cliente, new LinkedList<Prenotazione>());
    }
    
    public void modificaCliente(String clienteVecchio, String clienteNuovo){
        if(!prenotazioni.containsKey(clienteVecchio)){
            throw new ClienteNonEsistenteException();
        }
        prenotazioni.get(clienteVecchio).forEach( a -> a.cliente = clienteNuovo);    
        prenotazioni.put(clienteNuovo, prenotazioni.get(clienteVecchio));
        prenotazioni.remove(clienteVecchio);
    }
    
    public void rimuoviCliente(String cliente){
        if(!prenotazioni.containsKey(cliente)){
            throw new ClienteNonEsistenteException();
        }
        prenotazioni.remove(cliente);
    }
    
    public void aggiungiPrenotazione(Prenotazione p){
        if(!prenotazioni.containsKey(p.cliente)){
            throw new ClienteNonEsistenteException();
        }
        prenotazioni.get(p.cliente).add(p);
    }
    
    public void modificaPrenotazione(Prenotazione pVecchia, Prenotazione pNuova){
        if(!prenotazioni.containsKey(pVecchia.cliente)){
            throw new ClienteNonEsistenteException();
        }
        if(!prenotazioni.get(pVecchia.cliente).contains(pVecchia)){
            throw new PrenotazioneNonEsistenteException();
        }
        eliminaPrenotazione(pVecchia);
        if(!prenotazioni.containsKey(pNuova.cliente)){
            aggiungiCliente(pNuova.cliente);
        }
        aggiungiPrenotazione(pNuova);
    }
    
    public void eliminaPrenotazione(Prenotazione p){
        if(!prenotazioni.containsKey(p.cliente)){
            throw new ClienteNonEsistenteException();
        }
        if(!prenotazioni.get(p.cliente).contains(p)){
            throw new PrenotazioneNonEsistenteException();
        }
        prenotazioni.get(p.cliente).remove(p);
    }
    
    public Stream<Prenotazione> ricercaPerCliente(String cliente){
        if(!prenotazioni.containsKey(cliente)){
            throw new ClienteNonEsistenteException();
        }
        return prenotazioni.get(cliente).stream();
    }
    
    public Stream<Prenotazione> ricercaPerData(LocalDate data){
        return prenotazioni.values().stream().flatMap(Collection::stream).filter(a -> a.dataPrenotazione.isEqual(data));
    }
    
    public int copertiPerData(LocalDate data){
        return prenotazioni.values().stream().flatMap(Collection::stream).filter(a -> a.dataPrenotazione.isEqual(data)).reduce(0, (partial, e) -> partial+e.coperti, Integer::sum);
    }
    
    public int copertiPerCliente(String cliente){
        return prenotazioni.get(cliente).stream().reduce(0, (partial, e) -> partial+e.coperti, Integer::sum);
    }
    
    public int copertiInRange(LocalDate data1, LocalDate data2){
        return prenotazioni.values().stream().flatMap(Collection::stream).filter(a -> a.dataPrenotazione.isAfter(data1) && a.dataPrenotazione.isBefore(data2)).reduce(0, (partial, e) -> partial+e.coperti, Integer::sum);
    }
    
    public LocalDate maggiorNumeroCoperti(){
        Prenotazione p = prenotazioni.values().stream().flatMap(Collection::stream).filter(a -> a.dataPrenotazione.isBefore(LocalDate.now())).reduce(
            prenotazioni.values().stream().flatMap(Collection::stream).findFirst().get(), (partial, actual) -> {
                if(copertiPerData(partial.dataPrenotazione) > copertiPerData (actual.dataPrenotazione)){
                    return partial;
                } 
                return actual;
                });
        return p.dataPrenotazione;
    }
    
    public String clientePiuCoperti(){
        Prenotazione p = prenotazioni.values().stream().flatMap(Collection::stream).filter(a -> a.dataPrenotazione.isBefore(LocalDate.now())).reduce(
            prenotazioni.values().stream().flatMap(Collection::stream).findFirst().get(), (partial, actual) -> {
                if(copertiPerCliente(partial.cliente) > copertiPerCliente (actual.cliente)){
                    return partial;
                } 
                return actual;
                });
        return p.cliente;
    }

    public LinkedList<String> clientiOrdinatiPerNumeroPrenotazioni(){
        LinkedList<String> s = new LinkedList<String>();
        prenotazioni.entrySet().stream().sorted((a,b) -> a.getValue().size() - b.getValue().size()).forEach(a -> s.add(a.getKey()));
        return s;
    }
}
