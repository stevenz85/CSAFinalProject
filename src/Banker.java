public class Banker {
    private int chipsNum;
    private int score;
    private boolean stillInGame;
    public Banker() {
        chipsNum = 1000;
        score = 0;
    }
    public int getChipsNum() {
        return chipsNum;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int num) {
        score = num;
    }
    public void updateChips(int num) {
        if (stillInGame) {
            chipsNum += num;
        }
    }
}
