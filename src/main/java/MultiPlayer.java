
import java.util.Arrays;
import java.util.Scanner;


public class MultiPlayer {
    public static boolean play(){        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player 1 - Write a 5 letter word");
        
        String word = WordLab.multi(scanner);
        
        System.out.println("Player 2 - Make a guess");
        
        String[] result = GuessLoop.guess(scanner, word);
        
        if (Arrays.equals(result, new String[]{"green", "green", "green", "green", "green"})){
            return true;
        } else{
            return false;
        }
    }
}
