package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Snake extends JPanel implements ActionListener {

    public static int GAME_SPEED = 100;
    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;
    public static final int BORDER_SIZE = 10;
    public static final int LIMIT = 29;
    public static final int MAX_LENGTH = WIDTH * HEIGHT / ( BORDER_SIZE * BORDER_SIZE );

    public static final int INITIAL_COORD_X = 50;
    public static final int INITIAL_COORD_Y = 50;

    public static final String appleCrunch = "src/res/apple_crunch.wav";
    public static final String gameOverSound = "src/res/game_over.wav";

    static boolean leftDirection = false;
    static boolean rightDirection = true;
    static boolean upDirection = false;
    static boolean downDirection = false;
    private boolean isGame = true;


    JFrame frame;
    JLabel score;
    Timer timer;
    ImageIcon frameIcon;

    int lengthOfSnake;
    private int apple_x;
    private int apple_y;

    int[] coord_x = new int[MAX_LENGTH];
    int[] coord_y = new int[MAX_LENGTH];


    public Snake() {
        initUI();
        initGame();
    }

    private void initUI() {
        initFrame();
        initBoard();
    }

    private void initFrame() {
        frame = new JFrame();
        loadIcon();
        frame.setTitle("Snake Game");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(WIDTH+BORDER_SIZE,HEIGHT+BORDER_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initBoard() {
        score = new JLabel();
        add(score);
        frame.add(this);
        setBackground(Color.BLACK);
        addKeyListener( new Key() );
        setFocusable(true);
        requestFocusInWindow();
    }


    private void initGame() {
        initSnake();
        initTimer();
        locateApple();
    }

    private void initTimer() {
        timer = new Timer(GAME_SPEED,this);
        timer.start();
    }

    private void initSnake() {
        lengthOfSnake = 3;
        for (int i = 0; i <lengthOfSnake; i++) {
            coord_x[i] = INITIAL_COORD_X - BORDER_SIZE * i;
            coord_y[i] = INITIAL_COORD_Y;
        }
    }

    private void loadIcon() {
        frameIcon = new ImageIcon("src/res/icon.png");
        frame.setIconImage(frameIcon.getImage());
    }

    private void locateApple() {
        int r = (int) (Math.random() * LIMIT);
        apple_x = r * BORDER_SIZE;

        r = (int) (Math.random() * LIMIT);
        apple_y = r * BORDER_SIZE;
    }

    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent(g);
        doDrawings(g);
    }

    private void doDrawings(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(apple_x,apple_y,BORDER_SIZE,BORDER_SIZE);
        drawSnake(g);
    }

    private void drawSnake( Graphics g ) {
        for (int i = 0; i < lengthOfSnake; i++) {
            g.drawRect(coord_x[i],coord_y[i],BORDER_SIZE,BORDER_SIZE);
        }
    }

    private void move() {
        for (int i = lengthOfSnake; i > 0; i--) {
            coord_x[i] = coord_x[i-1];
            coord_y[i] = coord_y[i-1];
        }
        if ( leftDirection ) {
            coord_x[0] -= BORDER_SIZE;
        }
        if ( rightDirection ) {
            coord_x[0] += BORDER_SIZE;
        }
        if ( upDirection )
            coord_y[0] -= BORDER_SIZE;
        if ( downDirection )
            coord_y[0] += BORDER_SIZE;
    }

    private void isAppleEaten() {
        if ( apple_x == coord_x[0] && apple_y == coord_y[0] ) {
            lengthOfSnake++;
            snake.Sound.playMusic(appleCrunch);
            locateApple();
        }
    }

    private void checkCollision() {
        for (int i = lengthOfSnake; i > 0; i--) {
            if ((lengthOfSnake > 4) && coord_x[0] == coord_x[i] && coord_y[0] == coord_y[i]) {
                isGame = false;
                break;
            }
        }
        if ( coord_x[0] >= WIDTH - BORDER_SIZE )
            isGame = false;
        if ( coord_y[0] >= HEIGHT - ( 3 * BORDER_SIZE ) )
            isGame = false;
        if ( coord_x[0] < 0 )
            isGame = false;
        if ( coord_y[0] < 0 )
            isGame = false;

        if ( !isGame ) {
            timer.stop();
            snake.Sound.playMusic(gameOverSound);
        }
    }

    private void gameOver() {
        GameOverDialog gameOver = new GameOverDialog();
        gameOver.setGameOverMessage( String.valueOf(lengthOfSnake - 3) );
    }

    public void resetGame() {
        leftDirection = false;
        rightDirection = true;
        upDirection = false;
        downDirection = false;
    }

    private void updateScore() {
        score.setText("Score : " +  ( lengthOfSnake - 3 ));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( isGame ) {
            isAppleEaten();
            checkCollision();
            move();
            updateScore();
        }
        repaint();
        if ( !isGame ) {
            gameOver();
            frame.dispose();
            resetGame();
        }
    }
}