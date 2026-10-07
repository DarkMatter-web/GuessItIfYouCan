
import java.util.Arrays;
import java.util.Scanner;

public class GuessLoop {
    
    public static boolean[] guess(Scanner scanner, String word){
        boolean[] validator = new boolean[word.length()];
        for(int i = 0; i < 5; i++){
            String guess = scanner.nextLine();
            
            if(guess.equals(word)){
                Arrays.fill(validator, true);
                System.out.println(Arrays.toString(validator));
                return validator;
            }
            
            for(int j = 0; j < word.length(); j++){
                if(guess.charAt(j) == word.charAt(j)){
                    validator[j] = true;
                } else{
                    validator[j] = false;
                }
            }
        
            System.out.println(Arrays.toString(validator));
        }
        
        
        return validator;
    }    
}
