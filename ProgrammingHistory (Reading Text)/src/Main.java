
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void findWord(String word, ArrayList<String> text){

        for (int i = 0; i < text.size();i++){
            String l = text.get(i);
            //System.out.println(l);
            ArrayList<String> words = new ArrayList<>();
            int start = 0;
            int end = 0;
            while (end != -1){
                end = l.indexOf(" ", end);
               // System.out.println("start"  + start);
               // System.out.println(end);
                if (end ==-1) {
                    for (int ii = 0; ii < words.size(); ii++){
                        String w = words.get(ii);
                        if (w.equals(word)){
                           System.out.println("Sentence " + i + " Word " + ii);
                        }
                    }
                    //System.out.println("m");
                    break;
                }
                else {
                    //System.out.println("strubg " + l.substring(start, end));
                    words.add(l.substring(start, end));
                    start = end+1;
                    if (end != -1){ end++;}
                }

            }

        }
    }

    public static void main(String[] args)throws IOException {
        //limit = length - 1
        FileReader in = new FileReader("ProgrammingHistory.txt");
        BufferedReader br = new BufferedReader(in);
        ArrayList<String> sentences = new ArrayList<>();
        ArrayList<String> exceptions = new ArrayList<>();
        exceptions.addAll(Arrays.asList("e.g.", "ex.", "E.g.", "Ex.","Mr.", "Ms.", "ms.", "mr."));
        String sentence;
        String line;
        while ((line = br.readLine()) != null){
            int indexStart = 0;
            int indexEnd = 0;
            while (indexEnd < line.length()) {
                indexEnd = line.indexOf(".", indexStart);
                System.out.println("index end: " + indexEnd);
                if(indexEnd == - 1){
                    break;
                }
                for (String w : exceptions){
                    if (indexEnd != - 1&& indexEnd + 3 < line.length()) {
                        if (line.substring(indexEnd-1, indexEnd + 3).equals(w)){

                            indexEnd += 3;
                            break;
                        }
                    }
                    else{
                        break;
                    }
                }


                sentence = line.substring(indexStart, indexEnd+1);
                sentences.add(sentence);
                if(indexEnd > line.length()){
                    break;
                }
                indexStart = indexEnd+1;
            }
        }
        for (String l : sentences){
            System.out.println(l);
        }

        System.out.println("Sentences " + sentences.size());

        //17 sentences total

        findWord("for", sentences);

    }
}
