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


    public int solve(){
         for (int i = 0; i < maze.length; i++){
           for (int j = 0; j < maze[i].length; j++){
             if (maze[i][j] == 'S'){
               maze[i][j] = ' ';
               return solve(i, j, 0);
             }
           }
         }
         return -1;
       }


       private int solve(int row, int col, int steps){
         //automatic animation! You are welcome.
         /*if(animate){
           clearTerminal();
           System.out.println(this);
           wait(20);
         }*/
         if (maze[row][col] == 'E') return steps;
         if (maze[row][col] != ' ') return -1;
         int[][] moves = new int[][]{
           {0,1} , {1,0} , {0,-1} , {-1,0}
         };
         for (int i = 0; i < moves.length; i++){
           int r = row + moves[i][0];
           int c = col + moves[i][1];
           maze[row][col] = '@';
           int solution = solve(r, c, steps + 1);
           if (solution != -1) return solution;
           maze[row][col] = '.';
         }
         return -1;
       }

    }
