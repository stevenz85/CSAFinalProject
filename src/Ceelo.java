import java.util.Scanner;
public class Ceelo {
    Scanner sc;
    Banker banker;
    Player player1;
    Player player2;
    Player player3;
    public Ceelo() {
        sc = new Scanner(System.in);
        greetings();
    }
    public void greetings() {
        System.out.println("Welcome to the ceelo game!");
        System.out.println("Please enter player 1's name.");
        player1.name = sc.nextLine();
        System.out.println("Please enter player 2's name.");
        player2.name = sc.nextLine();
        System.out.println("Please enter player 3's name.");
        player3.name = sc.nextLine();
    }
    public void play() {

    }
}
