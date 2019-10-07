package main.java.src.ch.brugg.fhwn.reader;

import main.java.src.ch.brugg.fhwn.dto.Email;
import main.java.src.ch.brugg.fhwn.dto.EmailType;
import main.java.src.ch.brugg.fhwn.dto.Wort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static javax.script.ScriptEngine.FILENAME;

//TODO Faustina
//entzppen
//email generiert
//und woerter in email befüllt
public class Reader {

    private List<Email> hamMails = new ArrayList<>();
    private List<Email> spamMails = new ArrayList<>();

    private static final String PATHNAME_HAM = "./resources/";
    private static final String PATHNAME_SPAM = "./resources/";

    private InputStream input;

    //TODO entzippen
    public void enzippen(){

    }

    //TODO alle entippten Mails durchgehen und importieren
    public void mailsParsen (){


    }


    //TODO lesen des mails, erstellen des mails mit wortliste und in spam/ham Liste speichern
    public void importSpamMail() {

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader());
            String linie = br.readLine();
            List<Wort> woerterListe = new ArrayList();
            Email email = new Email(EmailType.SPAM,woerterListe);
            while (linie != null) {
                this.wortListeInEmailBefuellen(linie, email);
            }

            spamMails.add(email);

            br.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        System.out.println("");
    }



    //TODO lesen des mails, erstellen des mails mit wortliste und in spam/ham Liste speichern
    public void importHamMail() {

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader());
            String linie = br.readLine();
            List<Wort> woerterListe = new ArrayList();
            Email email = new Email(EmailType.HAM,woerterListe);
            while (linie != null) {
                this.wortListeInEmailBefuellen(linie, email);
            }

            spamMails.add(email);

            br.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        System.out.println("");
    }

    private void wortListeInEmailBefuellen(String linie, Email email) {

        String[] wortBezeichnungen = linie.split("");

        for (String wortInLinie : wortBezeichnungen) {

            for (Wort wort : email.getWoerter()) {
                if (!wort.getBezeichnug().equals(wortInLinie)) {
                    Wort neuesWort = new Wort(wortInLinie, 0, 0, 0.0, 0.0);
                    email.addWort(neuesWort);
                }

            }
        }

    }

    public void readMails() {

    }

    public void entpacker() {
    }

    public List<Email> getHamMails() {
        return hamMails;
    }

    public void setHamMails(List<Email> hamMails) {
        this.hamMails = hamMails;
    }

    public List<Email> getSpamMails() {
        return spamMails;
    }

    public void setSpamMails(List<Email> spamMails) {
        this.spamMails = spamMails;
    }

    public InputStream getInput() {
        return input;
    }

    public void setInput(InputStream input) {
        this.input = input;
    }
}
