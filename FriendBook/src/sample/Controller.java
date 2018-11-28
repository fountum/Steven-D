package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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


    public void makeFriend(MouseEvent mouseEvent) {
        Friend temp = new Friend(txtName.getText(), Integer.parseInt(txtAge.getText()), txtGender.getText());
        listFriends.getItems().add(temp);

        txtName.clear();
        txtAge.clear();
        txtGender.clear();

    }

    public void deleteFriend(MouseEvent mouseEvent) {
        Friend temp = listFriends.getSelectionModel().getSelectedItem();
        listFriends.getItems().remove(temp);
    }


    public void displayFriends(MouseEvent mouseEvent) {
        Friend temp = listFriends.getSelectionModel().getSelectedItem();
        lblName.setText(temp.getName());
        lblAge.setText(Integer.toString(temp.getAge()));
        lblGender.setText(temp.getGender());
    }
}
