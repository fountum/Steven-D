import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Node> nodes = new ArrayList<>();

    public static void getLeafs(Node N){
        //base case
        if (N.left == 0 && N.right == 0){
            System.out.println( N.id);
        }
        else{
            //left branch
            if (N.left != 0) {
                for (int i = 0; i < nodes.size(); i++) {
                    if (nodes.get(i).id == N.left) {
                        Node n = nodes.get(i);
                        getLeafs(n);
                        break;
                    }
                }
            }
            //right branch
            if (N.right != 0) {
                for (int i = 0; i < nodes.size(); i++) {
                    if (nodes.get(i).id == N.right) {
                        Node n = nodes.get(i);
                        getLeafs(n);
                        break;
                    }
                }
            }
        }

    }


    public static void setup() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("Tree.txt"));
        while(scan.hasNextLine()){
            String[] temp = scan.nextLine().split(" ");
            int id = Integer.parseInt(temp[0]);
            int left = Integer.parseInt(temp[1]);
            int right = Integer.parseInt(temp[2]);
            nodes.add(new Node(id,left,right));
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
       setup();
       getLeafs(nodes.get(0));

    }
}
