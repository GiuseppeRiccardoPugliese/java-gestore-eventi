package org.eventi.bonus;

import org.eventi.Evento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProgrammaEventi {

    private String titolo;
    private List<Evento> eventi;

    public ProgrammaEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }

    // Metodo per aggiungere un evento alla lista
    public void aggiungiEvento(Evento evento) {
        eventi.add(evento);
    }

    // Metodo che restituisce una lista con tutti gli eventi presenti in una certa data
    public List<Evento> getEventiInData(LocalDate data) {
        List<Evento> eventiInData = new ArrayList<>();
        for (Evento evento : eventi) {
            if (evento.getData().equals(data)) {
                eventiInData.add(evento);
            }
        }
        return eventiInData;
    }

    // Metodo che restituisce quanti eventi sono presenti nel programma
    public int getNumeroEventi() {
        return eventi.size();
    }

    // Metodo che svuota la lista di eventi
    public void svuotaEventi() {
        eventi.clear();
    }

    // Metodo che restituisce una stringa con il titolo del programma e tutti gli eventi ordinati per data
    @Override
    public String toString() {
        String result = titolo + "\n";

        // Ordinare la lista di eventi per data
        List<Evento> eventiOrdinati = new ArrayList<>(eventi);
        eventiOrdinati.sort(new Comparator<Evento>() { //.sort ordino la lista secondo il comparatore anonimo
            @Override
            public int compare(Evento e1, Evento e2) {
                return e1.getData().compareTo(e2.getData());
                //Confronto le date dei due eventi e restituisco un valore negativo, zero o positivo a seconda che e1 sia rispettivamente precedente, uguale o successivo a e2
            }
        });

        // Ciclo nell'arrayList nuova tutti gli eventi e li aggiungo alla stringa "result"
        for (Evento evento : eventiOrdinati) {
            result += evento.getDataFormattata() + " - " + evento.getTitolo() + "\n";
        }

        return result; // restituisco la stringa result che contenente il titolo del programma seguito da tutti gli eventi ordinati per data
    }
}
