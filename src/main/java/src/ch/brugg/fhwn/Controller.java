package main.java.src.ch.brugg.fhwn;

import main.java.src.ch.brugg.fhwn.dto.Email;
import main.java.src.ch.brugg.fhwn.dto.Wort;
import main.java.src.ch.brugg.fhwn.reader.Reader;

import java.util.ArrayList;
import java.util.List;

//TODO Mel
public class Controller {

    //TODO muss definiert werden
    private final int SCHWELLENWERT = 0;
    private Reader reader = new Reader();
    private List<Wort> woerter = new ArrayList<>();

    //Todo Faustina: kannst Du mir hier eine Liste mit allen Mails geben?
    private List<Email> emailList = new ArrayList<>();

    //TODO
    //Hier werden alle Mails durch iteriert
    // in der iteration wird die wortliste der Mail rausgezogen und mit der woesterListe verglichen
    //wenn ein Wort noch nicht in woersterListe existiert, dieses adden und den zugehörigen Counter raufzählen
    //wenn schon existiert nur counter raufzählen

    public void woerterDurchgehen() {
        this.hamMailWoerterVergleichMitWortListe();
        this.spamMailWoerterVergleichMitWortListe();

        spamwahrscheinlichkeitWort();
        spamWahrscheinlichlkeitMail();
    }

    private void hamMailWoerterVergleichMitWortListe() {
        for (Email email : this.reader.getHamMails()) {
            for (Wort emailWort : email.getWoerter()) {
                for (Wort wort : woerter) {
                    if (wort.getBezeichnug().equals(emailWort.getBezeichnug())) {
                        wort.setAnzahlHamMails(wort.getAnzahlHamMails() + 1);
                    } else {
                        woerter.add(emailWort);
                    }
                }
            }
        }
    }

    private void spamMailWoerterVergleichMitWortListe() {
        for (Email email : this.reader.getSpamMails()) {
            for (Wort emailWort : email.getWoerter()) {
                for (Wort wort : woerter) {
                    if (wort.getBezeichnug().equals(emailWort.getBezeichnug())) {
                        wort.setAnzahlSpamMails(wort.getAnzahlSpamMails() + 1);
                    } else {
                        woerter.add(emailWort);
                    }
                }
            }
        }
    }

    //Mit Bayes berechnen
    public void spamwahrscheinlichkeitWort() {
        for (Email email : this.reader.getSpamMails()) {
            for (Wort wort : email.getWoerter()) {
                setSpamwahrscheinlichkeitByWort(wort);
            }
        }
    }


    //TODO alle Spamwahrscheinlichkeiten -hamWahrscheinlichkeiten ausrechnen
    public void spamWahrscheinlichlkeitMail() {
        for (Email email : emailList) {
            double spamWahrscheinlichkeit = 1.0;
            for (Wort wort : email.getWoerter()) {
                spamWahrscheinlichkeit = spamWahrscheinlichkeit * wort.getSpamWahrscheinlichekeit();
            }
            email.setSpamWahrscheinlichkeit(spamWahrscheinlichkeit);
        }
    }


    private void setSpamwahrscheinlichkeitByWort(Wort wort) {
        int anzahlMail = reader.getHamMails().size() + reader.getSpamMails().size();

        double zaehler = wort.getAnzahlHamMails() / anzahlMail;
        double nenner = (wort.getAnzahlHamMails() + wort.getAnzahlSpamMails()) / anzahlMail;

        wort.setSpamWahrscheinlichekeit(zaehler / nenner);
    }


    //Todo Melanie: Kontrollieren, welche Methode korrekt ist (evtl. ergibt es dasselbe Resultat)
    private void setSpamwahrscheinlichkeitByWort2(Wort wort) {
        int anzahlMail = reader.getHamMails().size() + reader.getSpamMails().size();

        double P_SpamWord = wort.getAnzahlSpamMails() / anzahlMail;
        double P_Spam = 0.5;
        double P_HamWord = wort.getAnzahlHamMails() / anzahlMail;
        double P_Ham = 0.5;

        double wahrscheinlichkeit = P_SpamWord * P_Spam / (P_SpamWord * P_Spam + P_HamWord * P_Ham);
        wort.setSpamWahrscheinlichekeit(wahrscheinlichkeit);
    }
}
