package UI.Controllers;

import Database.DatabaseHandler;
import Model.MyExceptions;
import Model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AddStudent implements Initializable {
    public Label labelStatus;
    DatabaseHandler handler;
    public TextField textName;
    public TextField textGrade;
    public TextField textEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            handler = DatabaseHandler.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(ActionEvent actionEvent) {
        String name = textName.getText();
        int grade = Integer.parseInt(textGrade.getText());
        String email = textEmail.getText();
        int bookID = 0;

        try {
            if (name.isEmpty() || email.isEmpty() || !email.contains("@") || grade < 1 || grade > 12) {
                throw new MyExceptions(new Student(name, grade, email));
            }
        } catch (MyExceptions e) {
            labelStatus.setText(e.toString());
            return;
        }

        String st = "INSERT INTO STUDENTS VALUES (" +
                "'" + name + "'," +
                "'" + grade + "'," +
                "'" + email + "'," +
                "'" + bookID + "')";
        if (handler.execAction(st)) {
            System.out.println("Student added");
        } else {
            System.out.println("Student not added");
        }
        textName.clear();
        textEmail.clear();
        textGrade.clear();
        labelStatus.setText("Student added");
    }




}
