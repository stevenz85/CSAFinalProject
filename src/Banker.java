public class Banker {
    int chipsNum;
    int score;
    public Banker() {
        chipsNum = 1000;
        score = 0;
    }
    public void updateChips(int num) {
        chipsNum += num;
    }
}
