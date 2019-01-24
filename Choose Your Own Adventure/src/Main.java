import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
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
        public ArrayList<Page> getPaths(){
            ArrayList<Page> temp = new ArrayList<>();
            for (int i = 0; i < pathsAvailable; i++) {
                Page p = pages.get(paths[i]-1);
                if (p.visted !=true && !p.equals(this) && !queue.contains(p)) { //adds no pages that have already been visited or self
                    temp.add(p);
                }
            }
            return temp;
        }

    }

    private static int[][] pagesData;
    private static ArrayList<Page> pages = new ArrayList<>();
    private static LinkedList<Page> queue = new LinkedList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());
        pagesData = new int[N][N+1];
        int steps = 0;
        int levelPaths; //number of possible non-repeat paths for the current level
        int visits = 0;
        int level = 1;
        int lowest = 999999999;
        for (int i = 0; i < N; i++){
            String temp[] = in.nextLine().split(" ");
            for (int ii = 1; ii < temp.length; ii++) {
                pagesData[i][ii] = Integer.parseInt(temp[ii]);
            }
            pages.add(new Page(i+1, Integer.parseInt(temp[0]), false ));
        }

        queue.add(pages.get(0));
        levelPaths = 1;
        while (!queue.isEmpty()){
            Page page = queue.remove(); //grabs first item from list and removes it
            steps++;
            if (page.pathsAvailable == 0){
                page.visted = true;
                if (level < lowest){
                    lowest = level;
                }
            } else if (page.visted == false) {
                page.visted = true;
                if (page.pathsAvailable != 0) {
                    queue.addAll(page.getPaths());
                }
                if (steps == levelPaths){
                    steps = 0;
                    levelPaths = queue.size();
                    level++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if(pages.get(i).visted == true){
                visits++;
            }
        }
        //printing results
        if (visits == pages.size()){
            System.out.println("Y");
        } else {
            System.out.println("N");
        }
        System.out.println(lowest);




    }

}
