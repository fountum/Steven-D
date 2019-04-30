package UI.Controllers;

import Database.DatabaseHandler;
import Model.Book;
import Model.MyExceptions;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddBook implements Initializable {
    public TextField textTitle;
    public TextField textAuthor;
    public Label labelStatus;
    DatabaseHandler handler;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            handler = DatabaseHandler.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(ActionEvent actionEvent) {
        String title = textTitle.getText();
        String author = textAuthor.getText();
        int id = title.hashCode() + author.hashCode();

        try {
            if (title.isEmpty() || author.isEmpty()) {
                throw new MyExceptions(new Book(title, author, false, 0));
            }
        } catch (MyExceptions e) {
            labelStatus.setText(e.toString());
            return;
        }
        if (title.contains("'")) {
            int index = title.indexOf("'");
            while (index > -1) {
                title = title.substring(0, index) + "''" + title.substring(index + 1);
                index = title.indexOf("'", index + 2);
            }
        }

        String st = "INSERT INTO BOOKS VALUES (" +
                "'" + id + "'," +
                "'" + title + "'," +
                "'" + author + "'," +
                "'" + false + "')";

        if (handler.execAction(st)) {
            labelStatus.setText("Book added");
        } else {
            labelStatus.setText("Book not added");
        }

        textAuthor.clear();
        textTitle.clear();
    }


}
