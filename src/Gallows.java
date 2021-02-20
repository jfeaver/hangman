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
        return
                "<html><pre>" +
                "╔════╕<br/>" +
                "║    │<br/>" +
                "║    " + hangIf("O", 1) + "<br/>" +
                "║   " + hangIf("/", 3) + hangIf("|", 2) + hangIf("\\", 4) + "<br/>" +
                "║    " + hangIf("|", 2) + "<br/>" +
                "║  " + hangIf("_", 7) + hangIf("/", 5) + " " + hangIf("\\", 6) + hangIf("_", 8) + "<br/>" +
                "║<br/>" +
                "╙────────" +
                "</pre></html>";
    }

    private String hangIf(String bodyPart, int deathLevelReached) {
        if (deathApproaches >= deathLevelReached) {
            return bodyPart;
        } else {
            return " ";
        }
    }
}
