import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Listener implements ActionListener {
    private JButton winButton;
    private CurrentGame cG;


    public Listener(JButton button, CurrentGame currentGame) {
        this.winButton = winButton;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == winButton){
            cG.fastWin();
            }
        }

    }





