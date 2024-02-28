import java.io.File;
import java.util.Scanner;
/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to PDF file : ");
        String path = scanner.nextLine();

        File file = FileOperator.loadFile(path.trim());
        if (file == null) {
           return;
        }

        System.out.println("[1] Lock PDF file");
        System.out.println("[2] Unlock PDF file");
        System.out.println("[3] Combination Unlock PDF file");
        System.out.println("[4] Wordlist Unlock PDF file");

        
        System.out.print("Enter choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter password : ");
            String password = scanner.nextLine();
            FileOperator.lockFile(file, password);
        } else if (choice == 2) {
            System.out.print("Enter password to unlock : ");
            String password = scanner.nextLine();
            FileOperator.unlockFile(file, password);
        } else if (choice == 3) {
            System.out.print("Enter start length : ");
            int startLength = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter end length : ");
            int endLength = scanner.nextInt();
            scanner.nextLine();

            CombinationUnlocker cu = new CombinationUnlocker(file, startLength, endLength);
            cu.unlock();
            
        } else if (choice == 4) {
            System.out.print("Enter wordlist file path : ");
            String wordlistPath = scanner.nextLine();
            File wordlistFile =  new File(wordlistPath);
            if (wordlistFile.exists() && !wordlistFile.isDirectory() && wordlistPath.endsWith(".txt")) {
                CombinationUnlocker cu = new CombinationUnlocker(file, wordlistFile);
                cu.unlock();
            }
        }
        
    }
}
