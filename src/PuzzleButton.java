import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PuzzleButton extends JButton {

    public PuzzleButton(String text, boolean gameTile) {
        this.setText(text);
        setButtonColor();
        assignMouseListener();

        setFont(gameTile);
        setSize(gameTile);

        //Hör till Graphics2D och det bidrar bara till runda hörn
        setContentAreaFilled(false); // Disable default button appearance
        setFocusPainted(false); // Disable focus painting
        setBorderPainted(false); // Disable border painting
    }

    public void setSize(boolean gameTile){
        this.setPreferredSize(gameTile ? new Dimension(80, 80) : new Dimension(140, 40));
    }

    public void setFont(boolean gameTile){
        this.setFont(gameTile? new Font("Courier New", Font.BOLD, 20) : new Font("Courier New", Font.BOLD, 14));
    }

    public void assignMouseListener(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {setBackground(buttonPressedColor());}
            @Override
            public void mouseReleased(MouseEvent e) {setBackground(buttonColor());}
            @Override
            public void mouseEntered(MouseEvent e) {setBackground(buttonHoverColor()); }
            @Override
            public void mouseExited(MouseEvent e) {setBackground(buttonColor()); }
        });
    }

    public void setButtonColor(){
        this.setBackground(buttonColor());
        this.setForeground(new Color(255, 255, 255));
    }

    public Color buttonColor() {
        return new Color(250, 95, 94);
    }

    public Color buttonPressedColor() {
        return new Color(255, 150, 147);
    }

    public Color buttonHoverColor() {
        Color color1 = buttonColor();
        Color color2 = buttonPressedColor();

        int red = (color1.getRed() + color2.getRed()) / 2;
        int green = (color1.getGreen() + color2.getGreen()) / 2;
        int blue = (color1.getBlue() + color2.getBlue()) / 2;

        return new Color(red, green, blue); // Mid-tone pink coral
    }

    //Ger knapparna runda hörn
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
    }
}
