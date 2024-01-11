public class Player {
    private String name;
    private int chipsNum;
    private int score;
    private int wager;
    public Player(String str) {
        name = str;
        chipsNum = 100;
        score = 0;
        wager = 0;
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
    public void winChips() {
        chipsNum += wager;
    }
    public void loseChips() {
        chipsNum -= wager;
    }
}
