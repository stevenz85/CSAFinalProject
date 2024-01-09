import java.util.Scanner;
public class Ceelo {
    Scanner sc;
    Banker banker;
    Player player1;
    Player player2;
    Player player3;
    Die die1;
    Die die2;
    Die die3;
    public Ceelo() {
        greetings();
        play();
    }
    public void greetings() {
        sc = new Scanner(System.in);
        System.out.println("Welcome to the ceelo game!");
        System.out.println("Please enter player 1's name.");
        player1.name = sc.nextLine();
        System.out.println("Please enter player 2's name.");
        player2.name = sc.nextLine();
        System.out.println("Please enter player 3's name.");
        player3.name = sc.nextLine();
        banker = new Banker();
        die1 = new Die();
        die2 = new Die();
        die3 = new Die();
    }
    public void play() {
        boolean win = false;
        while (banker.chipsNum > 0 && player1.chipsNum) {

        }
    }
    public void rollDices() {
        die1.roll();
        die2.roll();
        die3.roll();
    }
}
