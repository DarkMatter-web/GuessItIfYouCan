import java.util.Scanner;
import java.util.Arrays;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author elash
 */
import java.util.ArrayList;


public class GuessItIfYouCan { // Main Class

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean win;
        
        System.out.println("Choose game mode, 1 or 2");
        
        int mode = scanner.nextInt();
        
        if(mode == 1){
            win = singlePlayer();
        } else {
            win = multiPlayer();
        }
        
        if (win){
            System.out.println("Game won");
        } else{
            System.out.println("Game lost");
        }
    }
    
    public static boolean singlePlayer(){
      return true;  
    }
    
    public static boolean multiPlayer(){        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player 1 - Write a 5 letter word");
        
        String word = theWord(scanner);
        
        System.out.println("Player 2 - Make a guess");
        
        boolean[] result = check(scanner, word);
        
        if (Arrays.equals(result, new boolean[]{true, true, true, true, true})){
            return true;
        } else{
            return false;
        }
    }
    
    public static String theWord(Scanner scanner){

        String word = scanner.nextLine();
        
        if (word.length() != 5){
            System.out.println("Try again, 5 letter word");
            return theWord(scanner);
        }
        
        return word;
    }
    
    public static boolean[] check(Scanner scanner, String word){
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
                }
            }
        
            System.out.println(Arrays.toString(validator));
        }
        
        
        return validator;

        boolean isSinglePlayer = true;

        GuessItIfYouCan single = new GuessItIfYouCan();
        //GuessItIfYouCan multi = new GuessItIfYouCan();

        if (isSinglePlayer) {
            single.SinglePlayer();
        }/* else if (playerNumber = false) {
            multi.MultiPlayer();
        }*/

    }

    public void SinglePlayer() {

        boolean isEasy = true;

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
        
        String word = "";

        if (isEasy) {
            int randomIndex = random.nextInt(easyWords.size());
            word = easyWords.get(randomIndex);
        } else {
            int randomIndex = random.nextInt(hardWords.size());
            word = hardWords.get(randomIndex);
        }

    }

}

