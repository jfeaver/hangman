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
            JLabel gallowsLabel = new JLabel(gallows.toString());
            JLabel blankLabel = new JLabel("");
            String promptHtml = "<html>" + message.toString() + "<br/>Please choose a letter:</html>";

            JLabel promptLabel = new JLabel(promptHtml);
            String guess = JOptionPane.showInputDialog(new JLabel[] {gallowsLabel, blankLabel, promptLabel});
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
            JLabel gallowsLabel = new JLabel(gallows.toString());
            String gameResult = won() ? "You Win!" : "You lost... your life...";
            gameResult = gameResult + " The word was \"" + message.cheatersNeverProsper() + "\"";
            JLabel gameResultLabel = new JLabel(gameResult);
            JOptionPane.showMessageDialog(null, new JLabel[] {gallowsLabel, gameResultLabel});
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