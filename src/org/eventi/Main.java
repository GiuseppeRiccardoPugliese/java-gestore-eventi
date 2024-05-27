package org.eventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Apro lo Scanner
        Scanner scanner = new Scanner(System.in);

//      Chiedere all’utente di inserire un nuovo evento con tutti i parametri

        try {
            // Chiedere all'utente di inserire i dati per il nuovo evento

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

        } catch (Exception e) {
            System.out.println("Errore durante la creazione dell'evento: " + e.getMessage());
        }finally {
            scanner.close();
        }
    }
}
