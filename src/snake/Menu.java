package snake;

import javax.swing.*;

public class Menu extends JPanel {

    JFrame frame;
    private JPanel panel;
    private JButton startButton;
    private JButton exitButton;

    public Menu( JFrame frame ) {
        this.frame = frame;

        initListeners();
        repaintPanel();
    }

    private void repaintPanel() {
        frame.getContentPane().removeAll();
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }



    private void initListeners() {
        startButtonListener();
        //settingsButtonListener();
        exitButtonListener();
    }

    private void startButtonListener() {
        startButton.addActionListener( e -> {
            new Snake();
            frame.dispose();
        });
    }

    private void exitButtonListener() {
        exitButton.addActionListener( e -> System.exit(0) );
    }

}
