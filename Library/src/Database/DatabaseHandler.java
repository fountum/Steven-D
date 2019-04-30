package Database;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.*;

public class DatabaseHandler {
    private final String DB_url = "jdbc:derby:database/library;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;
    private static DatabaseHandler handler = null;

    public DatabaseHandler() {
        createConnection();
        createBooksTable();
        createStudentsTable();
    }

    private void createStudentsTable() {
        String TABLE_NAME = "STUDENTS";
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dmn = conn.getMetaData();
            ResultSet tables = dmn.getTables(null, null, TABLE_NAME, null);
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists.");
            } else {
                String statement = "CREATE TABLE " + TABLE_NAME + "(\n"
                        + "name varchar(200),\n"
                        + "grade varchar(200),\n"
                        + "email varchar(200),\n"
                        + "bookID varchar(200))";
                System.out.println(statement);
                stmt.execute(statement);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "setting up database");
        }
    }

    private void createBooksTable() {
        String TABLE_NAME = "BOOKS";
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dmn = conn.getMetaData();
            ResultSet tables = dmn.getTables(null, null, TABLE_NAME, null);
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists.");
            } else {
                String statement = "CREATE TABLE " + TABLE_NAME + "(\n"
                        + "id varchar(200) primary key,\n"
                        + "title varchar(200),\n"
                        + "author varchar(200),\n"
                        + "taken varchar(200))";
                System.out.println(statement);
                stmt.execute(statement);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "setting up database");
        }
    }

    private void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection(DB_url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseHandler getInstance() throws SQLException {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    public boolean execAction(String qu) {
        try {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage(), "Error Occurred", JOptionPane.ERROR_MESSAGE);
            System.out.println("exception at execAction" + e.getLocalizedMessage());
            return false;
        }
    }

    public ResultSet execQuery(String query) {
        ResultSet resultSet;
        try {
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Exception at Execute query");
            return null;
        }
        return resultSet;
    }

    public void execUpdate(String update) {
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(update);
        } catch (SQLException e) {
            System.out.println("Exception at Execute Update");
            e.printStackTrace();
        }
    }


}
