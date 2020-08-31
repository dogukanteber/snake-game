package snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Key extends KeyAdapter {

    @Override
    public void keyPressed( KeyEvent e ) {
        int keyCode = e.getKeyCode();
        if ( keyCode == KeyEvent.VK_LEFT && !Snake.rightDirection ) {
            Snake.leftDirection = true;
            Snake.upDirection = false;
            Snake.downDirection = false;
        }
        if ( keyCode == KeyEvent.VK_RIGHT && !Snake.leftDirection ) {
            Snake.rightDirection = true;
            Snake.upDirection = false;
            Snake.downDirection = false;
        }
        if ( keyCode == KeyEvent.VK_DOWN && !Snake.upDirection ) {
            Snake.downDirection = true;
            Snake.rightDirection = false;
            Snake.leftDirection = false;
        }
        if ( keyCode == KeyEvent.VK_UP && !Snake.downDirection ) {
            Snake.upDirection = true;
            Snake.rightDirection = false;
            Snake.leftDirection = false;
        }
    }

}
