
import java.util.Arrays;
import java.util.Scanner;

public class GuessLoop {
    
    public static String[] guess(Scanner scanner, String word){
        String[] validator = new String[word.length()];
        for(int i = 0; i < 5; i++){
            String guess = scanner.nextLine();
            
            if(guess.equals(word)){
                Arrays.fill(validator, "green");
                System.out.println(Arrays.toString(validator));
                return validator;
            }
            
            for(int j = 0; j < word.length(); j++){
                if(guess.charAt(j) == word.charAt(j)){
                    validator[j] = "green";
                } else if (inWord(word, guess, j)){
                    validator[j] = "yellow";
                } else{
                    validator[j] = "red";
                }
            }
        
            System.out.println(Arrays.toString(validator));
        }
        
        
        return validator;
    }
    
    public static boolean inWord(String word, String guess, int j){
        boolean isIn = false;
        
        for(int k = 0; k < word.length(); k++){
            if(guess.charAt(j) == word.charAt(k)){
                isIn = true;
                return isIn;
            } else {
                isIn = false;
            }
        }
        
        return isIn;
    }
}
