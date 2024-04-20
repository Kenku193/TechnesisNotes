package com.technesis.syrovatko.repository;

import com.technesis.syrovatko.model.Note;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NoteRepository {

    public static ObservableList<Note> noteList = FXCollections.observableArrayList();

    public void addNote(Note note){
        noteList.add(note);
    }

    public void deleteNote(Note note){
        noteList.remove(note);
    }

    public void updateNote(Note note) {
        for (Note inListNote : noteList) {
            if (inListNote.getOrigin().equals(note.getOrigin())) {
                noteList.set(noteList.indexOf(inListNote), new Note(note.getOrigin(), note.getTitle(), note.getText()));
            }
        }
    }
}
