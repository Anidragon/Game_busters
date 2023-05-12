public class SudokuSolver {
    public static void main(String[] args) {
        // Example input for a 9x9 Sudoku grid
        char[][] grid = {
            {'_', '8', '9', '3', '7', '_', '_', '4', '_'},
            {'3', '_', '_', '_', '_', '_', '_', '_', '_'},
            {'_', '_', '_', '1', '_', '_', '2', '_', '_'},
            {'_', '_', '_', '_', '_', '8', '_', '_', '4'},
            {'_', '7', '6', '_', '9', '_', '_', '8', '_'},
            {'_', '5', '_', '_', '_', '_', '_', '_', '_'},
            {'5', '_', '_', '_', '_', '_', '_', '6', '_'},
            {'4', '_', '_', '_', '3', '_', '_', '_', '_'},
            {'_', '6', '3', '7', '_', '_', '_', '_', '1'}
        };

        solveSudoku(grid);
        printGrid(grid);
    }

    public static void solveSudoku(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return;
        }

        solve(grid);
    }

    private static boolean solve(char[][] grid) {
        int n = grid.length;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == '_') {
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(grid, row, col, num)) {
                            grid[row][col] = num;

                            if (solve(grid)) {
                                return true;
                            } else {
                                grid[row][col] = '_';
                            }
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isValid(char[][] grid, int row, int col, char num) {
        int n = grid.length;

        // Check row and column
        for (int i = 0; i < n; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }

        // Check 3x3 box
        int boxStartRow = row - row % 3;
        int boxStartCol = col - col % 3;

        for (int i = boxStartRow; i < boxStartRow + 3; i++) {
            for (int j = boxStartCol; j < boxStartCol + 3; j++) {
                if (grid[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printGrid(char[][] grid) {
        int n = grid.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}