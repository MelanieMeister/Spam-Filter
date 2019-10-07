package main.java.src.ch.brugg.fhwn;

import main.java.src.ch.brugg.fhwn.dto.Email;
import main.java.src.ch.brugg.fhwn.dto.Wort;
import main.java.src.ch.brugg.fhwn.reader.Reader;

import java.util.ArrayList;
import java.util.List;

//TODO Mel
public class Controller {

    private Reader reader = new Reader();

    //TODO muss definiert werden
    private final int SCHWELLENWERT = 0;
    private List<Wort> woerter = new ArrayList<>();

    //TODO
    //Hier werden alle Mails durch iteriert
    // in der iteration wird die wortliste der Mail rausgezogen und mit der woesterListe verglichen
    //wenn ein Wort noch nicht in woersterListe existiert, dieses adden und den zugehörigen Counter raufzählen
    //wenn schon existiert nur counter raufzählen

    public void woerterDurchgehen() {
        this.hamMailWoerterVergleichMitWortListe();
        this.spamMailWoerterVergleichMitWortListe();
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
    }

    //TODO alle Spamwahrscheinlichkeiten -hamWahrscheinlichkeiten ausrechnen
    public void spamWahrscheinlichlkeitMail() {
    }

}
