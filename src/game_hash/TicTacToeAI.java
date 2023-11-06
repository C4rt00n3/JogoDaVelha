package game_hash;

public class TicTacToeAI {
    private static final String EMPTY = " ";
    private static final String X = "X";
    private static final String O = "O";

    public static void main(String[] args) {
        String[][] board = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
        };
        int[] bestMove = minimax(board, X);
    }
    
    public int[] play(String[][] board) {
    	int[] bestMove = minimax(board, X);
    	if(!board.equals(null)) {
        	return bestMove;
    	}else {
    		return bestMove;
    	}
    	
    }

    public static int[] minimax(String[][] board, String player) {
        String opponent = (player.equals("X")) ? O : X;
        int[] bestMove = null;
        int bestScore = (player.equals(X)) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(EMPTY)) {
                    board[i][j] = player;
                    int score = minimaxScore(board, player, opponent, false);
                    board[i][j] = EMPTY;

                    if ((player.equals(X) && score > bestScore) || (player.equals(O) && score < bestScore)) {
                        bestScore = score;
                        bestMove = new int[]{i, j};
                    }
                }
            }
        }
        return bestMove;
    }

    public static int minimaxScore(String[][] board, String player, String opponent, boolean isMaximizing) {
        String winner = checkWinner(board);
        if (winner.equals(X)) {
            return 1;
        } else if (winner.equals(O)) {
            return -1;
        } else if (winner.equals("T")) {
            return 0;
        }

        int bestScore = (isMaximizing) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(EMPTY)) {
                    board[i][j] = (isMaximizing) ? player : opponent;
                    int score = minimaxScore(board, player, opponent, !isMaximizing);
                    board[i][j] = EMPTY;
                    if (isMaximizing) {
                        bestScore = Math.max(score, bestScore);
                    } else {
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }

    public static String checkWinner(String[][] board) {
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].equals(EMPTY) && board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2])) {
                return board[i][0];
            }
            if (!board[0][i].equals(EMPTY) && board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i])) {
                return board[0][i];
            }
        }
        if (!board[0][0].equals(EMPTY) && board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])) {
            return board[0][0];
        }
        if (!board[0][2].equals(EMPTY) && board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])) {
            return board[0][2];
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(EMPTY)) {
                    return EMPTY;
                }
            }
        }
        return "T";
    }
}
