class Solution {
    public int totalNQueens(int n) {
        if (n == 1) {
            return 1;
        }

        if (n < 4) {
            return 0;
        }

        boolean[][] board = new boolean[n][n];

        // i'm thinking you only have to loop through the first row as a
        // only one queen can possibly be placed on each row
        for (int col = 0; col < n; col++) {
            // TODO: 1) place the first queen at board[0][col]
            // TODO: 2) place the second queen at the first open spot on row 1
            // TODO: 3) continue down to the final row
            // TODO: 4) check if n queens placed
            // TODO: 5) if not back track and try the next column on the last row
            // TODO: 6) continue backtracking all the way back up the board

        }
    }

    private boolean placeQueens(int n, int row, int col) {

    }
}
