public class Banker {
    int chipsNum;
    int score;
    public Banker() {
        chipsNum = 1000;
        score = 0;
    }
    public int getChipsNum() {
        return chipsNum;
    }
    public void updateChips(int num) {
        chipsNum += num;
    }
}
