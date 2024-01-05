public class Banker {
    int chipsNum;
    int score;
    Die die1;
    Die die2;
    Die die3;
    public Banker() {
        chipsNum = 0;
        score = 0;
        die1 = new Die();
        die2 = new Die();
        die3 = new Die();
    }
    public void rollDices() {
        die1.roll();
        die2.roll();
        die3.roll();
    }
    public void updateChips(int num) {
        chipsNum += num;
    }
}
