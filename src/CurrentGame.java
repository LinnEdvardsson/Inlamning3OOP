import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class CurrentGame implements ActionListener {

    private int[][] gameBoard = new int[4][4];
    private List<JButton> buttons;

    private JPanel buttonsPanel;
    private JButton pressedButton;

    private JLabel moveCounter;

    public CurrentGame(JPanel buttonsPanel, List<JButton> buttons, JLabel moveCounter, boolean fastGame) {
        this.buttonsPanel = buttonsPanel;
        this.buttons = buttons;
        this.moveCounter = moveCounter;

        resetGame();

        if (fastGame){
            fastWin();
        }
    }

    public void resetGame(){
        resetCounter();
        resetActionListeners();
        resetGameBoard();
    }

    public void resetCounter(){
        moveCounter.setText("0");
    }

    public void resetGameBoard(){
        fillArray();
        fillGameBoardPanel();
    }

    public void resetActionListeners(){
        for (JButton button : buttons) {
            for (ActionListener al : button.getActionListeners()) {
                button.removeActionListener(al);
            }
        }

    }

    //Hjälp-metod för att fylla tvådimenstionella arrayen
    public List<Integer> getShuffledNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i <= 15; i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    //Rotate flyttar första alla element ett steg till vänster som gör att 0 hamnar sist.
    public List<Integer> getWinningNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i <= 15; i++){
            numbers.add(i);
        }
        Collections.rotate(numbers, -1);
        return numbers;
    }

    //Fyller själva tvådimensionella arrayen med siffror från getShuffledNumbers
    public void fillArray() {
        List<Integer> numbers = getShuffledNumbers();
        int index = 0;
        for(int row = 0; row < gameBoard.length; row++) {
            for(int col = 0; col < gameBoard[0].length; col++) {
                gameBoard[row][col] = numbers.get(index);
                index++;
            }
        }
    }

    //Fyller panelen med knappar utefter arrayer
    public void fillGameBoardPanel(){
        buttonsPanel.removeAll();
        int buttonIndex = 0;
        for(int row = 0; row <gameBoard.length; row++){
            for(int col = 0; col <gameBoard[0].length; col++){
                JButton button = buttons.get(buttonIndex);
                button.setText(String.valueOf(gameBoard[row][col]));
                buttonsPanel.add(button);
                setVisibility(button);
                button.addActionListener(this);
                buttonIndex++;
            }
        }
        buttonsPanel.revalidate();
        buttonsPanel.repaint();
    }

    //Kollar om rutan till höger/vänster/norr/söder är tom
//    public boolean checkAdjacent(JButton pressedButton){
//        for(int i = 0; i<gameBoard.length; i++){
//            for(int j = 0; j<gameBoard[0].length; j++){
//                if(pressedButton.getText().equals(gameBoard[i][j]) ){
//
//                    if (j < gameBoard[i].length - 1 && gameBoard[i][j + 1].equals(" ")) {
//                        return true;
//                    }
//                    else if (j > 0 && gameBoard[i][j - 1].equals(" ")) {
//                        return true;
//                    }
//                    else if (i > 0 && gameBoard[i - 1][j].equals(" ")) {
//                        return true;
//                    }
//                    else if (i < gameBoard.length - 1 && gameBoard[i + 1][j].equals(" ")) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }

    public boolean isAdjacent(int[] pressedSpot, int[] emptySpot){
        int rowDiff = pressedSpot[0] - emptySpot[0];
        int colDiff = pressedSpot[1] - emptySpot[1];
        return (rowDiff == 1 || rowDiff == -1) && colDiff == 0 || (colDiff == 1 || colDiff == -1) && rowDiff == 0;
    }

    //Placerar ut draget på planen, dvs byter plats på empty button med pressed button
    public void makeMove(int[] emptySpot, int[] pressedSpot){

        //Hämtar först ut index för den tryckta och tomma knappen
        int pressed = gameBoard[pressedSpot[0]][pressedSpot[1]];
        int empty = gameBoard[emptySpot[0]][emptySpot[1]];

        gameBoard[pressedSpot[0]][pressedSpot[1]] = empty;
        gameBoard[emptySpot[0]][emptySpot[1]] = pressed;

        checkForWin(); //Kollar om det är en vinst

        updateCounterLabel(); //Uppdaterar med ett nytt drag
    }

    //Uppdaterar texten på dragräknaren
    public void updateCounterLabel(){
        int currentMoves = Integer.parseInt(moveCounter.getText()) + 1;
        moveCounter.setText(String.valueOf(currentMoves));
    }


    //Ger index för tomma knappen
    public int[] indexEmptyButton(){
        for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j < gameBoard[0].length; j++){
                if(gameBoard[i][j] == 0){ //Plockar ut index för den tomma platsen
                    return new int[]{i, j};
                }
            }
        }
        return new int[] {-1,-1};
    }

    //Ger index för den tryckta knappen
    public int[] indexPressedButton(){
        for(int row = 0; row <gameBoard.length; row++){
            for(int col = 0; col <gameBoard[0].length; col++){
                if (gameBoard[row][col] == Integer.parseInt(pressedButton.getText())){ //Tittar om denna plats i arrayn har samma text som platsen på den tryckta knappen
                    return new int[] {row, col};
                }
            }
        }
        return new int[] {-1, -1};
    }

    //Metod för snabbvinst, flytta en bricka
    public void fastWin(){
        gameBoard = setUpWinningBoard();
        fillGameBoardPanel();
    }

    private int[][] setUpWinningBoard() {
        return new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 0, 15}
        };
    }


    //checkwin, om alla nummer ligger i ordning med tom bricka sist
    public void checkForWin() {
        List<Integer> expected = getWinningNumbers();

        List<Integer> actual = new ArrayList<>();
        for (int[] row : gameBoard) {
            for (int num : row) {
                actual.add(num);
            }
        }
        //Jämför nuvarande spelplansläge med ett förväntat
        if (actual.equals(expected)) {
            displayWinFrame();
        }
    }

    public void displayWinFrame(){
        try{
            Thread.sleep(200);
            WinFrame wF = new WinFrame(); //Öppnar upp nytt fönster för vinst
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //Bestämmer synlighet på knapparna
    public void setVisibility(JButton button){
        if((Integer.parseInt(button.getText()) == 0)){
            button.setVisible(false);
        }
        else{
            button.setVisible(true);
        }
    }

    //Actionevent när man klickar på en spelbricka
    @Override
    public void actionPerformed(ActionEvent e) {

        pressedButton = (JButton) e.getSource();
        int[] pressedSpot = indexPressedButton();
        int[] emptySpot = indexEmptyButton();

        if (isAdjacent(pressedSpot, emptySpot)){
            makeMove(emptySpot, pressedSpot);
            fillGameBoardPanel();
        }
    }
}
