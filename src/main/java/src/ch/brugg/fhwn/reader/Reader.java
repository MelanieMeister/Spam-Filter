package main.java.src.ch.brugg.fhwn.reader;

import main.java.src.ch.brugg.fhwn.dto.Email;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


//TODO Faustina
public class Reader {

    List<Email> hamMails = new ArrayList<>();
    List<Email> spamMails = new ArrayList<>();


    private static final String PATHNAME_HAM = "./resources/";
    private static final String PATHNAME_SPAM = "./resources/";

   private InputStream input;

    public void readMails() {

    }

    public void entpacker(){}
}
