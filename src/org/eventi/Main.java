package org.eventi;

import org.eventi.bonus.ProgrammaEventi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Apro lo Scanner
        Scanner scanner = new Scanner(System.in);

        //Istanza ProgrammaEventi
        ProgrammaEventi programma = new ProgrammaEventi("Il mio programma di eventi: ");

        boolean stop = true; //Booleano per il ciclo infinito finche' non ritorna false

        while (stop) {
            try {
                System.out.println("Vuoi creare un concerto o un evento? (c/e) " + "\nOppure visualizza il tuo programma (v) ");
                String scelta = scanner.nextLine().toLowerCase();

                switch (scelta) {
                    case "c" : {
                        Evento evento = creaConcerto(scanner);
                        programma.aggiungiEvento(evento); //Aggiungo il mio concerto al programma
                        break;
                    }
                    case "e" : {
                        Evento evento = creaEvento(scanner);
                        programma.aggiungiEvento(evento); //Aggiungo il mio evento al programma
                        break;
                    }
                    case "v" : System.out.println(programma);
                        break;
                    default : {
                        System.out.println("Scelta non valida. Riprova.");
                        continue; // Ritorna all'inizio del loop
                    }
                }

                // Chiedo all'utente se vuole continuare
                System.out.println("Vuoi creare un altro evento o visulizzare il programma? (s/n/v)");
                String risposta = scanner.nextLine().toLowerCase();

                if (risposta.equals("n")) {
                    System.out.println("Ciao!");
                    stop = false;
                }
                if (risposta.equals("v")) {
                    System.out.println(programma);
                }
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        //Chiudo lo scanner
        scanner.close();
    }

    //Creo il mio concerto
    private static Concerto creaConcerto(Scanner scanner) {
        System.out.println("Inserisci il titolo del concerto:");
        String titolo = scanner.nextLine();

        System.out.println("Inserisci la data del concerto (formato: dd-MM-yyyy):");
        String dataInput = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate data = LocalDate.parse(dataInput, formatter);

        System.out.println("Inserisci l'ora del concerto (formato: HH:mm):");
        String oraInput = scanner.nextLine();
        LocalTime ora = LocalTime.parse(oraInput);

        System.out.println("Inserisci il numero totale di posti disponibili per il concerto:");
        int postiTotali = Integer.parseInt(scanner.nextLine());

        System.out.println("Inserisci il prezzo del biglietto per il concerto:");
        BigDecimal prezzo = new BigDecimal(scanner.nextLine());

        Concerto concerto = new Concerto(titolo, data, postiTotali, ora, prezzo);
        System.out.println("Concerto creato con successo: " + concerto);
        return concerto;
    }

    //Metodo per creare l'evento
    private static Evento creaEvento(Scanner scanner) {
        System.out.println("Inserisci il titolo dell'evento:");
        String titolo = scanner.nextLine(); //TITOLO

        System.out.println("Inserisci la data dell'evento (formato: dd-MM-yyyy):");
        String dataInput = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate data = LocalDate.parse(dataInput, formatter); //DATA

        System.out.println("Inserisci il numero totale di posti:");
        int postiTotali = Integer.parseInt(scanner.nextLine()); //POSTI TOT

        // Creazione dell'evento, ovvero creazione dell'OGGETTO Evento
        Evento evento = new Evento(titolo, data, postiTotali);
        System.out.println("Evento creato con successo: " + evento);

        // Gestire le prenotazioni
        System.out.println("Vuoi effettuare delle prenotazioni? (s/n):");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.println("Quanti posti vuoi prenotare?");
            int postiDaPrenotare = Integer.parseInt(scanner.nextLine());
            try {
                evento.prenota(postiDaPrenotare);
                System.out.println("Posti prenotati con successo.");
            } catch (IllegalArgumentException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

        // Stampare il numero di posti prenotati e disponibili
        System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
        System.out.println("Posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));


        // Gestire le disdette per le prenotazioni
        System.out.println("Vuoi disdire delle prenotazioni? (s/n):");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.println("Quanti posti vuoi disdire?");
            int postiDaDisdire = Integer.parseInt(scanner.nextLine());
            try {
                evento.disdici(postiDaDisdire);
                System.out.println("Posti disdetti con successo.");
            } catch (IllegalArgumentException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

        // Stampare il numero di posti prenotati e disponibili
        System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
        System.out.println("Posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));
        return evento;
    }
}
