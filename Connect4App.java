import java.awt.MouseInfo;
import java.awt.event.*;
import javax.swing.JFrame;

public class Connect4App extends JFrame {
    Connect4Panel game;

    public Connect4App(){
        setSize(855,755);
        setResizable(false);
        setVisible(true);
        game = new Connect4Panel();
        add(game);
        addMouseListener(new ClickListener());
    }

    private class ClickListener implements MouseListener{
        public void mouseClicked(MouseEvent e){
            int column = e.getX()/120;
            game.playMove(column);
            game.AImove();
            game.repaint();
        }

        public void mousePressed(MouseEvent e){}

        public void mouseReleased(MouseEvent e){}

        public void mouseEntered(MouseEvent e){}

        public void mouseExited(MouseEvent e){}
    }
}
