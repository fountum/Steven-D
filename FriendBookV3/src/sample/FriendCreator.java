package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FriendCreator {
    private FileReader fr;
    private BufferedReader br;

    public ArrayList<Friend> getFriends(String fileName) throws IOException {
        fr = new FileReader(fileName);
        br = new BufferedReader(fr);
        String line;
        ArrayList<String> friendAttrib = new ArrayList<>();
        ArrayList<Friend> friends = new ArrayList<>();
        while ((line = br.readLine()) != null){
            if (line.equals(";")){
                friends.add(parseFriend(friendAttrib));
                friendAttrib.clear();
            }
            else{
                friendAttrib.add(line);
            }
        }
        return friends;
    }

    public Friend parseFriend(ArrayList<String> attributes){
        String name = attributes.get(0);
        int age = Integer.parseInt(attributes.get(1));
        String gender = attributes.get(2);
        Friend temp = new Friend(name, age, gender);
        return temp;
    }


}
