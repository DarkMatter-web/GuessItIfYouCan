
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;


public class WordLab {
    
    public static String multi(Scanner scanner){

        String word = scanner.nextLine();
        
        if (word.length() != 5){
            System.out.println("Try again, 5 letter word");
            return multi(scanner);
        }
        
        return word;
    }
    /*
    public static String single(int mode) {

        ArrayList<String> easyWords = new ArrayList<>();

        easyWords.add("RAISE");
        easyWords.add("SLATE");
        easyWords.add("AROSE");
        easyWords.add("IRATE");

        ArrayList<String> hardWords = new ArrayList<>();

        hardWords.add("PARER");
        hardWords.add("MYRRH");
        hardWords.add("JAZZY");
        hardWords.add("SWELL");
        hardWords.add("PROXY");

        java.util.Random random = new java.util.Random();

        switch (mode) {
            case 1:
                return easyWords.get(random.nextInt(easyWords.size()));
    
            case 2:
                return hardWords.get(random.nextInt(hardWords.size()));
    
            default:
                throw new IllegalArgumentException("Invalid difficulty");
        }
    }*/
    
    
    public static String single(int difficulty) {

        ArrayList<String> words = new ArrayList<>();
        
        String fileName;
        
        if (difficulty == 1) {
            fileName = "src/main/java/easyWords_300.txt";
        } else {
            fileName = "src/main/java/hardWords_300.txt";
        }
        
        try {
            Scanner fileScanner = new Scanner(new File(fileName));

            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine();
                words.add(word);
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Word file not found.");
            return "";
        }

         Random random = new Random();

        int randomIndex = random.nextInt(words.size());

        return words.get(randomIndex);
    }
}
