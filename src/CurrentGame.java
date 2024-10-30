import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class CurrentGame implements ActionListener {

    String[][] gameBoard = new String[4][4];
    JPanel buttonsPanel;
    JButton pressedButton;
    List<JButton> buttons;

    public CurrentGame(){}

    public CurrentGame(JPanel buttonsPanel, List<JButton> buttons, boolean fastGame) {
        this.buttonsPanel = buttonsPanel;
        this.buttons = buttons;

        //Claude hjälpte mig inse att jag behövde resetta actionlisteners från knapparna,
        //eftersom vi faktiskt gör permanenta förändringar till knapparna här inne
        for (JButton button : buttons) {
            for (ActionListener al : button.getActionListeners()) {
                button.removeActionListener(al);
            }
        }

        if (fastGame){
            fastWin();
        }
        else{
            fillArray(false);
            fillGameBoardPanel();
        }
    }

    //Hjälp-metod för att fylla tvådimenstionella arrayen
    public List<Integer> getNumbers(boolean fastWin) {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i <= 15; i++){
            numbers.add(i);
        }

        if (fastWin) {
            int numbOne = numbers.get(0);
            int numbTwo = numbers.get(1);
            numbers.set(0, numbTwo);
            numbers.set(1, numbOne);
        }

        Collections.shuffle(numbers);
        return numbers;
    }

    //Fyller själva tvådimensionella arrayen
    public void fillArray(boolean fastWin) {
        List<Integer> numbers = getNumbers(fastWin);
        Random randomIndex =  new Random();
        for(int row = 0; row <gameBoard.length; row++){
            for(int col = 0; col <gameBoard[0].length; col++){
                int index = randomIndex.nextInt(numbers.size());
                gameBoard[row][col] = String.valueOf(numbers.get(index));
                if (gameBoard[row][col].equals("0")){
                    gameBoard[row][col] = " ";
                }
                numbers.remove(index);
            }
        }
    }

    //Fyller spelaplanen samt adderar actionlisteners till knappar
    //denna ändrade jag nu med en buttonslista passerad in i klassen
    public void fillGameBoardPanel(){
        buttonsPanel.removeAll();
        int buttonIndex = 0;
        for(int row = 0; row <gameBoard.length; row++){
            for(int col = 0; col <gameBoard[0].length; col++){
                JButton button = buttons.get(buttonIndex);
                button.setText(gameBoard[row][col]);
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
    public boolean checkAdjacent(JButton pressedButton){
        for(int i = 0; i<gameBoard.length; i++){
            for(int j = 0; j<gameBoard[0].length; j++){
                if(pressedButton.getText().equals(gameBoard[i][j]) ){

                    if (j < gameBoard[i].length - 1 && gameBoard[i][j + 1].equals(" ")) {
                        return true;
                    }
                    else if (j > 0 && gameBoard[i][j - 1].equals(" ")) {
                        return true;
                    }
                    else if (i > 0 && gameBoard[i - 1][j].equals(" ")) {
                        return true;
                    }
                    else if (i < gameBoard.length - 1 && gameBoard[i + 1][j].equals(" ")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Placerar ut draget på planen, dvs byter plats på empty button med pressed button
    public void makeMove(int[] emptySpot, int[] pressedSpot){

        String pressed = gameBoard[pressedSpot[0]][pressedSpot[1]];
        String empty = gameBoard[emptySpot[0]][emptySpot[1]];

        gameBoard[pressedSpot[0]][pressedSpot[1]] = empty;
        gameBoard[emptySpot[0]][emptySpot[1]] = pressed;

    }

    //Ger index för tomma knappen
    public int[] indexEmptyButton(){
        for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j < gameBoard[0].length; j++){
                if(gameBoard[i][j].equals(" ")){
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
                if (gameBoard[row][col].equals(pressedButton.getText())){
                    return new int[] {row, col};
                }
            }
        }
        return new int[] {-1, -1};
    }

    //Actionevent
    @Override
    public void actionPerformed(ActionEvent e) {
        pressedButton = (JButton) e.getSource();
        boolean validMove = checkAdjacent(pressedButton);

        if (validMove){
            int[] pressed = indexPressedButton();
            int[] emptySpot = indexEmptyButton();
            makeMove(emptySpot, pressed);
            fillGameBoardPanel();
        }
    }

    public void setVisibility(JButton button){
        if(button.getText().equals(" ")){
            button.setVisible(false);
        }
        else{
            button.setVisible(true);
        }
    }

   /* public String[][] getGameBoard(){
       int rows = gameBoard.length;
       int col = gameBoard[0].length;
       int[] tempArray = new int[rows * col];

        int index = 0;
       for(int i = 0; i < rows; i++){
           for(int j = 0; j < col; j++){
               tempArray[i++] = Integer.parseInt(gameBoard[i][j]);
           }
       }
       return gameBoard;
    }*/


    public boolean checkWin (){
        int expNumb = 1;

        //går igenom hela brädet
        for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j < gameBoard[i].length; j++){

                //kollar om sista positionen på brädet är tom
                if(i == gameBoard.length - 1 && j == gameBoard[i].length -1){
                    return gameBoard[i][j].equals(" ");
                }
                if(!gameBoard[i][j].equals(String.valueOf(expNumb))){
                    return false;
                }
                expNumb ++;
            }
        }
        return true;

    }

    public void gameSet(){
        if(checkWin()){
            WinFrame winFrame = new WinFrame();
        }
    }

    public void fastWin(){
        String[][] getnumb = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", " ", "15"}
        };
        gameBoard = getnumb;
        fillGameBoardPanel();
    }

}
