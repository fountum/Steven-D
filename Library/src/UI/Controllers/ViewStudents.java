package UI.Controllers;

import Database.DatabaseHandler;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ViewStudents implements Initializable {


    public static class Student {
        private final SimpleStringProperty name;
        private final SimpleStringProperty grade;
        private final SimpleStringProperty email;
        private final SimpleStringProperty bookID;

        Student(String name, int grade, String email, int bookID) {
            this.name = new SimpleStringProperty(name);
            this.grade = new SimpleStringProperty(Integer.toString(grade));
            this.email = new SimpleStringProperty(email);
            this.bookID = new SimpleStringProperty(Integer.toString(bookID));

        }

        public String getName() {
            return name.get();
        }

        public String getGrade() {
            return grade.get();
        }

        public String getEmail() {
            return email.get();
        }

        public String getBookID() {
            if (bookID.get().equals("0")) {
                return "No Book Taken Out";
            } else {
                return bookID.get();
            }
        }


    }

    private ObservableList<Student> students = FXCollections.observableArrayList();
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colGrade;
    public TableColumn colEmail;
    public TableView tableStudents;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initCol() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
    }

    private void loadData() throws SQLException {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String qu = "SELECT * FROM STUDENTS";
        ResultSet rs = handler.execQuery(qu);
        while (rs.next()) {
            String name = rs.getString("name");
            int grade = rs.getInt("grade");
            String email = rs.getString("email");
            int bookID = rs.getInt("bookID");

            students.add(new Student(name, grade, email, bookID));
        }
        tableStudents.getItems().setAll(students);
    }


}

