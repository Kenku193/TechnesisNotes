package com.technesis.syrovatko.controller;

import com.technesis.syrovatko.model.Note;
import com.technesis.syrovatko.repository.NoteRepository;
import com.technesis.syrovatko.util.NoteFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Data;

import java.io.IOException;

@Data
public class AddOrEditController {
    NoteRepository noteRepository = new NoteRepository();
    Note note;
    Boolean isEdit = false;
    @FXML
    TextField addOrEditNoteTitleField;
    @FXML
    TextArea addOrEditNoteTextArea;
    @FXML
    Label addOrEditNoteBornTimeLabel;
    @FXML
    Button addOrEditOkButton;
    @FXML
    Button addOrEditCancelButton;

    void initData(Note note, Boolean isEdit) {
        this.note = note;
        this.isEdit = isEdit;
        addOrEditNoteTitleField.setText(note.getTitle());
        addOrEditNoteTextArea.setText(note.getText());
        addOrEditNoteBornTimeLabel.setText(note.getOrigin().format(NoteFormatter.dateTimeFormatter));
    }

    @FXML
    protected void onAdOrEditOkButtonClick() throws IOException {
        note.setTitle(addOrEditNoteTitleField.getText());
        note.setText(addOrEditNoteTextArea.getText());
        if (isEdit == true) {
            noteRepository.updateNote(this.note);
        } else {
            noteRepository.addNote(this.note);
        }
        Stage stage = (Stage) addOrEditCancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onAddOrEditCancelButtonClick() throws IOException {
        Stage stage = (Stage) addOrEditCancelButton.getScene().getWindow();
        stage.close();
    }
}
