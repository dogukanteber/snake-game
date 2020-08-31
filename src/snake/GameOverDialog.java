package snake;

import javax.swing.*;

public class GameOverDialog extends JFrame {

    public static final int DIALOG_WIDTH = 300;
    public static final int DIALOG_HEIGHT = 150;

    private JPanel dialogPanel;
    private JButton tryAgainButton;
    private JButton goBackToMainButton;
    private JLabel gameOverMessage;
    ImageIcon frameIcon;

    public GameOverDialog() {
        initFrame();
        initListeners();
    }

    private void initFrame() {
        loadIcon();
        setTitle("Game is over");
        add(dialogPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(DIALOG_WIDTH,DIALOG_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initListeners() {
        tryAgainButtonListener();
        goBackButtonListener();
    }

    private void tryAgainButtonListener() {
        tryAgainButton.addActionListener( e -> {
            new Snake();
            dispose();
        });
    }

    private void goBackButtonListener() {
        goBackToMainButton.addActionListener( e -> {
            GameFrame frame = new GameFrame();
            new Menu(frame);
            dispose();
        });
    }

    public void setGameOverMessage( String message ) {
        gameOverMessage.setText("Game is over!!!    Your score is " + message);
    }

    private void loadIcon() {
        frameIcon = new ImageIcon("src/res/icon.png");
        setIconImage(frameIcon.getImage());
    }

}
