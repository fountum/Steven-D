import java.io.File;
import java.io.FileNotFoundException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("illiad.txt"));
        HashSet<String> tempStrings = new HashSet<>();
        HashSet<Word> wordSet = new HashSet<>();
        while (scan.hasNext()) {
            tempStrings = new HashSet(Arrays.asList(scan.nextLine().split(" ")));
            for (String s : tempStrings) {
                wordSet.add(new Word(s));
            }
        }

        Iterator<Word> iter = wordSet.iterator();

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        System.out.println("Total unique words: " + wordSet.size());
    }
}
