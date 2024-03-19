import java.util.HashSet;

class Solution {
	private char _empty = '.';

	public boolean isValidSudoku(char[][] board) {

		for (int i = 0; i < 9; i++) {
			HashSet<Character> row = new HashSet<Character>();
			HashSet<Character> col = new HashSet<Character>();
			HashSet<Character> cube = new HashSet<Character>();

			for (int j = 0; j < 9; j++) {
				int cubeRow = (i / 3) * 3 + (j / 3);
				int cubeCol = (i % 3) * 3 + (j % 3);

				if (board[i][j] != _empty && row.contains(board[i][j])) {
					return false;
				}

				if (board[j][i] != _empty && col.contains(board[j][i])) {
					return false;
				}

				if (board[cubeRow][cubeCol] != _empty && cube.contains(board[cubeRow][cubeCol])) {
					return false;
				}

				row.add(board[i][j]);
				col.add(board[j][i]);
				cube.add(board[cubeRow][cubeCol]);
			}
		}

		return true;
	}
}
