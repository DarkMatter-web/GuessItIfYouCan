
import java.util.ArrayList;
import java.util.Scanner;


public class WordLab {
    
    public static String multi(Scanner scanner){

        String word = scanner.nextLine();
        
        if (word.length() != 5){
            System.out.println("Try again, 5 letter word");
            return multi(scanner);
        }
        
        return word;
    }
    
    public static String single(Scanner scanner){
        String word;
        int mode = scanner.nextInt();
        scanner.nextLine();
        int randomIndex;
        
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
        
        switch(mode){
            case 1:
                randomIndex = random.nextInt(easyWords.size());
                word = easyWords.get(randomIndex);
                break;
            case 2:
                randomIndex = random.nextInt(hardWords.size());
                word = hardWords.get(randomIndex);
                break;
            default:
                System.out.println("Wrong game mode, choose mode 1 or 2");
                word = single(scanner);
                break;
        }
        
        System.out.println(word);
        
        return word;
    }
}
