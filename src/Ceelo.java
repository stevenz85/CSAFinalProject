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
        System.out.println("Welcome to the Ceelo game!");
        try {
            Thread.sleep(1000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }
        initializeAll();
        System.out.println("The goal of the Ceelo game is to make the banker lose all his chips to win! Best of luck!");
        try {
            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }
    }
    public void play() {
        greetings();
        ConsoleUtility.clearScreen();
        while (playersHaveNotLostOrWin()) {// The game would continue looping until there is a winner
            ceeloGame();
            ConsoleUtility.clearScreen();
        }
        determineGameWinners();
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
    public void ceeloGame() {
        printGameInfo();
        setWagers();
        System.out.println("The banker is rolling the dices!");
        try {
            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }
        String winner = determineRoundWinners();
        if (winner.equals("player")) {
            for (Player name : playerList) {
                giveRoundRewards(name, true);
            }
        } else if (winner.equals("banker")) {
            for (Player name : playerList) {
                giveRoundRewards(name, false);
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
                    int wager = sc.nextInt();
                    if (wager <= name.getChipsNum()) {
                        name.setWager(wager);
                        num ++;
                    } else {
                        System.out.println("Please type in a valid wager!\nYou currently have " + player1.getChipsNum() + " chips!");
                        try {
                            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
                        } catch (Exception e) {
                            System.out.println("error");
                        }
                    }
                }
            }
            num = 0;
        }
    }
    public void giveRoundRewards(Player player, boolean win) {
        if (win) {
            player.winChips();
            System.out.println(player.getName() + " has won " + ConsoleUtility.BLUE + player.getWager() + ConsoleUtility.RESET + " chips!");
            banker.updateChips(-player.getWager());
            System.out.println("The banker has has lost " + player.getWager() + " chips!");
            try {
                Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
            } catch (Exception e) {
                System.out.println("error");
            }
            if (player.getChipsNum() <= 0) {
                System.out.println(player.getName() + " ran out of chips! They are now out of the game!");
                try {
                    Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
                } catch (Exception e) {
                    System.out.println("error");
                }
            }
        } else {
            player.loseChips();
            System.out.println(player.getName() + " has lost " + ConsoleUtility.RED + player.getWager() + ConsoleUtility.RESET + " chips!");
            banker.updateChips(player.getWager());
            System.out.println("The banker has has won " + player.getWager() + " chips!");
            try {
                Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
            } catch (Exception e) {
                System.out.println("error");
            }
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
    //The next method orders the playerList array in such that the top contributor is first and least is last
    public void determineTopScorer() {
        int[] topScorers = new int[]{player1.getChipsNum(), player2.getChipsNum(), player3.getChipsNum()};
        Arrays.sort(topScorers);
        Collections.reverse(Arrays.asList(topScorers));
        for (int i = 0; i < topScorers.length; i ++) {
            for (int k = 0; k < playerList.length; k ++) {
                if (topScorers[i] == playerList[k].getChipsNum()) {
                    Player sub = playerList[i];
                    playerList[i] = playerList[k];
                    playerList[k] = sub;
                }
            }
        }
        Collections.reverse(Arrays.asList(playerList));
    }
    public void determineIndividualWinner(Player player) {
        for (int i = 0; i < 1; i --) {
            rollDices();
            System.out.println(player.getName() + " has rolled a " + die1.getLastRolledNum() + ", " + die2.getLastRolledNum() + ", and " + die3.getLastRolledNum() + "!");
            if (dices[0] == dices[1] && dices[1] == dices[2]) {
                System.out.println(player.getName() + " has rolled a double!\n" + player.getName() + " wins the round!");
                try {
                    Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
                } catch (Exception e) {
                    System.out.println("error");
                }
                giveRoundRewards(player, true);
                break;
            } else if (dices[0] == 4 && dices[1] == 5 && dices[2] == 6) {
                System.out.println(player.getName() + " has rolled the top three numbers!\n" + player.getName() + " wins the round!");
                try {
                    Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
                } catch (Exception e) {
                    System.out.println("error");
                }
                giveRoundRewards(player, true);
                break;
            } else if (dices[0] == 1 && dices[1] == 2 && dices[2] == 3) {
                System.out.println(player.getName() + " has rolled the bottom three numbers!\n" + player.getName() + " loses the round!");
                try {
                    Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
                } catch (Exception e) {
                    System.out.println("error");
                }
                giveRoundRewards(player, false);
                break;
            } else if (checkForDouble()) {
                player.setScore(dices[2]);
                if (player.getScore() > banker.getScore()) {
                    System.out.println(player.getName() + " has rolled a double/triple!\n" + player.getName() + " scored " + dices[2] + "!\n" + player.getName() + " wins the round!");
                    try {
                        Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
                    } catch (Exception e) {
                        System.out.println("error");
                    }
                    giveRoundRewards(player, true);
                    break;
                } else {
                    System.out.println(player.getName() + " has rolled a double/triple!\n" + player.getName() + " scored " + dices[2] + "!\n" + player.getName() + " loses the round!");
                    try {
                        Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
                    } catch (Exception e) {
                        System.out.println("error");
                    }
                    giveRoundRewards(player, false);
                    break;
                }
            }
            System.out.println(player.getName() + " rolled no combos!\n" + player.getName() + " to roll again!");
            try {
                Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
            } catch (Exception e) {
                System.out.println("error");
            }
        }
    }
    public void scoreRound() {
        int num = 0;
        for (Player name : playerList) {
            if (name.getChipsNum() > 0) {
                while (num == 0) {
                    System.out.println("It's " + name.getName() + "'s turn!\nWould you like to roll the dices, go to main menu, or check your chip balance? (r/m/c)");
                    String str = sc.nextLine();
                    if (!str.equals(null)) {
                        str = sc.nextLine();
                    }
                    if (str.equals("m")) {
                        mainMenu();
                    } else if (str.equals("c")) {
                        System.out.println(name.getName() + ", you currently have " + ConsoleUtility.YELLOW + name.getChipsNum() + ConsoleUtility.RESET + " chips!");
                    } else if (str.equals("r")) {
                        num ++;
                        determineIndividualWinner(name);
                    } else {
                        System.out.println("Please enter a valid input!");
                    }
                }
            }
        }
    }
    public void mainMenu() {
        ConsoleUtility.clearScreen();
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
            ConsoleUtility.clearScreen();
            game.play();
            System.exit(0);
        }
        if (str.equals("c")) {
            determineTopScorer();
            topContributors();
        }
    }
    //The next method prints out the top contributors and whether or not there are ties in the rankings
    public void topContributors() {
        ConsoleUtility.clearScreen();
        if (playerList[0] != playerList[1] && playerList[1] != playerList[2]) {
            System.out.println("The top contributors in order are\n" + ConsoleUtility.YELLOW + playerList[0].getName() + ConsoleUtility.RESET + " with " + playerList[0].getChipsNum() + " chips!\n" + playerList[1].getName() + " with " + playerList[1].getChipsNum() + " chips!\n" + playerList[2].getName() + " with " + playerList[2].getChipsNum() + " chips!");
        } else if (playerList[0] == playerList[1] && playerList[1] == playerList[2]) {
            System.out.println(ConsoleUtility.YELLOW + playerList[0].getName() + ConsoleUtility.RESET + ", " + ConsoleUtility.YELLOW + playerList[1].getName() + ConsoleUtility.RESET + ", and " + ConsoleUtility.YELLOW + playerList[2].getName() + ConsoleUtility.RESET + " are all tied for first with " + playerList[0].getChipsNum() + " chips!");
        } else if (playerList[0] == playerList[1]) {
            System.out.println(ConsoleUtility.YELLOW + playerList[0].getName() + ConsoleUtility.RESET + " and " + ConsoleUtility.YELLOW + playerList[1].getName() + ConsoleUtility.RESET + " are tied for first with " + playerList[0].getChipsNum() + " chips!\n" + playerList[2].getName() + " is trailing behind with " + playerList[2].getChipsNum() + "chips!");
        } else {
            System.out.println(ConsoleUtility.YELLOW + playerList[0].getName() + ConsoleUtility.RESET + " is in first place with " + playerList[0].getChipsNum() + " chips!\n" + playerList[1].getName() + " and " + playerList[2].getName() + " are trailing behind with " + playerList[1].getChipsNum());
        }
        try {
            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }
    }
    public void rollDices() {
        ConsoleUtility.clearScreen();
        die1.roll();
        die2.roll();
        die3.roll();
        dices = new int[]{die1.getLastRolledNum(), die2.getLastRolledNum(), die3.getLastRolledNum()};
        Arrays.sort(dices);
    }
    public void printGameInfo() {
        System.out.println(player1.getName() + " currently has " + ConsoleUtility.YELLOW + player1.getChipsNum() + ConsoleUtility.RESET + " chips\n" + player2.getName() + " currently has " + ConsoleUtility.YELLOW + player2.getChipsNum() + ConsoleUtility.RESET + " chips\n" + player3.getName() + " currently has " + ConsoleUtility.YELLOW + player3.getChipsNum() + ConsoleUtility.RESET + " chips\n" + "The banker currently has " + ConsoleUtility.RED + banker.getChipsNum() + ConsoleUtility.RESET + " chips");
        try {
            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }
    }
    //The next method returns who wins the current round or it returns "doubles" which signifies to the program that it should proceed with scoring and the scoring of the player's rolls from doubles
    private String determineRoundWinners() {
        for (int i = 0; i < 1;) {
            rollDices();
            System.out.println("The banker rolled a " + die1.getLastRolledNum() + ", " + die2.getLastRolledNum() + ", and " + die3.getLastRolledNum() + "!");
            try {
                Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
            } catch (Exception e) {
                System.out.println("error");
            }
            if (dices[0] == dices[1] && dices[1] == dices[2]) {
                System.out.println("The banker has rolled a double!\nThe banker wins the round!");
                try {
                    Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
                } catch (Exception e) {
                    System.out.println("error");
                }
                return "banker";
            } else if (dices[0] == 4 && dices[1] == 5 && dices[2] == 6) {
                System.out.println("The banker has rolled the top three numbers!\nThe banker wins the round!");
                try {
                    Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
                } catch (Exception e) {
                    System.out.println("error");
                }
                return "banker";
            } else if (dices[0] == 1 && dices[1] == 2 && dices[2] == 3) {
                System.out.println("The banker has rolled the bottom three numbers!\nThe banker loses the round!");
                try {
                    Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
                } catch (Exception e) {
                    System.out.println("error");
                }
                return "player";
            } else if (checkForDouble()) {
                System.out.println("The banker has rolled a double!\nHis score is " + dices[2] + ", try to get a higher score than him!");
                try {
                    Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
                } catch (Exception e) {
                    System.out.println("error");
                }
                banker.setScore(dices[2]);
                return "doubles";
            }
            System.out.println("The banker rolled no combos!\nThe banker has to roll again!");
            try {
                Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
            } catch (Exception e) {
                System.out.println("error");
            }
        }
        return null;
    }
    //The next method returns a boolean that checks if the players are still in the game and the banker haven't lost yet
    private boolean playersHaveNotLostOrWin() {
        return banker.getChipsNum() > 0 && player1.getChipsNum() > 0 || player2.getChipsNum() > 0 || player3.getChipsNum() > 0;
    }
    private boolean checkForDouble() {
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
}