import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;

public class Connect4Panel extends JPanel {
    int[][] board;
    HashMap<Integer,Color> colors;
    boolean p1turn;

    public Connect4Panel(){
        board = new int[6][7];
        for(int[] row:board)
            System.out.println(Arrays.toString(row));

        colors = new HashMap<>();
        colors.put(1, new Color(255,0,0));
        colors.put(0, new Color(255,255,255));
        colors.put(-1, new Color(200,200,0));

        p1turn = true;
    }

    public void playMove(int c){
        int r = 0;
        while(r<5&&board[r+1][c]==0)
            r++;
        board[r][c] = p1turn ? 1 : -1;
        p1turn = !p1turn;
    }

    public void paintComponent(Graphics g){
        drawBoard(g);
    }

    public void drawBoard(Graphics g){
        g.setColor(new Color(0,0,255));
        g.fillRect(0, 0, 840, 720);
        g.setColor(new Color(255,255,255));
        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                g.setColor(colors.get(board[i][j]));
                g.fillOval(120*j+15, 120*i+15, 90, 90);
            }
        }
    }
}
