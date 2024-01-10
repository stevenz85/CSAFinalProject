public class Die {
    int lastRolledNum;
    public Die() {}

    public int roll() {
        lastRolledNum = (int) (Math.random() * 6) + 1;
        return lastRolledNum;
    }
}
