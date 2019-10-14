package src.ch.brugg.fhwn.reader;

import src.ch.brugg.fhwn.dto.Email;
import src.ch.brugg.fhwn.dto.EmailType;
import src.ch.brugg.fhwn.dto.Wort;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static javax.script.ScriptEngine.FILENAME;

//TODO Faustina
//email generiert
//und woerter in email befüllt
public class Reader {

    private List<Email> hamMails = new ArrayList<>();
    private List<Email> spamMails = new ArrayList<>();

    private File[] spamFileList;
    private File[] hamFileList;

    private static final String PATHNAME_HAM_ZIP = "src/main/java/resources/ham-anlern.zip";
    private static final String PATHNAME_SPAM_ZIP = "src/main/java/resources/spam-anlern.zip";

    private static final String PATHNAME_HAM = "src/main/java/resources/entpacktefiles/ham";
    private static final String PATHNAME_SPAM = "src/main/java/resources/entpacktefiles/spam";

    private InputStream input;

    public void enzippen(File archive, File destDir) throws IOException {
        if (!destDir.exists()) {
            System.out.println(destDir+ ": Directory existiert noch nicht und wir angelegt");
            destDir.mkdir();
        }

        ZipFile zipFile = new ZipFile(archive);
        Enumeration entries = zipFile.entries();

        byte[] buffer = new byte[16384];
        int len;
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();

            String entryFileName = entry.getName();

            File dir = dir = buildDirectoryHierarchyFor(entryFileName, destDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            if (!entry.isDirectory()) {
                BufferedOutputStream bos = new BufferedOutputStream(
                        new FileOutputStream(new File(destDir, entryFileName)));

                BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));

                while ((len = bis.read(buffer)) > 0) {
                    bos.write(buffer, 0, len);
                }

                bos.flush();
                bos.close();
                bis.close();
            }
        }
        zipFile.close();
    }

    private File buildDirectoryHierarchyFor(String entryName, File destDir) {
        int lastIndex = entryName.lastIndexOf('/');
        String entryFileName = entryName.substring(lastIndex + 1);
        String internalPathToEntry = entryName.substring(0, lastIndex + 1);
        return new File(destDir, internalPathToEntry);
    }

    public File[] listDir(File dir) {

        File[] files = dir.listFiles();
        System.out.println("Anzahl entpackten Files: " +files.length);
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
               // System.out.print(files[i].getAbsolutePath());

            }
        }
        return files;
    }

    //TODO alle entippten Mails durchgehen und importieren
    public void mailsParsen() throws IOException {
        this.enzippen(new File(PATHNAME_HAM_ZIP), new File(PATHNAME_HAM));
        this.enzippen(new File(PATHNAME_SPAM_ZIP), new File(PATHNAME_SPAM));
        this.hamFileList = this.listDir(new File(PATHNAME_HAM));
        this.spamFileList = this.listDir(new File(PATHNAME_SPAM));

        System.out.println(this.hamFileList.length+ " Ham und "+this.spamFileList.length+": Spammails wurden eingelesen");
        this.importSpamMail();
        this.importHamMail();
        System.out.println("Anzahl Ham Mails"+this.hamMails.size());
        System.out.println("Anzahl Spam Mails"+this.spamMails.size());
    }

    //TODO lesen des mails, erstellen des mails mit wortliste und in spam/ham Liste speichern
    public void importSpamMail() {
        System.out.println("Spam Mails werden importiert");
        for (File spamFile : this.spamFileList) {
            BufferedReader br;
            try {
                System.out.println(spamFile.getAbsolutePath().toString()+ " File wird geprüft");
                br = new BufferedReader(new FileReader(spamFile));
                String linie = br.readLine();
                System.out.println(linie +" diese Linie wird geprüft");
                List<Wort> woerterListe = new ArrayList();
                Email email = new Email(EmailType.SPAM, woerterListe);
                while (linie != null) {
                    this.wortListeInEmailBefuellen(linie, email);
                    br.readLine();
                }

                spamMails.add(email);

                br.close();

            } catch (FileNotFoundException e) {

                System.out.println("Bei importSpamMail ist was schief gelaufen");
                e.printStackTrace();

            } catch (IOException e) {
                System.out.println("Bei importSpamMail ist was schief gelaufen");
                e.printStackTrace();
            }
            System.out.println("bei importSpamMail ist alles gut gelaufen");
        }

    }

    //TODO lesen des mails, erstellen des mails mit wortliste und in spam/ham Liste speichern
    public void importHamMail() {
        System.out.println("Ham Mails werden importiert");
        for (File hamFile : this.hamFileList) {

            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(hamFile.getAbsolutePath().substring(hamFile.getAbsolutePath().lastIndexOf("\\")+1)));
                String linie = br.readLine();
                List<Wort> woerterListe = new ArrayList();
                Email email = new Email(EmailType.HAM, woerterListe);
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
    }

    private void wortListeInEmailBefuellen(String linie, Email email) {
        System.out.println("wortListeInEmailBefuellen startet");
        String[] wortBezeichnungen = linie.split("\\s+");


        for (int i = 0; i < wortBezeichnungen.length; i++) {
            System.out.println(wortBezeichnungen[i]+ " wird geprüft");
            System.out.println(email.getWoerter().size() +"Anzahl woerter im mail");
            if (null == email.getWoerter()||email.getWoerter().size()==0){
                Wort neuesWort = new Wort(wortBezeichnungen[i], 0, 0, 0.0, 0.0);
                System.out.println("neues wort wurde gefunden");
email.addWort(neuesWort);
            }


            for (Wort wort : email.getWoerter()) {
                System.out.println(wort+ "wird geprüft");
                if (!wort.getBezeichnug().equals(wortBezeichnungen[i])) {
                    Wort neuesWort = new Wort(wortBezeichnungen[i], 0, 0, 0.0, 0.0);
                    System.out.println("neues wort wurde gefunden");
                    email.addWort(neuesWort);
                }

            }
        }

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
