package com.ciandt.noteMovies.responses;

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

    private LocalDateTime registrationDate;

}
