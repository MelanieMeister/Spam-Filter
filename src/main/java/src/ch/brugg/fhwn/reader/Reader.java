package main.java.src.ch.brugg.fhwn.reader;

import main.java.src.ch.brugg.fhwn.dto.Email;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
