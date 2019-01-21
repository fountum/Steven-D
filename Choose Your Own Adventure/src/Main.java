import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static class Page{
        private int id;
        private int pathsAvailable;
        private boolean visted;
        private int[] paths;
        Page(int id, int pathsAvailable, boolean visted) {
            this.id = id;
            this.pathsAvailable = pathsAvailable;
            this.visted = visted;
        }

        public void setupPaths(){
            for (int i = 0; i < pathsAvailable; i++) {
                this.paths[i] = pagesData[id-1][i];
            }
        }

        public String toString() {
            return ("id:" + id + " pathsAvailable:" + pathsAvailable+ " visted:" + visted);
        }
    }

    private static int[][] pagesData;
    private static ArrayList<Page> pages = new ArrayList<>();

    public static void walk(int page){
        if (pagesData[page][0] == 0){

        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("H:\\My Documents\\Steven-D\\Choose Your Own Adventure\\src\\in.txt"));
        int N = Integer.parseInt(in.nextLine());
        pagesData = new int[N][N];
        for (int i = 0; i < N; i++){
            String temp[] = in.nextLine().split(" ");
            pages.add(new Page(i+1, Integer.parseInt(temp[0]), false ));
            for (int ii = 1; ii < pagesData[i][0]+1; ii++) {
                pagesData[i][ii] = Integer.parseInt(temp[ii-1]);
            }
        }
        /*
        for (int i = 0; i < N; i++){
            System.out.println(pagesData[i][0]);
            for (int ii = 1; ii < pagesData[i][0]+1; ii++) {
                System.out.println(pagesData[i][ii]);
            }
        }


        */

        for (int i = 0; i < pages.size(); i++) {
            System.out.println(pages.get(i));
        }
    }
}
