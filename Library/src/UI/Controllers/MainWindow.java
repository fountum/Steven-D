package UI.Controllers;

import Database.DatabaseHandler;
import Model.Book;
import Model.MyExceptions;
import Model.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;


public class MainWindow implements Initializable {
    public Student student;
    public ArrayList<Student> students = new ArrayList<>();
    public ComboBox comboStudent;
    public Label labelTaken;
    public TableView tableBooks;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colID;
    public TableColumn colTaken;
    public Label labelStatus;
    private ObservableList<Book> books = FXCollections.observableArrayList();
    private DatabaseHandler handler;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Main window initialized");
    }

    private void initCol() {
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colTaken.setCellValueFactory(new PropertyValueFactory<>("taken"));
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
    }

    public void loadData() throws SQLException {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        tableBooks.getItems().clear();
        students.clear();
        books.clear();
        String qu = "SELECT * FROM STUDENTS";
        ResultSet rs = handler.execQuery(qu);
        while (rs.next()) {
            String name = rs.getString("name");
            int grade = rs.getInt("grade");
            String email = rs.getString("email");
            int bookID = rs.getInt("bookID");

            students.add(new Student(name, grade, email, bookID));
        }
        comboStudent.getItems().setAll(students);

        qu = "SELECT * FROM BOOKS";
        rs = handler.execQuery(qu);
        while (rs.next()) {
            String title = rs.getString("title");
            String author = rs.getString("author");
            boolean taken = rs.getBoolean("taken");
            int ID = rs.getInt("ID");

            books.add(new Book(title, author, taken, ID));
        }
        tableBooks.getItems().setAll(books);

    }

    public void exchangeBook() {
        ArrayList<String> updates = new ArrayList<>();

        try {
            handler = DatabaseHandler.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Book oldBook = (Book) tableBooks.getSelectionModel().getSelectedItems().get(0);

        try {
            if (student.getBookID() == 0) {
                if (!(Boolean.parseBoolean(oldBook.getTaken()))) {
                    String up = "UPDATE BOOKS\n" +
                            "SET TAKEN='true'\n" +
                            "WHERE ID = '" + oldBook.getID() + "'";
                    updates.add(up);
                    up = "UPDATE STUDENTS\n" +
                            "SET BOOKID='" + oldBook.getID() + "'" +
                            "WHERE NAME='" + student.getName() + "' AND GRADE='" + student.getGrade() + "' AND EMAIL='" + student.getEmail() + "'";
                    updates.add(up);
                    labelStatus.setText("Book taken out.");
                } else {
                    throw new MyExceptions(Boolean.parseBoolean(oldBook.getTaken()));
                }

            } else if (student.getBookID() != 0) {
                String up = "UPDATE BOOKS\n" +
                        "SET TAKEN='false'\n" +
                        "WHERE ID = '" + student.getBookID() + "'";
                updates.add(up);
                up = "UPDATE STUDENTS\n" +
                        "SET BOOKID='0'" +
                        "WHERE NAME='" + student.getName() + "' AND GRADE='" + student.getGrade() + "' AND EMAIL='" + student.getEmail() + "'";
                updates.add(up);
                labelStatus.setText("Book returned");
            }

        } catch (MyExceptions e) {
            labelStatus.setText(e.toString());
            return;
        } catch (java.lang.NullPointerException e) {
            if (student == null) {
                labelStatus.setText("Error: no student selected.");
            } else if (oldBook == null) {
                labelStatus.setText("Error: no book selected.");
            }
            return;
        }
        Iterator<String> updateIter = updates.iterator();
        while (updateIter.hasNext()) {
            handler.execUpdate(updateIter.next());
        }
        reload();
    }

    public void reload() {
        String name;
        try {
            Student s = (Student) comboStudent.getValue();
            name = s.getName();
        } catch (NullPointerException e) {
            System.out.println("No student selected");
            name = "";
        }

        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Iterator<Student> iter = comboStudent.getItems().iterator();
        while (iter.hasNext()) {
            Student newS = iter.next();
            if (newS.getName().equals(name)) {
                comboStudent.setValue(newS);
            }
        }


    }

    private void loadWindow(String location, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(location));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openAddStudent(ActionEvent actionEvent) {
        loadWindow("../Layouts/AddStudent.fxml", "Add Student");
    }

    public void openViewStudents(ActionEvent actionEvent) {
        loadWindow("../Layouts/ViewStudents.fxml", "View Students");
    }

    public void openAddBook(ActionEvent actionEvent) {
        loadWindow("../Layouts/AddBook.fxml", "Add Book");
    }

    public void selectStudent() {
        student = students.get(students.indexOf(comboStudent.getValue()));
        if (student.getBookID() != 0) {
            labelTaken.setText("This Student has taken out a book.");
        } else {
            labelTaken.setText("");
        }
    }

}
