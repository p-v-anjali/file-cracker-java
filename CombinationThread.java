

import  java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;

public class CombinationThread extends Thread{


    int length;
    File file;
    String characterSet;

    CombinationThread(File file, int i, String characterSet) {
        super();
        this.file = file;
        this.characterSet = characterSet;
        this.length = i;
    }

    public void generateWordsOfSize(File file, int size, String word, String alphabet) {
        if (size == 0) {
            try {
                System.out.print("\033[H\033[2J");
                System.out.println("Trying : " + word);
                PDDocument document = PDDocument.load(file, word);
                if (document.isEncrypted()) {
                    document.setAllSecurityToBeRemoved(true);
                    document.save(file);
                    document.close();
                    System.out.println("File unlocked successfully!");
                    System.out.println("Password : " + word);
                    System.exit(0);
                }
            }catch(Exception e) {
                
            }
        } else {
            for (int i = 0; i < alphabet.length(); i++) {
                generateWordsOfSize(file, size - 1, word + alphabet.charAt(i), alphabet);
            }
        }
    }

    public void run() {
        this.generateWordsOfSize(this.file, this.length, "", this.characterSet);
    }
}
