import java.util.*;
import java.io.*;
public class Maze{

    private char[][] maze;
    private boolean animate;//false by default

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!

      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:
         throw a FileNotFoundException or IllegalStateException
    */
    public Maze(String filename) throws FileNotFoundException{
      animate = false;
      try {
        File f = new File(filename);
        Scanner s = new Scanner(f);
        int r = 0;
        int c = 0;
        while (s.hasNextLine()) {
          r = s.nextLine().length();
          c++;
        }
        maze = new char[c][r];
        s = new Scanner(f);
        int i = 0;
        while (s.hasNextLine()) {
          String line = s.nextLine();
          for (int j = 0; j < r; j++) {
            maze[i][j] = line.charAt(j);
          }
          i++;
        }
      }
      catch (FileNotFoundException e) {
          System.out.println("File not found" + filename);
        }
    }

    public String toString() {
      String s = "";
      for (char[] r : maze) {
        for (char c : r) {
          s += c;
        }
        s += "\n";
      }
      return s;
    }

    /*
    public boolean hasEnd(){
      for (int i = 0; i < maze.length; i++){
        for (int x = 0; x < maze[0].length; x++){
          if (maze[i][x] == 'E') return true;
        }
      }
      return false;
    }

    public boolean hasStart(){
      for (int i = 0; i < maze.length; i++){
        for (int x = 0; x < maze[0].length; x++){
          if (maze[i][x] == 'S') return true;
        }
      }
      return false;
    }

    public int getRows(String f) throws FileNotFoundException{
      File file = new File(f);
      Scanner s = new Scanner(file);
      return s.nextLine().length();
    }

    public int getCols(String f) throws FileNotFoundException{
      int cols = 0;
      File file = new File(f);
      Scanner s = new Scanner(file);
      while (s.hasNext()){
        cols++;
      }
      return cols;
    }
    */

    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }

    public void setAnimate(boolean b){
        animate = b;
    }

    public void clearTerminal(){
        //erase terminal, go to top left of screen.
        System.out.println("\033[2J\033[1;1H");
    }


    /*Wrapper Solve Function returns the helper function
      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    */
    public int solve(){
      int r = 0, c = 0;
      for (int i = 0; i < maze.length; i++){
        for (int j = 0; j < maze[0].length; j++){
          if (maze[i][j] == 'S'){
            r = i;
            c = j;
          }
        }
      }
      return solve(r,c);
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.

      Postcondition:
        The S is replaced with '@' but the 'E' is not.
        All visited spots that were not part of the solution are changed to '.'
        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col){ //you can add more parameters since this is private

      //automatic animation! You are welcome.
      if(animate){
          clearTerminal();
          System.out.println(this);
          wait(20);
      }

      int count = 1;
      char empty = ' ';
      char end = 'E';
      //these represent the next character in each direction
      char up = maze[row][col + 1];
      char down = maze[row][col - 1];
      char right = maze[row + 1][col];
      char left = maze[row - 1][col];
      //checks if next move is the end
      if (up == end || down == end || left == end || right == end) {
        maze[row][col] = '@';
        for (int i = 0; i < maze.length; i++){
          for (int j = 0; j < maze[0].length; j++){
            if (maze[i][j] == '@') count++;
          }
        }
        return count;
      }
      //if next direction is a valid move solves that
      if (up == empty) {
        maze[row][col] = '@';
        return solve(row,col+1);
      }
      if (down == empty) {
        maze[row][col] = '@';
        return solve(row,col-1);
      }
      if (right == empty) {
        maze[row][col] = '@';
        return solve(row+1,col);
      }
      if (left == empty) {
        maze[row][col] = '@';
        return solve(row-1,col);
      }

        //automatic animation! You are welcome.

        return count;
    }

}
