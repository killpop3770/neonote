package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Date;

public class Controller extends JDBCConnection {

    @FXML
    private TableColumn<Note, String> noteColumn;

    @FXML
    private Button newNoteButton;

    @FXML
    private TableColumn<Note, Date> dateColumn;

    @FXML
    private TableView<Note> tableNotes;

    @FXML
    private TableColumn<Note, Integer> idColumn;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField timeArea;


    @FXML
    void initialize(){
        JDBCConnection connection = new JDBCConnection();
        connection.showNotes(idColumn, noteColumn, dateColumn, tableNotes);
        timeArea.setText(new Date(System.currentTimeMillis()).toString());

        newNoteButton.setOnAction(event -> {
            if(!textArea.getText().equals("")) {
                connection.createNote(textArea.getText().trim());
                connection.showNotes(idColumn, noteColumn, dateColumn, tableNotes);
                textArea.clear();
            }
        });



    }
}
