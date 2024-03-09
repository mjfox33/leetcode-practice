class Solution {
    // bit "position" corresponds to a number, so we are setting "positions" 1-9 as
    // unavailable or to 0
    static final int ALL_ZEROS = 0;

    // bit "position" corresponds to a number, so we are setting "positions" 1-9 as
    // available or to 1
    static final int ALL_ONES = 0b1111111110;

    // game constants
    static final int COLS = 9;
    static final int ROWS = 9;
    static final int CUBES = 9;
    static final int ROWS_PER_CUBE = 3;
    static final int COLS_PER_CUBE = 3;
    static final int TOTAL_CELLS = 81;
    static final char EMPTY_SQUARE = '.';

    // We use this to convert '1' - '0' to the int value 1
    static final char ZERO_ASCII = '0';

    // Worker arrays that store what number is in the corresponding row, col, cube
    int[] rowBitmaps, colBitmaps, cubeBitmaps;

    // Working game board will be used to store the partially solved board
    int[] workingGameBoard;

    // This is used to try and solve the square with the least possible digits first
    // This will contain solved squares first followed by empty squares
    int[] squarePriorityQueue;

    // TODO: update the description below
    // Always points to the first empty cell's SQUARE index, which is stored in
    // SEQUENCE
    int squarePriorityStartingIndex = 0;

    // Utility arrays to store mapping from current SQUARE to current
    // ROW/COLs/CUBES:
    // e.g. current square 37 corresponds to row 4, col 1, cube 3;
    int[] currentSquareToCurrentRow, currentSquareToCurrentCol, currentSquareToCurrentCube;

    public void solveSudoku(char[][] board) {
        initWorkerDataStructures();

        initQuickReferenceDataStructures();

        initGameBoard(board);

        // main solver process
        boolean success = place(squarePriorityStartingIndex);
        assert success : "Unsolvable Puzzle!";

        // dump result back from ENTRY array to BOARD
        for (int currentSquare = 0; currentSquare < TOTAL_CELLS; currentSquare++) {
            int row = currentSquareToCurrentRow[currentSquare];
            int col = currentSquareToCurrentCol[currentSquare];
            board[row][col] = (char) (Integer.numberOfTrailingZeros(workingGameBoard[currentSquare]) + ZERO_ASCII);
        }

    }

    // Initialize all working data structures
    // All digits are initially all available (marked by 1) in all
    // rows/columns/cubes
    // Game rule is you can only use each number 1 through 9 once per row or column
    // or cube
    void initWorkerDataStructures() {
        // Initialize all rows to have all numbers available (marked by 1)
        rowBitmaps = new int[ROWS];
        for (int row = 0; row < ROWS; row++) {
            rowBitmaps[row] = ALL_ONES;
        }

        // Initialize all columns to have all numbers available (marked by 1)
        colBitmaps = new int[COLS];
        for (int col = 0; col < COLS; col++) {
            colBitmaps[col] = ALL_ONES;
        }

        // Initialize all cubes to have all numbers available (marked by 1)
        cubeBitmaps = new int[CUBES];
        for (int cube = 0; cube < CUBES; cube++) {
            cubeBitmaps[cube] = ALL_ONES;
        }

        // Initialize the working game board
        workingGameBoard = new int[TOTAL_CELLS];

        // Initialize the priority helper and just set the priority to the square number
        squarePriorityQueue = new int[TOTAL_CELLS];
        for (int currentSquare = 0; currentSquare < TOTAL_CELLS; currentSquare++)
            squarePriorityQueue[currentSquare] = currentSquare;
    }

    // Create and store quick references between the current square and the
    // corresponding row/col/cube
    // This was we do not have to calculate the row/col/cube given the current
    // square later
    void initQuickReferenceDataStructures() {
        currentSquareToCurrentRow = new int[TOTAL_CELLS];
        currentSquareToCurrentCol = new int[TOTAL_CELLS];
        currentSquareToCurrentCube = new int[TOTAL_CELLS];

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int square = row * ROWS + col;
                currentSquareToCurrentRow[square] = row;
                currentSquareToCurrentCol[square] = col;
                // Remember this is integer math so given row = 4, col = 4
                // currentCube = (4 / 3) * 3 + 4 / 3
                // currentCube = 1 * 3 + 1 = 4
                currentSquareToCurrentCube[square] = (row / ROWS_PER_CUBE) * ROWS_PER_CUBE + col / COLS_PER_CUBE;
            }
        }
    }

    // Update the helper and worker data structures given the initial board as
    // char[][]
    // the array uses '1'-'9' for given squares and '.' for empty squares.
    void initGameBoard(char[][] initialGameBoard) {
        // Fill in the given cells. Update the bitmaps at the same time
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                // keep moving along if the current square is empty
                if (initialGameBoard[row][col] == EMPTY_SQUARE) {
                    continue;
                }

                // Calculate current square and cube from the given row and column
                int currentSquare = row * ROWS + col;
                int currentCube = (row / ROWS_PER_CUBE) * ROWS_PER_CUBE + col / COLS_PER_CUBE;

                // Get the value of the current square
                int squareValue = initialGameBoard[row][col] - ZERO_ASCII;

                // Convert the int value to our masked value and update our helper arrays
                // Example if the board says '4' we convert that to 4 and then 0b1000
                int squareBitValue = 1 << squareValue;
                rowBitmaps[row] &= ~squareBitValue;
                colBitmaps[col] &= ~squareBitValue;
                cubeBitmaps[currentCube] &= ~squareBitValue;

                // update the working game board
                workingGameBoard[currentSquare] = squareBitValue;

                // TODO: update what this code does to be more clear
                int currentPriorityIndex = squarePriorityStartingIndex;
                // Starting with the first empty cell, Compact non-empty cells to the front, and
                // use SEQ_START to mark the first empty cell's position
                while (currentPriorityIndex < TOTAL_CELLS && squarePriorityQueue[currentPriorityIndex] != currentSquare)
                    currentPriorityIndex++;
                swapSeq(squarePriorityStartingIndex++, currentPriorityIndex);

            }
        }
    }

    boolean place(int priorityQueueIndex) {
        if (priorityQueueIndex >= TOTAL_CELLS)
            return true;

        int nextSquareIndex = nextSquare(priorityQueueIndex);
        swapSeq(priorityQueueIndex, nextSquareIndex);

        int square = squarePriorityQueue[priorityQueueIndex];
        int currentRow = currentSquareToCurrentRow[square];
        int currentCol = currentSquareToCurrentCol[square];
        int currentCube = currentSquareToCurrentCube[square];
        int currentCellBitmap = rowBitmaps[currentRow] & colBitmaps[currentCol] & cubeBitmaps[currentCube];

        while (currentCellBitmap > 0) {
            // Get each available bit/digit in order
            int nextDigitBit = currentCellBitmap & -currentCellBitmap;
            currentCellBitmap &= ~nextDigitBit;
            workingGameBoard[square] = nextDigitBit;

            // claim this DIGIT is used in row/column/cube
            rowBitmaps[currentRow] &= ~nextDigitBit;
            colBitmaps[currentCol] &= ~nextDigitBit;
            cubeBitmaps[currentCube] &= ~nextDigitBit;

            if (place(priorityQueueIndex + 1))
                return true;

            // undo claims in the bitmaps
            rowBitmaps[currentRow] |= nextDigitBit;
            colBitmaps[currentCol] |= nextDigitBit;
            cubeBitmaps[currentCube] |= nextDigitBit;
            workingGameBoard[square] = ALL_ZEROS;
        }

        swapSeq(priorityQueueIndex, nextSquareIndex);

        // if you get here this is an invalid board
        return false;
    }

    // Find among empty cells the one with the smallest search space: the least bits
    // on its bitmap;
    int nextSquare(int startingSquare) {
        int minSquare = startingSquare;
        int minDigitCount = 100; // could this be 10?
        for (int currentSquare = startingSquare; currentSquare < TOTAL_CELLS; currentSquare++) {
            int square = squarePriorityQueue[currentSquare];
            // Number of bits in CELL_BITMAP is the number of digits that this cell can take
            int cellBitmap = rowBitmaps[currentSquareToCurrentRow[square]]
                    & colBitmaps[currentSquareToCurrentCol[square]]
                    & cubeBitmaps[currentSquareToCurrentCube[square]];
            // Counts the bits, so you know how many digits this CELL can take: we want the
            // minimum one
            int totalAvailableDigits = Integer.bitCount(cellBitmap);
            if (totalAvailableDigits < minDigitCount) {
                minSquare = currentSquare;
                minDigitCount = totalAvailableDigits;
            }
        }
        return minSquare;
    }

    // Helper function to swap values in the priority array
    void swapSeq(int i, int j) {
        int tmp = squarePriorityQueue[i];
        squarePriorityQueue[i] = squarePriorityQueue[j];
        squarePriorityQueue[j] = tmp;
    }
}
