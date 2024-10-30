import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameFrameTest extends JFrame {

    JPanel GamePanel = new JPanel();
    JPanel notGamePanel = new JPanel();
    JButton startButton = new JButton("New game");
    JButton winButton = new JButton("Win game");
    JPanel counterPanel = new JPanel();
    JLabel counterLabel = new JLabel("MOVES:");
    JLabel movesLabel = new JLabel("0");
    List<JButton> buttonList = new ArrayList<>();

    public GameFrameTest(){
        this.setLayout(new BorderLayout());

        this.add(GamePanel, BorderLayout.CENTER);
        this.add(notGamePanel, BorderLayout.SOUTH);
        this.add(counterPanel, BorderLayout.NORTH);

        counterPanel.setLayout(new FlowLayout());
        counterPanel.setOpaque(false);
        counterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        counterPanel.add(counterLabel);
        counterPanel.add(movesLabel);
        counterLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        movesLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        GamePanel.setLayout(new GridLayout(4,4, 5,5));
        GamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GamePanel.setOpaque(false);

        notGamePanel.setLayout(new FlowLayout());
        notGamePanel.add(startButton, BorderLayout.SOUTH);
        notGamePanel.add(winButton, BorderLayout.SOUTH);
        notGamePanel.setOpaque(false);

        startButton.setSize(10,15);

        initializeButtons();
        //här testar jag. testar att om man trycker på winButton, startar version av spelet som anropar fastwinmetoden
        startButton.addActionListener(l -> { CurrentGame game = new CurrentGame(GamePanel, buttonList, movesLabel,false); });
        winButton.addActionListener(l -> { CurrentGame fastGame = new CurrentGame(GamePanel, buttonList, movesLabel,true);});


        setUpFrame();
    }

    public Color buttonColor(){
        return Color.decode("#FC9B6D");
    }
    public Color buttonPressedColor(){
        return Color.decode("#FFBC9B");
    }

    public Color buttonHoverColor() {
        Color color1 = buttonColor();
        Color color2 = buttonPressedColor();

        int red = (color1.getRed() + color2.getRed()) / 2;
        int green = (color1.getGreen() + color2.getGreen()) / 2;
        int blue = (color1.getBlue() + color2.getBlue()) / 2;

        return new Color(red, green, blue);
    }

    public void initializeButtons(){
        for(int i = 0; i <= 16; i++){
            JButton button = new RoundedButton();
            button.setBackground(buttonColor());
            button.setFont(new Font("Courier New", Font.BOLD, 20));
            button.setForeground(new Color(255, 255, 255));
            button.setPreferredSize(new Dimension(80, 80));
            assignMouseListener(button);
            buttonList.add(button);
        }
    }

    public void assignMouseListener(JButton button){
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {button.setBackground(buttonPressedColor());}
            @Override
            public void mouseReleased(MouseEvent e) {button.setBackground(buttonColor());}
            @Override
            public void mouseEntered(MouseEvent e) {button.setBackground(buttonHoverColor()); }
            @Override
            public void mouseExited(MouseEvent e) {button.setBackground(buttonColor()); }
        });
    }

    public void setUpFrame(){
        setSize(500,500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
