package snake;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater( () -> {
            GameFrame frame = new GameFrame();
            Menu gameMenu = new Menu(frame);
        });
    }

}
