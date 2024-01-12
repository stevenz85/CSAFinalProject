public class Die {
    int lastRolledNum;
    public Die() {}
    public int getLastRolledNum(){
        return lastRolledNum;
    }
    public void roll() {
        lastRolledNum = (int) (Math.random() * 6) + 1;
    }
}
