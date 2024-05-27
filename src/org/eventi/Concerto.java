package org.eventi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento{

    //ATTRIBUTES
    private LocalTime ora;
    private BigDecimal prezzo;

    //CONSTRUCT
    public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, BigDecimal prezzo) throws IllegalArgumentException {
        super(titolo, data, postiTotali);
        this.ora = ora;
        this.prezzo = prezzo;
    }

    //GETTER & SETTER
    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    //METHODS

    // Metodo per restituire data e ora formattata
    public String getDataOraFormattata() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return getData().format(dateFormatter) + " " + ora.format(timeFormatter);
    }

    // Metodo per restituire il prezzo formattato
    public String getPrezzoFormattato() {
        return prezzo.setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "€";
        //setScale 2 : numero di cifre decimali a 2
        //modo di arrotondamento. Se la cifra immediatamente a destra della posizione da arrotondare è 5 o superiore, viene incrementata
    }

    @Override
    public String toString() {
        return getDataOraFormattata() + " - " + getTitolo() + " - " + getPrezzoFormattato();
    }
}
