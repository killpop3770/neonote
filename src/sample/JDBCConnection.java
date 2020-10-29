package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.sql.Date;

public class JDBCConnection{
    //  Database credentials
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/neo_note";
    static final String USER = "neo";
    static final String PASS = "root";

    public Connection getConnection(){
        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return null;
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");

        Connection connection;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }
    }


    public void createNote(String text){
        Date date = new Date(System.currentTimeMillis());

        Connection connection = getConnection();

        try {
            String query = "INSERT INTO notes VALUES(default, ? , ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, text);
            preparedStatement.setDate(2, date);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ObservableList<Note> getNotes(){
        ObservableList<Note> noteList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM notes";
        Statement statement;
        ResultSet resultSet;

        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            Note note;

            while(resultSet.next()){
                note = new Note(resultSet.getInt("id"),
                                    resultSet.getString("note"),
                                        resultSet.getDate("date"));
                noteList.add(note);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return noteList;
    }

    public void showNotes(TableColumn<Note, Integer> idColumn,
                          TableColumn<Note, String> noteColumn,
                          TableColumn<Note, Date> dateColumn,
                          TableView<Note> tableNotes){

        ObservableList<Note> list = getNotes();

        try {
            idColumn.setCellValueFactory(new PropertyValueFactory<Note, Integer>("id"));

            noteColumn.setCellValueFactory(new PropertyValueFactory<Note, String>("note"));

            dateColumn.setCellValueFactory(new PropertyValueFactory<Note, Date>("date"));

            tableNotes.setItems(list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}