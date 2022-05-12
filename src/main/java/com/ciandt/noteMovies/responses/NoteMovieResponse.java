package com.ciandt.noteMovies.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NoteMovieResponse implements Serializable{

    private UUID id;
    private String name;
    private String genre;
    private double note;
    private double noteIMDB;
    private String director;
    private String whereFound;
    private String productor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime registrationDate;
}
