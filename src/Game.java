import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

public class Game implements ActionListener {

    //DETTA ÄR ETT FÖRSÖK TILL EN KLASS SOM JAG LÄMNADE
    JPanel gamePanel;
    List<JButton> buttons;

    public Game(JPanel gamePanel, List<JButton> buttons) {
        this.gamePanel = gamePanel;
        this.buttons = buttons;
        shuffleButtons();
        showButtons();
    }

    public void shuffleButtons(){
        Collections.shuffle(buttons);
    }

    public void showButtons(){
        for(JButton button: buttons){
            button.setVisible(true);
        }
    }

    public void addActionListeners() {
        for (int i = 0; i < gamePanel.getComponentCount(); i++) {
            Component component = gamePanel.getComponent(i);
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        int index = gamePanel.getComponentZOrder(button);
        int row = index / 4; // Assuming a 4x4 grid
        int col = index % 4;
    }
}
