public class Gallows {
    private int deathApproaches = 0;
    private final int THE_ABYSS_OF_DEATH = 8;

    void incrementHangman() {
        deathApproaches++;
    }

    boolean isDead() {
        return deathApproaches >= THE_ABYSS_OF_DEATH;
    }

    public String toString() {
        return "";
    }
}
