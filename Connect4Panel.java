import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Connect4Panel extends JPanel {
    int[][] board;
    HashMap<Integer,Color[]> colors;
    boolean p1turn;
    boolean gameOver;

    public Connect4Panel(){
        board = new int[6][7];
        for(int[] r:board)
            System.out.println(Arrays.toString(r));

        colors = new HashMap<>();
        colors.put(1, new Color[]{new Color(245, 39, 39),new Color(143, 12, 10)});
        colors.put(0, new Color[]{new Color(255,255,255),new Color(255,255,255)});
        colors.put(-1, new Color[]{new Color(242, 229, 41),new Color(163, 155, 23)});

        p1turn = true;
        gameOver = false;
    }

    public void playMove(int c, boolean p1turn){
        if(gameOver) return;

        System.out.println(getValidMoves());

        int r = getColIndex(c);
        board[r][c] = p1turn ? 1 : -1;

        if(checkWinner()!=0) gameOver = true;
    }

    public int getColIndex(int c){
        int r = -1;
        while(r<5&&board[r+1][c]==0)
            r++;
        
        return r;
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
        int bestMove = 0;
        int bestScore = Integer.MAX_VALUE;

        ArrayList<Integer> validMoves = getValidMoves();
        for (int move : validMoves) {
            int r = getColIndex(move);
            board[r][move] = -1;
            int score = minimax(10, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
            System.out.println(move+" "+score);
            board[r][move] = 0;

            if (score < bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        playMove(bestMove, false);
    }

    private int minimax(int depth, int alpha, int beta, boolean p1) {
        int score = checkWinner();
        if (score == 1 || score == -1|| depth == 0) {
            return score;
        }

        ArrayList<Integer> moves = getValidMoves();
        if (p1) {
            int maxScore = Integer.MIN_VALUE;
            for (int move : moves) {
                int r = getColIndex(move);
                board[r][move] = 1;
                score = minimax(depth - 1, alpha, beta, false);
                maxScore = Math.max(maxScore, score);
                board[r][move] = 0;
                alpha = Math.max(alpha, maxScore);
                if (beta <= alpha)
                    break;
            }
            return maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;
            for (int move : moves) {
                int r = getColIndex(move);
                board[r][move] = -1;
                score = minimax(depth - 1, alpha, beta, true);
                minScore = Math.min(minScore, score);
                board[r][move] = 0;
                beta = Math.min(beta, minScore);
                if (beta <= alpha)
                    break;
            }
            return minScore;
        }
    }

    public int evaluate(){
        return checkWinner();
    }

    private ArrayList<Integer> getValidMoves() {
        ArrayList<Integer> moves = new ArrayList<>();
        int[] columnOrder = {3,2,4,1,5,0,6};

        for (int col:columnOrder)
            if (board[0][col] == 0)
                moves.add(col);
    
        return moves;
    }

    public void paintComponent(Graphics g){
        drawBoard(g);
    }

    public void drawBoard(Graphics g){
        g.setColor(new Color(57, 51, 232));
        g.fillRect(0, 0, 840, 720);
        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                g.setColor(new Color(29, 25, 125));
                g.fillOval(120*j+10, 120*i+10, 100, 100);

                g.setColor(colors.get(board[i][j])[0]);
                g.fillOval(120*j+15, 120*i+15, 90, 90);
            }
        }
    }
}
