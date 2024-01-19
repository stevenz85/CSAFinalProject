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
    public Ceelo() {}
    public void greetings() {
        sc = new Scanner(System.in);
        System.out.println("Welcome to the ceelo game!");
        initializeAll();
        System.out.println("Welcome to the Ceelo game! Make the banker lose all his chips to win!");
    }
    public void play() {
        greetings();

        while (playersHaveNotLostOrWin()) {
            ceeloGame();
        }
        determineGameWinners();
    }
    public boolean playersHaveNotLostOrWin() {
        return banker.getChipsNum() > 0 && player1.getChipsNum() > 0 || player2.getChipsNum() > 0 || player3.getChipsNum() > 0;
    }
    public void ceeloGame() {
        printGameInfo();
        setWagers();
        System.out.println("The banker is rolling the dices!");
        String winner = determineRoundWinners();
        if (winner.equals("player")) {
            for (Player name : playerList) {
                giveRoundRewards(name, true);
            }
        } else if (winner.equals("banker")) {
            for (Player name : playerList) {
                giveRoundRewards(name, true);
            }
        } else {
            scoreRound();
        }
    }
    public void setWagers() {
        int num = 0;
        for (Player name : playerList) {
            if (name.getChipsNum() > 0) {
                while (num == 0) {
                    System.out.println(name.getName() + ", please enter your wager: ");
                    num = sc.nextInt();
                    if (num <= name.getChipsNum()) {
                        name.setWager(num);
                    } else {
                        System.out.println("Please type in a valid wager!\nYou currently have " + player1.getChipsNum() + " chips!");
                    }
                }
            }
            num = 0;
        }
    }
    public void giveRoundRewards(Player player, boolean win) {
        if (win) {
            player.winChips();
            System.out.println(player.getName() + " has won " + player.getWager() + " chips!");
            banker.updateChips(-player.getWager());
            System.out.println(" The banker has has lost " + player1.getWager() + player2.getWager() + player3.getWager() + " chips!");
            if (player.getChipsNum() <= 0) {
                System.out.println(player.getName() + " ran out of chips! They are now out of the game!");
            }
        } else {
            player.loseChips();
            System.out.println(player.getName() + " has lost " + player.getWager() + " chips!");
            banker.updateChips(player.getWager());
            System.out.println(" The banker has has won " + player1.getWager() + player2.getWager() + player3.getWager() + " chips!");
        }
    }
    public void determineGameWinners() {
        determineTopScorer();
        if (banker.getChipsNum() == 0) {
            System.out.println("The banker has gone broke! You all have won the game!");
        } else {
            System.out.println("The banker has won! " + player1.getName() + ", " + player2.getName() + ", and " + player3.getName() + " have all gone broke!");
        }
        System.out.println("The top contributors in order are " + playerList[0].getName() + " with " + playerList[0].getChipsNum() + " chips!\n" + playerList[1].getName() + " with " + playerList[1].getChipsNum() + " chips!\n" + playerList[2].getName() + " with " + playerList[2].getChipsNum() + " chips!");
    }
    public void determineTopScorer() {
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
    }
    public String determineRoundWinners() {
        boolean end = false;
        while (!end) {
            rollDices();
            System.out.println("The banker rolled a " + die1.getLastRolledNum() + ", " + die2.getLastRolledNum() + ", and " + die3.getLastRolledNum() + "!");
            if (dices[0] == dices[1] && dices[1] == dices[2]) {
                System.out.println("The banker has rolled a double!\nThe banker wins the round!");
                end = true;
                return "banker";
            } else if (dices[0] == 4 && dices[1] == 5 && dices[2] == 6) {
                System.out.println("The banker has rolled the top three numbers!\nThe banker wins the round!");
                end = true;
                return "banker";
            } else if (dices[0] == 1 && dices[1] == 2 && dices[2] == 3) {
                System.out.println("The banker has rolled the bottom three numbers!\nThe banker loses the round!");
                end = true;
                return "player";
            } else if (checkForDouble()) {
                System.out.println("The banker has rolled a double!\nHis score is " + dices[2] + ", try to get a higher score than him!");
                banker.setScore(dices[2]);
                end = true;
                return "doubles";
            }
            System.out.println("The banker rolled no combos!\nThe banker has to roll again!");
        }
        return null;
    }
    public void determineIndividualWinner(Player player) {
        for (int i = 0; i < 1; i --) {
            rollDices();
            System.out.println(player.getName() + "has rolled a " + die1.getLastRolledNum() + ", " + die2.getLastRolledNum() + ", and " + die3.getLastRolledNum() + "!");
            if (dices[0] == dices[1] && dices[1] == dices[2]) {
                System.out.println(player.getName() + " has rolled a double!\n" + player.getName() + " wins the round!");
                giveRoundRewards(player, true);
            } else if (dices[0] == 4 && dices[1] == 5 && dices[2] == 6) {
                System.out.println(player.getName() + " has rolled the top three numbers!\n" + player.getName() + " wins the round!");
                giveRoundRewards(player, true);
            } else if (dices[0] == 1 && dices[1] == 2 && dices[2] == 3) {
                System.out.println(player.getName() + " has rolled the bottom three numbers!\n" + player.getName() + " loses the round!");
                giveRoundRewards(player, false);
            } else if (checkForDouble()) {
                if (dices[2] > banker.getScore()) {
                    System.out.println(player.getName() + " has rolled a double!\n" + player.getName() + " scored " + dices[2] + "!\n" + player.getName() + " wins the round!");
                    giveRoundRewards(player, true);
                } else {
                    System.out.println(player.getName() + " has rolled a double!\n" + player.getName() + " scored " + dices[2] + "!\n" + player.getName() + " loses the round!");
                    giveRoundRewards(player, false);
                }
            }
            System.out.println(player.getName() + " rolled no combos!\n" + player.getName() + " to roll again!");
        }
    }
    public boolean checkForDouble() {
        if (dices[0] == dices[1]) {
            return true;
        }
        if (dices[1] == dices[2]) {
            int sub = dices[2];
            dices[2] = dices[0];
            dices[0] = sub;
            return true;
        }
        if (dices[0] == dices[2]) {
            int sub = dices[1];
            dices[1] = dices[2];
            dices[2] = sub;
            return true;
        }
        return false;
    }
    public void scoreRound() {
        for (Player name : playerList) {
            if (name.getChipsNum() > 0) {
                System.out.println("It's " + name.getName() + "'s turn!\nWould you like to roll the dices, go to main menu, or check your chip balance? (r/m/c)");
                String str = sc.nextLine();
                if (str.equals("m")) {
                    mainMenu();
                }
                while (str.equals("c") || str.equals("m")) {
                    System.out.println(name.getName() + ", you currently have " + name.getChipsNum() + " chips!\nWould you like to roll the dices or check your chip balance again? (r/c)");
                    str = sc.nextLine();
                }
                determineIndividualWinner(name);
            }
        }
    }
    public void mainMenu() {
        System.out.println("MAIN MENU\n(q)quit\n(n)new game\n(c)check top scorers");
        String str = sc.nextLine();
        if (str.equals("q")) {
            System.out.println("Are you sure? This action will terminate the game! (y/n)");
            str = sc.nextLine();
            if (str.equals("y")) {
                System.exit(0);
            }
        }
        if (str.equals("n")) {
            Ceelo game = new Ceelo();
            System.exit(0);
        }
        if (str.equals("c")) {
            determineTopScorer();
            System.out.println("The top contributors in order are " + playerList[0].getName() + " with " + playerList[0].getChipsNum() + " chips!\n" + playerList[1].getName() + " with " + playerList[1].getChipsNum() + " chips!\n" + playerList[2].getName() + " with " + playerList[2].getChipsNum() + " chips!");
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
        System.out.println(player1.getName() + " currently has " + player1.getChipsNum() + " chips\n" + player2.getName() + " currently has " + player2.getChipsNum() + " chips\n" + player3.getName() + " currently has " + player3.getChipsNum() + " chips\n" + "The banker currently has " + banker.getChipsNum() + " chips");
    }
    public void initializeAll() {
        System.out.println("Please enter player 1's name: ");
        String name = sc.nextLine();
        player1 = new Player(name);
        System.out.println("Please enter player 2's name: ");
        name = sc.nextLine();
        player2 = new Player(name);
        System.out.println("Please enter player 3's name: ");
        name = sc.nextLine();
        player3 = new Player(name);
        banker = new Banker();
        die1 = new Die();
        die2 = new Die();
        die3 = new Die();
        playerList = new Player[]{player1, player2, player3};
    }
}