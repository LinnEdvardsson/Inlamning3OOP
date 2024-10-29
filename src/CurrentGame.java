import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class CurrentGame implements ActionListener {

    String[][] gameBoard = new String[4][4];
    JPanel buttonsPanel;
    JButton pressedButton;
    List<JButton> buttons;

    public CurrentGame(JPanel buttonsPanel, List<JButton> buttons) {
        this.buttonsPanel = buttonsPanel;
        this.buttons = buttons;

        //Claude hjälpte mig inse att jag behövde resetta actionlisteners från knapparna,
        //eftersom vi faktiskt gör permanenta förändringar till knapparna här inne
        for (JButton button : buttons) {
            for (ActionListener al : button.getActionListeners()) {
                button.removeActionListener(al);
            }
        }


        fillArray();
        fillGameBoardPanel();
    }

    //Hjälp-metod för att fylla tvådimenstionella arrayen
    public List<Integer> getNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i <= 15; i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    //Fyller själva tvådimensionella arrayen
    public void fillArray(){
        List<Integer> numbers = getNumbers();
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
}
