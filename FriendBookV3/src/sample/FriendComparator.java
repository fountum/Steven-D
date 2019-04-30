package sample;
import java.util.Comparator;
public class FriendComparator implements Comparator<Friend> {
    @Override
    public int compare(Friend f1, Friend f2) {
        int index1 = f1.getName().indexOf(" ")+1;
        int index2 = f2.getName().indexOf(" ")+1;
        char[] lastName1 = f1.getName().substring(f1.getName().indexOf(" ")+1).toCharArray();
        char[] lastName2 = f2.getName().substring(f2.getName().indexOf(" ")+1).toCharArray();
        char char1;
        char char2;
        int length = lastName1.length;
        if (lastName2.length>length){
            length = lastName2.length;
        }
        try {
            for (int i = 0; i < length; i++) {
                char1 = lastName1[i];
                char2 = lastName2[i];
                if (char1 < char2) {
                    return  1;
                } else if (char1 > char2) {
                    return -1;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
            if (lastName1.length < lastName2.length) {
                return 1;
            } else if (lastName1.length > lastName2.length) {
                return -1;
            } else {
                return 0;
            }
        }
        return 0;
    }


}
