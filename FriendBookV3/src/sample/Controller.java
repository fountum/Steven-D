package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.TreeSet;

public class Controller {
    public ListView<Friend> listFriends = new ListView<>();
    public ListView<String> listGroups = new ListView<>();
    public Button btnDeleteFriend;
    public Button btnMakeFriend;
    public Button btnSortAge;
    public TextField txtGender;
    public TextField txtAge;
    public TextField txtName;
    public TextField txtGroup;
    public Label lblName;
    public Label lblAge;
    public Label lblGender;
    public BufferedReader br;
    public FileReader fr;
    public Button btnSortLastName;

    public static void nameCheck(String n) throws NameException{
        if (!n.replaceAll("\\s", "").matches("[a-zA-Z]+")){
            throw new NameException(n);
        }
        if (n.length() < 3){
            throw new NameException(n);
        }
        if (!n.contains(" ")){
            throw new NameException(n);
        }
    }

    public static void ageCheck(int a) throws AgeException {
        if (a < 18) {
            throw new AgeException(a);
        }
    }

    public static void genderCheck(String g) throws GenderException {
        if (g.replaceAll("\\s", "").isEmpty()) {
            throw new GenderException(g);
        }
    }

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
        txtName.setPromptText("");
        txtAge.setPromptText("");
        txtGender.setPromptText("");
        String group;
        try {
            nameCheck(txtName.getText());
            ageCheck(Integer.parseInt(txtAge.getText()));
            genderCheck(txtGender.getText());
            if (txtGroup.getText().replaceAll("\\s", "").isEmpty()) {
                group = "Uncategorized";
            } else {
                group = txtGroup.getText();
            }
            Friend temp = new Friend(txtName.getText(), Integer.parseInt(txtAge.getText()), txtGender.getText(), group);
            listGroups.getItems().add(txtGroup.getText());
        } catch (NumberFormatException e) {
            txtAge.setPromptText("Invalid age");
        } catch (NameException e) {
            txtName.setPromptText(e.toString());
        } catch (AgeException e) {
            txtAge.setPromptText(e.toString());
        } catch (GenderException e) {
            txtGender.setPromptText(e.toString());
        }
        txtName.clear();
        txtAge.clear();
        txtGender.clear();
        txtGroup.clear();

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
            f.toFile(targetGroup);
        }

    }

    public void clearFile(String targetGroup) throws IOException {
        FileWriter fw = new FileWriter(targetGroup);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("");
        bw.close();
    }


    public void sortAge(MouseEvent mouseEvent) throws IOException {
        String targetGroup = listGroups.getSelectionModel().getSelectedItem() + ".txt";
        FriendCreator fc = new FriendCreator();
        ArrayList<Friend> sortedList = new ArrayList<>();
        sortedList.addAll(fc.getFriends(targetGroup));
        clearFile(targetGroup);
        Collections.sort(sortedList);
        for (Friend f:sortedList){
            f.toFile(targetGroup.substring(0,targetGroup.length()-4));
        }
        listFriends.getItems().clear();
        listFriends.getItems().addAll(fc.getFriends(targetGroup));
    }

    public void sortLastName(MouseEvent mouseEvent) throws IOException{
        FriendComparator com = new FriendComparator();
        String targetGroup = listGroups.getSelectionModel().getSelectedItem() + ".txt";
        FriendCreator fc = new FriendCreator();
        ArrayList<Friend> sortedList = new ArrayList<>();
        sortedList.addAll(fc.getFriends(targetGroup));
        clearFile(targetGroup);
        Collections.sort(sortedList, com);
        Collections.reverse(sortedList);
        for (Friend f:sortedList){
            f.toFile(targetGroup.substring(0,targetGroup.length()-4));
        }

        listFriends.getItems().clear();
        listFriends.getItems().addAll(fc.getFriends(targetGroup));
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
