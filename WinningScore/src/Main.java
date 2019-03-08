
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> appleScore = new ArrayList<>();
        ArrayList<Integer> bananaScore = new ArrayList<>();
        int applePoints = 0;
        int bananaPoints = 0;
        for (int i = 0; i < 6;i++) {
            if (i < 3) {
                appleScore.add(scan.nextInt());
            } else{
                bananaScore.add(scan.nextInt());
            }
        }

        Iterator<Integer> appleIter = appleScore.iterator();
        Iterator<Integer> bananaIter = bananaScore.iterator();
        for (int i = 3; i > 0; i--) {
            applePoints += appleIter.next()*i;
            bananaPoints += bananaIter.next()*i;
        }

        if (applePoints>bananaPoints){
            System.out.println("A");
        } else if (applePoints < bananaPoints) {
            System.out.println("B");
        } else {
            System.out.println("T");
        }


    }
}
