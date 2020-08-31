package snake;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    ImageIcon frameIcon;

    public GameFrame() {
        initFrame();
    }

    private void initFrame() {
        loadIcon();
        setTitle("Snake Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadIcon() {
        frameIcon = new ImageIcon("src/res/icon.png");
        setIconImage(frameIcon.getImage());
    }

    public JFrame getFrame() {
        return this;
    }

}
