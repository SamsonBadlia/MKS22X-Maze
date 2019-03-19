import java.io.*;

public class Driver{
    public static void main(String[]args){
      String filename = "data1.dat";
      try{
        Maze f;
        f = new Maze(filename);//true animates the maze.

        f.setAnimate(true);
        System.out.println(f);
        System.out.println(f.solve());
      }catch(FileNotFoundException e){
        System.out.println("Invalid filename: "+filename);
      }
      String file = "data2.dat";
      try{
        Maze s;
        s = new Maze(file);//true animates the maze.

        s.setAnimate(true);
        System.out.println(s);
        System.out.println(s.solve());
      }catch(FileNotFoundException e){
        System.out.println("Invalid filename: "+filename);
      }
      String fil = "data3.dat";
      try{
        Maze fi;
        fi = new Maze(fil);//true animates the maze.

        fi.setAnimate(true);
        System.out.println(fi);
        System.out.println(fi.solve());
      }catch(FileNotFoundException e){
        System.out.println("Invalid filename: "+fil);
      }
    }
}
