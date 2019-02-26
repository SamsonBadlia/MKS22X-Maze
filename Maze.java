import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze{

  public static void main(String[] args) {

    Maze m = new Maze("Maze.txt");
    System.out.println(m);

  }

  int rows,cols;
  char[][] maze;

  public Maze(String s){
    try{
      maze = new char[getRows(s)][getCols(s)];
      File file = new File(f);
      Scanner s = new Scanner(file);
      String str;
      int j
      while(s.hasNextLine()){
        str = s.nextLine();
        for (int i = 0; i < str.length(); i++){
          maze[]
        }
      }
      }
    }
    catch(FileNotFoundException f){
      System.out.println("Hey, there's no file");
    }

  }

  public String toString(){
    String s = "";
    for (int i = 0; i < rows; i++){
      for (int j = 0 ;j < cols; j++){
        if (j == cols - 1) s += "\n";
        s += maze[i][j];
      }
    }
    return s;
  }

  public int getRows(String f) throws FileNotFoundException{
    File file = new File(f);
    Scanner s = new Scanner(file);
    while (s.hasNextLine()){
      rows++;
    }
    return rows;
  }

  public int getCols(String f) throws FileNotFoundException{
    File file = new File(f);
    Scanner s = new Scanner(file);
    while (s.hasNext()){
      cols++;
    }
    return cols;
  }

}
