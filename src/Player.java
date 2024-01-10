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
    public String getName() {
        return name;
    }
    public int getChipsNum() {
        return chipsNum;
    }
    public void changeChips(int num) {
        chipsNum += num;
    }
}
