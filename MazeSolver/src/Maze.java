import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Maze {
    private char[][] maze;
    private List<String> paths; // Store paths as strings
    private int rows;
    private int columns;

    public Maze(char[][] maze) {
        this.maze = maze;
        this.rows = maze.length;
        this.columns = maze[0].length;
        this.paths = new ArrayList<>();
    }

    public void pathToTreasure() {
        List<List<Integer>> treasures = Tfinding();

        if (treasures.isEmpty()) {
            System.out.println("0 treasures are found.");
            return;
        }

        for (List<Integer> treasure : treasures) {
            int startRow = 1; // starting row index
            int startCol = 0; // starting column index
            int endRow = treasure.get(0);
            int endCol = treasure.get(1);

            boolean[][] visited = new boolean[rows][columns];
            explorePaths(startRow, startCol, endRow, endCol, new StringBuilder(), visited);
        }

        Collections.sort(paths, (path1, path2) -> path1.length() - path2.length());

        System.out.println(paths.size() + " treasures are found.");
        if(paths.size()!=0){
        System.out.println("Paths are:");
        for (int i = 0; i < paths.size(); i++) {
            System.out.println((i + 1) + ") " + paths.get(i).replaceFirst("^\\s+", ""));
        }}
    }




    private void explorePaths(int row, int col, int endRow, int endCol, StringBuilder currentPath, boolean[][] visited) {
        if (row < 0 || row >= rows || col < 0 || col >= columns || visited[row][col] || maze[row][col] == '+' ||
                maze[row][col] == '-' || maze[row][col] == '|') {
            return;
        }

        currentPath.append(maze[row][col]);
        visited[row][col] = true;

        if (row == endRow && col == endCol) {
            paths.add(currentPath.toString());
        } else {
            explorePaths(row, col + 1, endRow, endCol, currentPath, visited);
            explorePaths(row, col - 1, endRow, endCol, currentPath, visited);
            explorePaths(row + 1, col, endRow, endCol, currentPath, visited);
            explorePaths(row - 1, col, endRow, endCol, currentPath, visited);
        }

        // Backtrack: Remove the last character to explore other paths
        currentPath.deleteCharAt(currentPath.length() - 1);
        visited[row][col] = false;
    }

    private List<List<Integer>> Tfinding() {
        List<List<Integer>> treasures = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (maze[i][j] == 'E') {
                    List<Integer> treasure = new ArrayList<>();
                    treasure.add(i);
                    treasure.add(j);
                    treasures.add(treasure);
                }
            }
        }
        return treasures;
    }
}
