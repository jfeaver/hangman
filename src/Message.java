import java.util.Random;
import java.util.regex.Pattern;

public class Message {
    private static String[] values = {"Denver", "Fort Collins", "Steamboat Springs", "Pueblo", "Durango", "Fraser", "Nederland", "Boulder", "Louisville"};
    private String value;
    private char[] lowerValue;
    private char[] revealedValue;
    private Pattern letterRegex = Pattern.compile("[a-zA-Z]");

    public Message() {
        int rnd = new Random().nextInt(values.length);
        value =  values[rnd];
        revealedValue = new char[value.length()];
        lowerValue = new char[value.length()];
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (isLetter(c)) {
                revealedValue[i] = '_';
                lowerValue[i] = Character.toLowerCase(value.charAt(i));
            } else {
                revealedValue[i] = c;
            }
        }
    }

    public String toString() {
        if (solved()) {
            return value;
        }

        char[] prettyValue = new char[revealedValue.length * 2 - 1];
        for (int i = 0; i < revealedValue.length; i++) {
            prettyValue[i * 2] = revealedValue[i];
            // Add a short space (U+2009) between each character for readability
            if (i + 1 < revealedValue.length) {
                prettyValue[i * 2 + 1] = ' ';
            }
        }
        return new String(prettyValue);
    }

    public boolean guess(char guessChar) {
        if (!isLetter(guessChar)) {
            return false;
        }
        boolean result = false;
        for (int i = 0; i < value.length(); i++) {
            if (Character.toLowerCase(guessChar) == lowerValue[i]) {
                result = true;
                revealedValue[i] = value.charAt(i);
            }
        }
        return result;
    }

    public boolean solved() {
        return value.contentEquals(new String(revealedValue));
    }

    public String cheatersNeverProsper() {
        return value;
    }

    private boolean isLetter(char c) {
        return letterRegex.matcher(String.valueOf(c)).matches();
    }
}
