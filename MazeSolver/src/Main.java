import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the maze file name:");
        String fileName = keyboard.nextLine();
        String filePath = "C:\\Users\\asus\\IdeaProjects\\CMPEHW2Q2\\src\\" + fileName;
        char[][] map = reader(filePath);

        if (map != null) {
            Maze maze = new Maze(map);


            maze.pathToTreasure();
        }
    }

    private static char[][] reader(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rows = 0;
            int columns = 0;


            while ((line = br.readLine()) != null) {
                rows++;
                if (columns == 0) {
                    columns = line.length();
                }
            }

            char[][] maze = new char[rows][columns];


            br.close();
            BufferedReader br1 = new BufferedReader(new FileReader(filePath));


            for (int i = 0; i < rows; i++) {
                line = br1.readLine();
                for (int j = 0; j < columns; j++) {
                    maze[i][j] = line.charAt(j);
                }
            }

            return maze;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}