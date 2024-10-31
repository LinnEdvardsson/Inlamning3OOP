import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Listener implements ActionListener {
    private JButton winButton;
    private JButton startButton;
    private CurrentGame cG;

    public Listener(JButton button, CurrentGame currentGame) {
        this.winButton = winButton;
        this.startButton = startButton;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == winButton) {

        }
        if(e.getSource() == startButton) {

        }
//        if (e.getSource() == winButton) {
//            CurrentGame fastGame = new CurrentGame(GamePanel, buttonList, movesLabel, true);
//            //            cG.fastWin();
//        } else if (e.getSource() == startButton) {
//            CurrentGame game = new CurrentGame(GamePanel, buttonList, movesLabel, false);
//        }
    }
}





