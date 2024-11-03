import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameManager implements ActionListener {

    PuzzleGrid gameBoard;
    private JLabel moveDisplay;
    private int moves;

    public GameManager(JPanel gamePanel, List<JButton> buttons, JLabel moveDisplay, boolean quickGame) {
        this.gameBoard = new PuzzleGrid(buttons, gamePanel);
        this.moveDisplay = moveDisplay;
        this.moves = 0;

        if(quickGame) {
            gameBoard.setUpFastWin();
            initializeGame();
        }
        else{
            initializeGame();
        }
    }

    //Resettar moves och uppdaterar spelplanen
    private void initializeGame() {
        resetMoves();
        gameBoard.updateGameBoard(this);
    }

    //Gör moves till 0 och uppdaterar komponenten
    private void resetMoves() {
        moves = 0;
        updateMoveDisplay();
    }

    //Uppdaterar komponenten för dragräknare med moves
    private void updateMoveDisplay() {
        moveDisplay.setText(String.valueOf(moves));
    }

    public void handleMove(JButton pressedButton) {
        int[] pressedSpot = gameBoard.indexPressedButton(pressedButton);
        int[] emptySpot = gameBoard.indexEmptySpot();

        if (gameBoard.isAdjacentToEmptySpot(pressedSpot, emptySpot)) {
            gameBoard.makeMove(emptySpot, pressedSpot);
            moves++;
            updateMoveDisplay();
            gameBoard.updateGameBoard(this);
            checkForWin();
        }
    }

    private void checkForWin() {
        if (gameBoard.checkWin()) {
            showWinScreen();
        }
    }

    //Öppnar vinstfönster
    private void showWinScreen() {
        try {
            Thread.sleep(200);
            new WinFrame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressedButton = (JButton) e.getSource();
        handleMove(pressedButton);
    }
}
