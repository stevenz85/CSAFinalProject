import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
public class Ceelo {
    Scanner sc;
    Banker banker;
    Player player1;
    Player player2;
    Player player3;
    Die die1;
    Die die2;
    Die die3;
    int[] dices;
    public Ceelo() {
        greetings();
        play();
    }
    public void greetings() {
        sc = new Scanner(System.in);
        System.out.println("Welcome to the ceelo game!");
        initializeAll();
        System.out.println("Welcome to the Ceelo game! Make the banker lose all his chips to win!");
    }
    public void play() {
        while (banker.getChipsNum() > 0 && player1.getChipsNum() > 0 || player2.getChipsNum() > 0 || player3.getChipsNum() > 0) {
            printGameInfo();
            setWagers();
            System.out.println("The banker is rolling the dices!");
            boolean win = determineRoundWinners();
            roundRewards(win);
        }
        determineGameWinners();
    }
    public void setWagers() {
        int num;
        System.out.println(player1.getName() + ", please enter your wager: ");
        num = sc.nextInt();
        player1.setWager(num);
        System.out.println(player2.getName() + ", please enter your wager: ");
        num = sc.nextInt();
        player2.setWager(num);
        System.out.println(player3.getName() + ", please enter your wager: ");
        num = sc.nextInt();
        player3.setWager(num);
    }
    public void roundRewards(boolean win) {
        if (win) {
            player1.winChips();
            player2.winChips();
            player3.winChips();
            banker.updateChips(-player1.getChipsNum() + -player2.getChipsNum() + -player3.getChipsNum());
        } else {
            player1.loseChips();
            player2.loseChips();
            player3.loseChips();
            banker.updateChips(player1.getChipsNum() + player2.getChipsNum() + player3.getChipsNum());
        }
    }
    public void determineGameWinners() {
        if (banker.getChipsNum() == 0) {
            System.out.println("The banker has gone broke! You all have won the game!");
            int[] scorers = {player1.getChipsNum(), player2.getChipsNum(), player3.getChipsNum()};
            int[] topScorers = new int[3];
            for (int i = 0; i < topScorers.length; i++) {
                topScorers[i] = scorers[i];
            }
            Arrays.sort(topScorers);
            Collections.reverse(Arrays.asList(topScorers));
        } else {
            System.out.println("The banker has won! " + player1.getName() + ", " + player2.getName() + ", and " + player3.getName() + " have all gone broke!");
        }
    }
    public boolean determineRoundWinners() {
        rollDices();
        System.out.println("The banker rolled a " + die1.getLastRolledNum() + ", " + die2.getLastRolledNum() + ", and " + die3.getLastRolledNum() + "!");
        if (dices[0] == dices[1] && dices[1] == dices[2]) {
            return false;
        } else if (dices[0] == 4 && dices[1] == 5 && dices[2] == 6) {
            return false;
        } else if (dices[0] == 1 && dices[1] == 2 && dices[2] == 3) {
            return true;
        } else if (dices[0] == dices[1]) {
            banker.setScore(dices[2]);
        } else {
            System.out.println("The banker has to roll again!");
            determineRoundWinners();
        }
        return true;
    }
    public void rollDices() {
        die1.roll();
        die2.roll();
        die3.roll();
        dices = new int[]{die1.getLastRolledNum(), die2.getLastRolledNum(), die3.getLastRolledNum()};
        Arrays.sort(dices);
    }
    public void printGameInfo() {
        System.out.println(player1.getName() + " currently has " + player1.getChipsNum() + " chips\n" + player2.getName() + " currently has " + player2.getChipsNum() + " chips\n" + player3.getName() + " currently has " + player3.getChipsNum() + "\n" + "The banker currently has " + banker.getChipsNum() + " chips");
    }
    public void initializeAll() {
        System.out.println("Please enter player 1's name: ");
        String name = sc.nextLine();
        player1 = new Player(name);
        System.out.println("Please enter player 2's name: ");
        name = sc.nextLine();
        player2 = new Player(name);
        System.out.println("Please enter player 3's name:");
        name = sc.nextLine();
        player3 = new Player(name);
        banker = new Banker();
        die1 = new Die();
        die2 = new Die();
        die3 = new Die();
    }
}