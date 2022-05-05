package com.ciandt.noteMovies.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_NOTE_MOVIES")
public class NoteMovieModel implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 80)
    private String name;

    @Column(nullable = false, length = 50)
    private String genre;

    @Column(nullable = false)
    private double note;

    @Column(nullable = false)
    private double noteIMDB;

    @Column(nullable = false, length = 50)
    private String director;

    @Column(nullable = false, length = 50)
    private String whereFound;

    @Column(nullable = false, length = 50)
    private String productor;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

}
