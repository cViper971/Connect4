import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;

public class Connect4Panel extends JPanel {
    int[][] board;
    HashMap<Integer,Color> colors;
    boolean p1turn;
    boolean gameOver;

    public Connect4Panel(){
        board = new int[6][7];
        for(int[] r:board)
            System.out.println(Arrays.toString(r));

        colors = new HashMap<>();
        colors.put(1, new Color(255,0,0));
        colors.put(0, new Color(255,255,255));
        colors.put(-1, new Color(200,200,0));

        p1turn = true;
        gameOver = false;
    }

    public void playMove(int c){
        if(gameOver) return;

        int r = 0;
        while(r<5&&board[r+1][c]==0)
            r++;
        board[r][c] = p1turn ? 1 : -1;
        p1turn = !p1turn;

        if(checkWinner()!=0) gameOver = true;

        if(!p1turn) AImove();
    }

    public int checkWinner() {
        for (int r = 0; r < 6; r++)
            for (int c = 0; c < 4; c++)
                for(int i=-1;i<2;i+=2)
                    if (board[r][c] == i && board[r][c + 1] == i && board[r][c + 2] == i && board[r][c + 3] == i) 
                        return i;
    
        for (int c = 0; c < 7; c++)
            for (int r = 0; r < 3; r++)
                for(int i=-1;i<2;i+=2)
                    if (board[r][c] == i && board[r + 1][c] == i && board[r + 2][c] == i && board[r + 3][c] == i)
                        return i;

        for (int r = 0; r < 3; r++) 
            for (int c = 0; c < 4; c++)
                for(int i=-1;i<2;i+=2)
                    if (board[r][c] == i && board[r + 1][c + 1] == i && board[r + 2][c + 2] == i && board[r + 3][c + 3] == i) 
                        return i;
       
        for (int r = 3; r < 6; r++)
            for (int c = 0; c < 4; c++)
                for(int i=-1;i<2;i+=2)
                    if (board[r][c] == i && board[r - 1][c + 1] == i && board[r - 2][c + 2] == i && board[r - 3][c + 3] == i)
                        return i;

        return 0;
    }

    public void AImove(){
        playMove(3);
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
