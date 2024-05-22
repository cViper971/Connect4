import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.Timer;

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
        boolean debounce = false;
        public void mouseClicked(MouseEvent e){
            int column = e.getX()/120;
            if(game.getColIndex(column)>0&&!debounce){
                debounce = true;
                game.playMove(column, true);
                game.repaint();
                Timer timer = new Timer(200, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        game.AImove();
                        game.repaint();
                        debounce = false;
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }

        public void mousePressed(MouseEvent e){}

        public void mouseReleased(MouseEvent e){}

        public void mouseEntered(MouseEvent e){}

        public void mouseExited(MouseEvent e){}
    }
}
