package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    public ListView<Friend> listFriends = new ListView<>();
    public Button btnDeleteFriend;
    public Button btnMakeFriend;
    public TextField txtGender;
    public TextField txtAge;
    public TextField txtName;
    public Label lblName;
    public Label lblAge;
    public Label lblGender;
    public ListView<String> listGroups = new ListView<>();
    public TextField txtGroup;
    public BufferedReader br;
    public FileReader fr;

    public void getFiles(){
        String dir = System.getProperty("user.dir");
        File mydir = new File(dir);

        File[] files = mydir.listFiles();
        ArrayList<String> temp = new ArrayList<>();

        for(File f : files){
            if(f.getName().endsWith(".txt")){
                if (!(f.getName().substring(0, f.getName().indexOf("."))) .equals("ogText")) {
                    temp.add(f.getName().substring(0, f.getName().indexOf(".")));
                }
            }
        }
        listGroups.getItems().clear();
        listGroups.getItems().addAll(temp);

    }

    public void makeFriend(MouseEvent mouseEvent) throws IOException {
        Friend temp = new Friend(txtName.getText(), Integer.parseInt(txtAge.getText()), txtGender.getText(), txtGroup.getText());
        //listFriends.getItems().add(temp);
        listGroups.getItems().add(txtGroup.getText());

        txtName.clear();
        txtAge.clear();
        txtGender.clear();

    }

    public void deleteFriend(MouseEvent mouseEvent) throws IOException {

        boolean initialClean = false;
        boolean erased = false;
        String targetGroup = listGroups.getSelectionModel().getSelectedItem() + ".txt";
        Friend temp = listFriends.getSelectionModel().getSelectedItem();
        listFriends.getItems().remove(temp);

        FileWriter clearfw = new FileWriter(targetGroup);
        BufferedWriter clearbw = new BufferedWriter(clearfw);

        clearbw.write("");
        clearbw.close();
        for (Friend f : listFriends.getItems()){
            f.toFile(targetGroup, true);
        }

    }


    public void displayFriends(MouseEvent mouseEvent) {
        Friend temp = listFriends.getSelectionModel().getSelectedItem();
        lblName.setText(temp.getName());
        lblAge.setText(Integer.toString(temp.getAge()));
        lblGender.setText(temp.getGender());
    }

    public void displayGroups(MouseEvent mouseEvent) throws IOException{
        String fileName = listGroups.getSelectionModel().getSelectedItem() + ".txt";
        FriendCreator fc = new FriendCreator();
        listFriends.getItems().clear();
        listFriends.getItems().addAll(fc.getFriends(fileName));
    }
}
