public class Player {
    private String name;
    private int chipsNum;
    private int score;
    private int wager;
    private boolean stillInGame;
    public Player(String str) {
        name = str;
        chipsNum = 100;
        score = 0;
        wager = 0;
        stillInGame = true;
    }
    public void setWager(int num) {
        wager = num;
    }
    public String getName() {
        return name;
    }
    public int getChipsNum() {
        return chipsNum;
    }
    public int getWager() {
        if (stillInGame) {
            return wager;
        }
        return 0;
    }
    public void winChips() {
        if (stillInGame) {
            chipsNum += wager;
        }
    }
    public void loseChips() {
        chipsNum -= wager;
    }
}
