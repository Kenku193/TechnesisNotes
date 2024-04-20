package com.technesis.syrovatko.controller;

import com.technesis.syrovatko.FxApp;
import com.technesis.syrovatko.model.Note;
import com.technesis.syrovatko.repository.NoteRepository;
import com.technesis.syrovatko.util.NoteFormatter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

@Data
public class MainController implements Initializable {

    NoteRepository noteRepository = new NoteRepository();
    @FXML
    ListView<Note> mainNotesListView;
    @FXML
    Label mainNoteTitleLabel;
    @FXML
    TextArea mainNoteTextArea;
    @FXML
    Label mainNoteBornTime;
    @FXML
    Button mainAddNoteButton;
    @FXML
    Button mainEditNoteButton;
    @FXML
    Button mainDeleteNoteButton;
    Note currentNote;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainNotesListView.setItems(NoteRepository.noteList);
        mainNotesListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Note>() {
                    @Override
                    public void changed(ObservableValue<? extends Note> observableValue, Note note, Note t1) {
                        try {
                            currentNote = mainNotesListView.getSelectionModel().getSelectedItem();
                            mainNoteTitleLabel.setText(currentNote.getTitle());
                            mainNoteTextArea.setText(currentNote.getText());
                            mainNoteBornTime.setText(currentNote.getOrigin().format(NoteFormatter.dateTimeFormatter));
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                }
        );
    }

    @FXML
    protected void onMainAddNoteButtonClick() throws IOException {
        Note note = new Note();
        addOrEditWindowViewer("Add note", note, false);
    }

    @FXML
    protected void onMainEditNoteButtonClick() throws IOException {
        cleanMainView();
        Note selectedNote = mainNotesListView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            addOrEditWindowViewer("Edit note", selectedNote, true);
        }
    }

    @FXML
    protected void onMainDeleteNoteButton() throws IOException {
        Note selectedNote = mainNotesListView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Удалить заметку \"" + selectedNote.getTitle() + "\"?", ButtonType.CANCEL, ButtonType.YES);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText(null);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                NoteRepository.noteList.remove(selectedNote);
                if (mainNotesListView.getSelectionModel().isEmpty()) {
                    cleanMainView();
                }
            }
        }
    }

    protected void addOrEditWindowViewer(String title, Note note, Boolean isEdit) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FxApp.class.getResource("/view/edit.fxml"));
            AnchorPane rootLayout = loader.load();
            Scene scene = new Scene(rootLayout, 600, 400);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            InputStream iconStream = getClass().getResourceAsStream("/icons/image.png");
            Image image = new Image(iconStream);
            AddOrEditController controller = loader.getController();
            controller.initData(note, isEdit);
            primaryStage.getIcons().add(image);
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cleanMainView() {
        mainNoteTitleLabel.setText(mainNoteTitleLabel.getAccessibleText());
        mainNoteTextArea.setText(null);
        mainNoteBornTime.setText(mainNoteBornTime.getAccessibleText());
    }
}
