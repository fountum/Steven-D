import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> encodedLines = new ArrayList<>();
        int lines = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < lines; i++) {
            encodedLines.add(scan.nextLine());
        }
        Iterator<String> encodedIter = encodedLines.iterator();
        ArrayList<String> decodedLines = new ArrayList<>();
        while (encodedIter.hasNext()) {
            ArrayList<String> line = new ArrayList<>();
            line.addAll(Arrays.asList(encodedIter.next().split(" ")));
            String temp = "";
            for (int i = 0; i < Integer.parseInt(line.get(0)); i++) {
                temp += line.get(1);
            }
            decodedLines.add(temp);
        }
        Iterator<String> decodedIter = decodedLines.iterator();
        while (decodedIter.hasNext()) {
            System.out.println(decodedIter.next());
        }

    }
}
