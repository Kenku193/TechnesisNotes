package com.technesis.syrovatko.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Note {
    private LocalDateTime origin;
    private String  title;
    private String  text;

    public Note() {
        this.origin = LocalDateTime.now();
        this.title = "Новая заметка";
    }

    @Override
    public String toString() {
        return title;
    }

    public String toFullString() {
        return "Note{" +
                "origin=" + origin +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
