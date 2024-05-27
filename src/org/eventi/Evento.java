package org.eventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {
    //ATTRIBUTES
    private String titolo;
    private LocalDate data;
    private final int postiTotali;
    private int postiPrenotati;

    //CONSTRUCT
    public Evento(String titolo, LocalDate data, int postiTotali) throws IllegalArgumentException{
        //Controlli se la data e' prima
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento non può essere nel passato.");
        }
        //Controlli se il numero di posti totali e' minore o uguale a 0
        if (postiTotali <= 0) {
            throw new IllegalArgumentException("Il numero di posti totali deve essere positivo.");
        }

        this.titolo = titolo;
        this.data = data;
        this.postiTotali = postiTotali;
        this.postiPrenotati = 0;
    }

    //GETTER & SETTER TITOLO
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    //GETTER & SETTER DATA
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) throws IllegalArgumentException {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento non può essere nel passato.");
        }

        this.data = data;
    }

    //GETTER POSTI TOTALI
    public int getPostiTotali() {
        return postiTotali;
    }

    //GETTER POSTI PRENOTATI
    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    //METHODS

    //Metodo PRENOTA per prenotare i posti
    public void prenota(int numeroPosti) throws IllegalArgumentException{
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Non è possibile prenotare posti per un evento passato.");
        }
        if (postiPrenotati + numeroPosti > postiTotali) {
            throw new IllegalArgumentException("Non ci sono abbastanza posti disponibili.");
        }
        postiPrenotati += numeroPosti;
    }

    //Metodo DISDICI per disdire i posti
    public void disdici(int numeroPosti) throws IllegalArgumentException{
        if (data.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Non è possibile prenotare posti per un evento passato.");
        }
        if (numeroPosti > postiPrenotati) {
            throw new IllegalArgumentException("Non ci sono abbastanza prenotazioni da disdire.");
        }
        postiPrenotati -= numeroPosti;
    }

    //Metodo data formattata
    public String getDataFormattata() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return data.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return data.format(formatter) + " - " + titolo;
    }
}
