public class Banker {
    private int chipsNum;
    private int score;
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
        chipsNum += num;
    }
}
