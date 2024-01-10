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
        String name = sc.nextLine();
        player1 = new Player(name);
        System.out.println("Please enter player 2's name.");
        name = sc.nextLine();
        player2 = new Player(name);
        System.out.println("Please enter player 3's name.");
        name = sc.nextLine();
        player3 = new Player(name);
        banker = new Banker();
        die1 = new Die();
        die2 = new Die();
        die3 = new Die();
        System.out.println("Welcome to the Ceelo game! Make the banker lose all his chips to win!");
    }
    public void play() {
        boolean win = false;
        while (player1.getChipsNum() > 0 || player2.getChipsNum() > 0 || player3.getChipsNum() > 0) {
            System.out.println(player1.getName() + ", please enter your wager: ");
        }
    }
    public void rollDices() {
        die1.roll();
        die2.roll();
        die3.roll();
    }
    public void printGameInfo() {
        System.out.println(player1.getName() + " currently has " + player1.getChipsNum() + "\n" + player2.getName() + " currently has " + player2.getChipsNum() + "\n" + player3.getName() + " currently has " + player3.getChipsNum() + "\n" + "The banker currently has " + banker.);
    }
}
