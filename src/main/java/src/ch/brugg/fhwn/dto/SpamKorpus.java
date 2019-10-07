package main.java.src.ch.brugg.fhwn.dto;

import java.util.ArrayList;
import java.util.List;

public class SpamKorpus {

    private List<Email> spamMails= new ArrayList<>();

    public List<Email> getSpamMails() {
        return spamMails;
    }

    public void setSpamMails(List<Email> spamMails) {
        this.spamMails = spamMails;
    }
}
