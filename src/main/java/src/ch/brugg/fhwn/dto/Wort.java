package src.ch.brugg.fhwn.dto;

public class Wort {
   private String bezeichnug;
    private int anzahlHamMails;
    private int anzahlSpamMails;
    private double spamWahrscheinlichekeit;
    private double hamWahrscheinlichkeit;

    public Wort(String bezeichnug, int anzahlHamMails, int anzahlSpamMails, double spamWahrscheinlichekeit,
            double hamWahrscheinlichkeit) {
        this.bezeichnug = bezeichnug;
        this.anzahlHamMails = anzahlHamMails;
        this.anzahlSpamMails = anzahlSpamMails;
        this.spamWahrscheinlichekeit = spamWahrscheinlichekeit;
        this.hamWahrscheinlichkeit = hamWahrscheinlichkeit;
    }

    public String getBezeichnug() {
        return bezeichnug;
    }

    public void setBezeichnug(String bezeichnug) {
        this.bezeichnug = bezeichnug;
    }

    public int getAnzahlHamMails() {
        return anzahlHamMails;
    }

    public void setAnzahlHamMails(int anzahlHamMails) {
        this.anzahlHamMails = anzahlHamMails;
    }

    public int getAnzahlSpamMails() {
        return anzahlSpamMails;
    }

    public void setAnzahlSpamMails(int anzahlSpamMails) {
        this.anzahlSpamMails = anzahlSpamMails;
    }

    public double getSpamWahrscheinlichekeit() {
        return spamWahrscheinlichekeit;
    }

    public void setSpamWahrscheinlichekeit(double spamWahrscheinlichekeit) {
        this.spamWahrscheinlichekeit = spamWahrscheinlichekeit;
    }

    public double getHamWahrscheinlichkeit() {
        return hamWahrscheinlichkeit;
    }

    public void setHamWahrscheinlichkeit(double hamWahrscheinlichkeit) {
        this.hamWahrscheinlichkeit = hamWahrscheinlichkeit;
    }
}
