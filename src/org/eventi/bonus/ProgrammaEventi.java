package org.eventi.bonus;

import org.eventi.Evento;

import java.time.LocalDate;
import java.util.ArrayList;
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
        return eventi.stream()
                .filter(evento -> evento.getData().equals(data))
                .collect(Collectors.toList());
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
        return titolo + "\n" + eventi.stream()
                .sorted((e1, e2) -> e1.getData().compareTo(e2.getData()))
                .map(evento -> evento.getDataFormattata() + " - " + evento.getTitolo())
                .collect(Collectors.joining("\n"));
    }
}
