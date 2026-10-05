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

