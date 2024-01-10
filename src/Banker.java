public class Banker {
    int chipsNum;
    public Banker() {
        chipsNum = 1000;
    }
    public int getChipsNum() {
        return chipsNum;
    }
    public void updateChips(int num) {
        chipsNum += num;
    }
}
