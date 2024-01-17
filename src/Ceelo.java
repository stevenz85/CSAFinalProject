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
    Player[] playerList;
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
            String winner = determineRoundWinners();
            if (winner.equals("player") || winner.equals("banker")) {
                roundRewards(determineRoundWinners());
            } else {
                for (Player name : playerList) {
                    if (name.getChipsNum() > 0) {
                        System.out.println("It's " + name.getName() + "'s turn!\nWould you like to roll the dices or check your chip balance? (r/c)");
                        String str = sc.nextLine();
                        while (str.equals("c")) {
                            System.out.println(name.getName() + ", you currently have " + name.getChipsNum() + " chips!\nWould you like to roll the dices or check your chip balance again? (r/c)");
                            str = sc.nextLine();
                        }
                        determineIndividualWinner(name);
                    }

                }
            }
        }
        determineGameWinners();
    }
    public void setWagers() {
        for (Player name : playerList) {
            int num = 0;
            if (name.getChipsNum() > 0) {
                while (num == 0) {
                    System.out.println(player1.getName() + ", please enter your wager: ");
                    num = sc.nextInt();
                    if (num < player1.getChipsNum()) {
                        player1.setWager(num);
                    } else {
                        System.out.println("Please type in a valid wager!\nYou currently have " + player1.getChipsNum() + " chips!");
                    }
                }
            }
        }
    }
    public void roundRewards(String winner) {
        if (winner.equals("player")) {
            player1.winChips();
            player2.winChips();
            player3.winChips();
            banker.updateChips(-player1.getWager() + -player2.getWager() + -player3.getWager());
        } else if (winner.equals("banker")) {
            player1.loseChips();
            player2.loseChips();
            player3.loseChips();
            banker.updateChips(player1.getWager() + player2.getWager() + player3.getWager());
        }
    }
    public void giveRewards(Player player, boolean win) {
        if (win) {
            player.winChips();
            banker.updateChips(-player.getWager());
            if (player.getChipsNum() <= 0) {
                System.out.println(player.getName() + " ran out of chips! They are now out of the game!");
            }
        } else {
            player.loseChips();
            banker.updateChips(player.getWager());
        }
    }
    public void determineGameWinners() {
        int[] topScorers = new int[]{player1.getChipsNum(), player2.getChipsNum(), player3.getChipsNum()};
        Arrays.sort(topScorers);
        Collections.reverse(Arrays.asList(topScorers));
        for (int i = 0; i < topScorers.length; i ++) {
            for (int k = 0; k < playerList.length; k ++) {
                if (topScorers[i] == playerList[k].getChipsNum()) {
                    Player sub = playerList[k];
                    playerList[i] = playerList [k];
                    playerList[k] = sub;
                }
            }
        }
        if (banker.getChipsNum() == 0) {
            System.out.println("The banker has gone broke! You all have won the game!");
        } else {
            System.out.println("The banker has won! " + player1.getName() + ", " + player2.getName() + ", and " + player3.getName() + " have all gone broke!");
        }
        System.out.println("The top contributors in order are " + playerList[0].getName() + " with " + playerList[0].getChipsNum() + " chips!\n" + playerList[1].getName() + " with " + playerList[1].getChipsNum() + " chips!\n" + playerList[2].getName() + " with " + playerList[2].getChipsNum() + " chips!");
    }
    public String determineRoundWinners() {
        for (int i = 0; i < 1; i --) {
            rollDices();
            System.out.println("The banker rolled a " + die1.getLastRolledNum() + ", " + die2.getLastRolledNum() + ", and " + die3.getLastRolledNum() + "!");
            if (dices[0] == dices[1] && dices[1] == dices[2]) {
                return "banker";
            } else if (dices[0] == 4 && dices[1] == 5 && dices[2] == 6) {
                return "banker";
            } else if (dices[0] == 1 && dices[1] == 2 && dices[2] == 3) {
                return "player";
            } else if (dices[0] == dices[1]) {
                banker.setScore(dices[2]);
                return "doubles";
            }
            System.out.println("The banker has to roll again!");
        }
        return null;
    }
    public void determineIndividualWinner(Player player) {
        for (int i = 0; i < 1; i --) {
            rollDices();
            System.out.println(player.getName() + "has rolled a " + die1.getLastRolledNum() + ", " + die2.getLastRolledNum() + ", and " + die3.getLastRolledNum() + "!");
            if (dices[0] == dices[1] && dices[1] == dices[2]) {
                giveRewards(player, true);
            } else if (dices[0] == 4 && dices[1] == 5 && dices[2] == 6) {
                giveRewards(player, true);
            } else if (dices[0] == 1 && dices[1] == 2 && dices[2] == 3) {
                giveRewards(player, false);
            } else if (dices[0] == dices[1]) {
                if (dices[2] > banker.getScore()) {
                    giveRewards(player, true);
                } else {
                    giveRewards(player, false);
                }
            }
            System.out.println(player.getName() + " has to roll again!");
        }
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
        playerList = new Player[]{player1, player2, player3};
    }
}