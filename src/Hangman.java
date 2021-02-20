import javax.swing.*;

public class Hangman {
    private Message message;

    public static void main(String[] args) {
        Hangman game = new Hangman();
        Object[] options = {"Yes, please", "No way!"};
        int again;
        do {
            game.play();
            game.showResult();
            again = JOptionPane.showOptionDialog(
                    null,
                    "Play again?",
                    null,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,     //do not use a custom Icon
                    options,  //the titles of buttons
                    options[0]);
        } while(again == JOptionPane.YES_OPTION);
    }

    private void getMessage() {
        this.message = new Message();
    }

    private void play() {
        getMessage();
        System.out.println(message.cheatersNeverProsper());
        while(!(won() || lost())) {
            // FIXME: NullPointer if I press cancel here.
            char guessChar = JOptionPane.showInputDialog(message.toString() + "\nPlease choose a letter").charAt(0);
            message.guess(guessChar);
        }
    }

    private void showResult() {
        String gameResult = won() ? "You Win!" : "You lost... your life...";
        gameResult = gameResult + " The word was \"" + message.toString() +"\"";
        JOptionPane.showMessageDialog(null, gameResult);
    }

    private boolean won() {
        return message.solved();
    }

    private boolean lost() {
        return false; // false will become gallows.isDead()
    }
}