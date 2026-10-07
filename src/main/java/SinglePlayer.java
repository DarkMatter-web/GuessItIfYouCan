
import java.util.Arrays;
import java.util.Scanner;


public class SinglePlayer {
    public static boolean play(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Difficulty 1 or 2");
        
        String word = WordLab.single(scanner);
        
        boolean[] result = GuessLoop.guess(scanner, word);
        
        if (Arrays.equals(result, new boolean[]{true, true, true, true, true})){
            return true;
        } else{
            return false;
        } 
    }
}
