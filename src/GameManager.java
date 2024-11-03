import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameManager implements ActionListener {

    private PuzzleGrid gameBoard;
    private JLabel moveDisplay;
    private int moves;

    public GameManager(JPanel gamePanel, List<JButton> buttons, JLabel moveDisplay, boolean quickGame) {
        this.gameBoard = new PuzzleGrid(buttons, gamePanel);
        this.moveDisplay = moveDisplay;
        this.moves = 0;

        //Om quickGame är true, sätts vi upp spelplanen fär en snabb vinst
        if(quickGame) {
            gameBoard.setUpFastWin();
        }
        initializeGame();
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

    //Hanterar ett drag; tar in en knapp och tittar om den är bredvid den tomma platsen
    //Gör i så fall draget, uppdaterar spelplan, kollar om det är en vinst
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

    //Anropar checkWin för spelplanen, visar vinst-fönster om det är en vinst
    private void checkForWin() {
        if (gameBoard.checkWin()) {
            showWinScreen();
        }
    }

    //Öppnar vinst-fönster med en liten delay för att visa sista draget
    private void showWinScreen() {
        try {
            Thread.sleep(200);
            new WinFrame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Klassens actionlistener, hämtar den tryckta knappen och anropar handleMove
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressedButton = (JButton) e.getSource();
        handleMove(pressedButton);
    }
}
