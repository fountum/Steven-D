import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int lines = scan.nextInt();
        scan.nextLine();
        ArrayList<String> encodedLines = new ArrayList<>();
        for (int i = 0; i < lines; i++) {
            encodedLines.add(scan.nextLine());
        }
        Iterator<String> encodedIter = encodedLines.iterator();
        while (encodedIter.hasNext()) {
            String line = encodedIter.next();
            HashSet<Character> chars = new HashSet<>();
            for (char c:line.toCharArray()){
                chars.add(c);
            }

            chars.
            for (int i = 1; i < chars.size()+1; i++) {

            }
        }
    }
}
