import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PuzzleGrid {

    private String[][] grid;
    private List<JButton> buttons;
    private JPanel gamePanel;

    public PuzzleGrid(List<JButton> buttons, JPanel gamePanel) {
        grid = new String[4][4];
        this.buttons = buttons;
        this.gamePanel = gamePanel;
        fillArray();
    }

    //Skapar en lista med shufflade nummer från 0-15
    public List<Integer> getShuffledNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i <= 15; i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    //Fyller objektets array med listan med shufflade siffror
    public void fillArray() {
        List<Integer> numbers = getShuffledNumbers();
        int index = 0;
        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                grid[row][col] = String.valueOf(numbers.get(index));
                if (grid[row][col].equals("0")) {
                    grid[row][col] = " ";
                }
                index++;
            }
        }
    }

    //Fyller panelen med knappar utefter arrayen och adderar actionlisteners
    public void fillBoard(ActionListener moveListener) {
        int buttonIndex = 0;

        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[0].length; col++){
                JButton button = buttons.get(buttonIndex);
                button.setText(grid[row][col]);
                gamePanel.add(button);

                removeAllListeners(button);
                button.addActionListener(moveListener);

                setVisibility(button);
                buttonIndex++;
            }
        }
    }


    //Resettar och uppdaterar spelplanen
    public void updateGameBoard(ActionListener moveListener){
        gamePanel.removeAll();
        fillBoard(moveListener);
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    //Ger em array med rad och kolumn-index för tom plats
    public int[] indexEmptySpot(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j].equals(" ")){
                    return new int[]{i, j};
                }
            }
        }
        return new int[] {-1,-1};
    }

    //Ger em array med rad och kolumn-index för tryckt knapp
    public int[] indexPressedButton(JButton pressedButton){
        for(int row = 0; row <grid.length; row++){
            for(int col = 0; col <grid[0].length; col++){
                if (grid[row][col].equals(pressedButton.getText())){
                    return new int[] {row, col};
                }
            }
        }
        return new int[] {-1, -1};
    }

    //Byter plats på den pressade knappen och tomma platsen
    public void makeMove(int[] emptySpot, int[] pressedSpot){
        String pressed = grid[pressedSpot[0]][pressedSpot[1]];
        String empty = grid[emptySpot[0]][emptySpot[1]];

        grid[pressedSpot[0]][pressedSpot[1]] = empty;
        grid[emptySpot[0]][emptySpot[1]] = pressed;
    }

    //Kollar om index för den pressade knappen är bredvid en tom plats
    //Skillnaden ska vara 1 om platserna är bredvid varandra, 0 om de är samma plats, >1 om de inte är bredvid
    public boolean isAdjacentToEmptySpot(int[] pressedSpot, int[] emptySpot) {
        int rowDiff = Math.abs(pressedSpot[0] - emptySpot[0]);
        int colDiff = Math.abs(pressedSpot[1] - emptySpot[1]);
        return (rowDiff + colDiff) == 1;
    }

    //Kollar om arrayn är sorterad
    public boolean checkWin() {
        String expected = "123456789101112131415 ";
        StringBuilder current = new StringBuilder();
        //Skapar upp en string av spelplanens nuvarande läge
        for (String[] row : grid) {
            for (String index : row) {
                current.append(index);
            }
        }
        //Returnerar true om de är samma
        return current.toString().equals(expected);
    }

    //Sätter upp griden för en snabb vinst
    public void setUpFastWin(){
        String[][] closeToSorted = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", " ", "15"}
        };
        //Tilldelar en nästan sorterad array till spelplanen
        grid = closeToSorted;
    }

    //Tar bort alla actionlisteners kopplade till en knapp för reset
    private void removeAllListeners(JButton button) {
        for (ActionListener listener : button.getActionListeners()) {
            button.removeActionListener(listener);
        }
    }

    //Sätter rätt synlighet på knapp
    public void setVisibility(JButton button){
        if(button.getText().equals(" ")){
            button.setVisible(false);
        }
        else{
            button.setVisible(true);
        }
    }


    public String[][] getGrid() {
        return grid;
    }

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }
}
