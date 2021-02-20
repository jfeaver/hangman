import javax.swing.*;

public class Hangman {
    private Message message;
    private Gallows gallows;
    private boolean quit = false;

    public static void main(String[] args) {
        Hangman game = new Hangman();
        Object[] options = {"Yes, please", "No way!"};
        int again;
        do {
            game.play();
            game.resolve();
            again = JOptionPane.showOptionDialog(
                    null,
                    "Play again?",
                    null,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
        } while(again == JOptionPane.YES_OPTION);
    }

    private void play() {
        message = new Message();
        gallows = new Gallows();
        System.out.println(message.cheatersNeverProsper());
        while(!(won() || lost() || quit)) {
            // FIXME: NullPointer if I press cancel here.
            String guess = JOptionPane.showInputDialog(message.toString() + "\nPlease choose a letter");
            if (guess == null) {
                quit = true; // Cancel button is pressed
            } else if (!guess.isBlank()) {
                if (!message.guess(guess.charAt(0))) {
                    gallows.incrementHangman();
                };
            }
        }
    }

    private void resolve() {
        // show the result
        if (!quit) {
            String gameResult = won() ? "You Win!" : "You lost... your life...";
            gameResult = gameResult + " The word was \"" + message.cheatersNeverProsper() + "\"";
            JOptionPane.showMessageDialog(null, gameResult);
        }
        // reset the quit status
        quit = false;
    }

    private boolean won() {
        return message.solved();
    }

    private boolean lost() {
        return gallows.isDead();
    }
}