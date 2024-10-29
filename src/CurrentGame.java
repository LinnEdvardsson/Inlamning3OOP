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

    public CurrentGame(JPanel buttonsPanel) {
        this.buttonsPanel = buttonsPanel;
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
            //ändrade från i till 0 i gameBoard[0].length
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
    public void fillGameBoardPanel(){
        buttonsPanel.removeAll();
        for(int row = 0; row <gameBoard.length; row++){
            for(int col = 0; col <gameBoard[0].length; col++){
                JButton button = new JButton(gameBoard[row][col]);
                buttonsPanel.add(button);
            }
        }
        addActionListeners();
        buttonsPanel.revalidate();
        buttonsPanel.repaint();
    }

    //Lägger till actionlisteners till knapparna
    public void addActionListeners() {
        for (int i = 0; i < buttonsPanel.getComponentCount(); i++) {
            Component component = buttonsPanel.getComponent(i);
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.addActionListener(this);
            }
        }
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
        //tilldelar klassens pressedbutton den tryckta knappen
        pressedButton = (JButton) e.getSource();
        //kollar om movet funkar
        boolean validMove = checkAdjacent(pressedButton);

        //om det funkar, hämta index och placera ut move
        if (validMove){

            int[] pressed = indexPressedButton();
            int[] emptySpot = indexEmptyButton();
            makeMove(emptySpot, pressed);
            //fyll spelplanen igen med nuvarande skick
            fillGameBoardPanel();
        }
    }
}
