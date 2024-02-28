import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import java.io.File;

public class FileOperator {

    public static void lockFile(File file, String password) {
        try {
            PDDocument document = PDDocument.load(file);
            AccessPermission ap = new AccessPermission();
            StandardProtectionPolicy spp = new StandardProtectionPolicy(password, password, ap);
            spp.setEncryptionKeyLength(128);
            spp.setPermissions(ap);         // from documentation
            document.protect(spp);
            document.save(file);
            document.close();
            System.out.println("File locked successfully!");
        } catch (Exception e) {
            System.out.println("File seems to be already locked!");
        }
    }

    public static void unlockFile(File file, String password) {
        try {
            PDDocument document = PDDocument.load(file, password);
            if(document.isEncrypted()) {
                document.setAllSecurityToBeRemoved(true);       // remove security
                document.save(file);
                document.close();
                System.out.println("File unlocked successfully!");
            } else {
                System.out.println("File is not encrypted!");
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
    
    public static File loadFile(String path) {
        File pdfFile = new File(path);
        if (pdfFile.exists() && pdfFile.isFile() && path.endsWith(".pdf")) {
            System.out.println("File Found!");
            System.out.println("File name : " + pdfFile.getName());
            System.out.println("File path : " + pdfFile.getAbsolutePath());
            System.out.println("File size : " + pdfFile.length() + " bytes");

            try {
                PDDocument document = PDDocument.load(pdfFile);
                System.out.println("File is not encrypted!");
                return pdfFile;
            }catch (Exception e) {
                System.out.println("File is encrypted!");
                return pdfFile;
            }
        } else {
            System.out.println("File not found! Quitting...");
            return null;
        }
    }
}