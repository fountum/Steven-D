import java.io.File;

public class FileTesting {
    public static void main(String[] args) {
        File[] files;
        String dir = System.getProperty("user.dir");
        File mydir = new File(dir);
        files = mydir.listFiles();
        for(File f : files){
            if(f.getName().endsWith(".txt")){
                System.out.println(f.getName().substring(0, f.getName().indexOf(".txt")));
            }
        }
    }
}
