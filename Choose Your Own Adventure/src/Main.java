import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static class Page{
        private int id;
        private int pathsAvailable;
        private boolean visted;
        private int[] paths; //ints holding ids of pages in array pages
        Page(int id, int pathsAvailable, boolean visted) {
            this.id = id;
            this.pathsAvailable = pathsAvailable;
            this.visted = visted;
            this.paths = new int[pathsAvailable];
            if (pathsAvailable > 0) {
                for (int i = 0; i < pathsAvailable; i++) {
                    this.paths[i] = pagesData[id - 1][i+1];

                }
            }
        }
        public String toString() {
            return ("id:" + id + " pathsAvailable:" + pathsAvailable+ " visted:" + visted);
        }
    }

    public static int visits = 0;
    public static int lowest = 999999999;
    private static int[][] pagesData;
    private static ArrayList<Page> pages = new ArrayList<>();
    private static ArrayList<Page> queue = new ArrayList<>();

    public static void visit(Page page, int level){
        if (page.pathsAvailable ==0 ){
            if (level < lowest){
                lowest = level;
            }
        }
        else{

        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("H:\\My Documents\\Steven-D\\Choose Your Own Adventure\\src\\in.txt"));
        int N = Integer.parseInt(in.nextLine());
        pagesData = new int[N][N];
        for (int i = 0; i < N; i++){
            String temp[] = in.nextLine().split(" ");
            for (int ii = 1; ii < Integer.parseInt(temp[0])+1; ii++) {
                pagesData[i][ii] = Integer.parseInt(temp[ii]);
            }
            pages.add(new Page(i+1, Integer.parseInt(temp[0]), false ));


        }


        /*
        for (int i = 0; i < N; i++){
            System.out.println(pagesData[i][0]);
            for (int ii = 1; ii < pagesData[i][0]+1; ii++) {
                System.out.println(pagesData[i][ii]);
            }
        }


        */



        visit(pages.get(0), 1);


        if (visits == pages.size()){
            System.out.println("Y");
        } else {
            System.out.println("N");
        }

        if (lowest != 999999999){
            System.out.println(lowest);
        }
        else{
            System.out.println(0);
        }



    }

}
