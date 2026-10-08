
import java.util.Scanner;

public class Start {
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean win;
        
        System.out.println("Choose game mode, 1 or 2");
        
        int mode = scanner.nextInt();
        
        if(mode == 1){
            win = SinglePlayer.play();
        } else {
            win = MultiPlayer.play();
        }
        
        if (win){
            System.out.println("Game won");
        } else{
            System.out.println("Game lost");
        }
    }
    
}
