package src.ch.brugg.fhwn.dto;

import java.util.ArrayList;
import java.util.List;

public class Email {
    private EmailType type;
    private List<Wort> woerter = new ArrayList<>();
    private double spamWahrscheinlichkeit;

    public double getSpamWahrscheinlichkeit() {
        return spamWahrscheinlichkeit;
    }

    public void setSpamWahrscheinlichkeit(double spamWahrscheinlichkeit) {
        this.spamWahrscheinlichkeit = spamWahrscheinlichkeit;
    }

    public Email(EmailType type, List<Wort> woerter) {
        this.type = type;
        this.woerter = woerter;
    }

    public EmailType getType() {
        return type;
    }

    public void setType(EmailType type) {
        this.type = type;
    }

    public List<Wort> getWoerter() {
        return woerter;
    }

    public void setWoerter(List<Wort> woerter) {
        this.woerter = woerter;
    }

    public void addWort(Wort neuesWort) {
        this.woerter.add(neuesWort);
    }
}
